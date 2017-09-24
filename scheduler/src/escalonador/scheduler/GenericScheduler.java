package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import escalonador.algorithm.knapsack.KnapsackIterator;
import escalonador.algorithm.knapsack.KnapsackResource;
import escalonador.kernel.Kernel;
import escalonador.queue.GpuQueue;
import escalonador.queue.GpuQueueSet;
import escalonador.scheduler.util.GpuKnapsackIterator;
import escalonador.scheduler.util.GpuKnapsackResource;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.scheduler.util.RoundKernel;
import escalonador.scheduler.util.StreamKernel;
import escalonador.util.FileWriter;

public abstract class GenericScheduler extends AbstractScheduler {

	protected static final int QUEUE_START = 13;

	protected static final int[] STEPS = new int[] { 1, 1024, 1 };

	protected final List<Kernel> kernels;

	protected final GpuQueueSet queues;

	protected GpuKnapsackIterator resource;

	protected int smCount;

	protected int queueLimit;

	protected Resource smResource;

	protected int time;

	protected Resource kernelUnit;

	public GenericScheduler(ResourceSet resourceSet, List<Kernel> kernelList, Resource kernelUnit, int smCount, int queueLimit, int blockCount, int kernelCount) throws IOException {
		this.kernelUnit = kernelUnit;
		this.smCount = smCount;
		this.queueLimit = queueLimit;
		this.smResource = resourceSet.getResources()[0];
		this.resource = new GpuKnapsackIterator(capacity(resourceSet)).end();
		this.preparedKernels = prepareKernels(resourceSet, kernelList);
		this.kernels = new ArrayList<>(preparedKernels);
		this.queues = new GpuQueueSet(queueLimit);
	}

	public void execute() throws IOException {
		// try (FileWriter file = new FileWriter("dynamic.txt")) {
		initialKernels = initialExecute(null);
		roundsStep = roundExecute(null);
		// }
		this.numberOfStream = numberOfStream();
	}

	protected RoundKernel initialExecute(FileWriter file) throws IOException {
		RoundKernel round = new RoundKernel();
		// file.println("Initial Kernels:");
		Kernel[] scheduledKernels = execute(file, true);
		int queueCount = Math.min(queueLimit, scheduledKernels.length);
		for (int queueIndex = 0; queueIndex < queueCount; queueIndex++) {
			Kernel scheduledKernel = scheduledKernels[queueIndex];
			enqueueKernel(file, queueIndex, round, scheduledKernel);
		}
		 overflowExecute(file, round);
		return round;
	}

	protected List<RoundKernel> roundExecute(FileWriter file) throws IOException {
		List<RoundKernel> rounds = new ArrayList<>();
		while (!kernels.isEmpty()) {
			int queueIndex = queues.findEarlyQueueWithKernel();
			dequeueKernel(file, queueIndex);
			if (resource.hasResource()) {
				// file.println("Step:");
				Kernel[] scheduledKernels = execute(file, false);
				RoundKernel entryList = new RoundKernel();
				for (Kernel scheduledKernel : scheduledKernels) {
					enqueueKernel(file, queueIndex, entryList, scheduledKernel);
					queueIndex = queues.findEarlyQueue();
				}
				 overflowExecute(file, entryList);
				rounds.add(entryList);
			}
		}
		return rounds;
	}

	protected void overflowExecute(FileWriter file, RoundKernel entryList) throws IOException {
//		file.println("Kernels to Overflow:");
		if (!kernels.isEmpty() && resource.hasResource()) {
			TreeSet<Kernel> kernelSet = kernelLowerThan(resource);
			while (!kernelSet.isEmpty() && resource.hasResource()) {
				Kernel scheduledKernel = kernelSet.first();
				if (resource.contains(resourcePerBlock(scheduledKernel))) {
					int queueIndex = queues.findEarlyQueue();
					enqueueKernel(file, queueIndex, entryList, scheduledKernel);
				}
				kernelSet.remove(scheduledKernel);
			}
		}
	}

	protected List<Kernel> prepareKernels(ResourceSet resourceSet, List<Kernel> kernelList) {
		List<Kernel> kernels = new ArrayList<>();
		for (int i = 0; i < kernelList.size(); i++) {
			Kernel kernel = new Kernel(kernelList.get(i));
			Resource resource = resourceSet.getResource(0);
			if (kernel.getRegister() > resource.getRegister()) {
				kernel.setRegister(resource.getRegister());
			}
			if (kernel.getSharedMemory() > resource.getSharedMemory()) {
				kernel.setSharedMemory(resource.getSharedMemory());
			}
			if (kernel.getThread() > resource.getThread()) {
				kernel.setThread(resource.getThread());
			}
			int blocks = kernel.getBlocks();
			if (kernel.getRegister() * blocks > smResource.getRegister() * smCount) {
				blocks = (int) Math.floor((float) smResource.getRegister() * smCount / kernel.getRegister());
			}
			if (kernel.getSharedMemory() * blocks > smResource.getSharedMemory() * smCount) {
				blocks = (int) Math.floor((float) smResource.getSharedMemory() * smCount / kernel.getSharedMemory());
			}
			if (kernel.getThread() * blocks > smResource.getThread() * smCount) {
				blocks = (int) Math.floor((float) smResource.getThread() * smCount / kernel.getThread());
			}
			kernel.setBlocks(blocks);
			kernels.add(kernel);
		}
		return kernels;
	}

	protected TreeSet<Kernel> kernelLowerThan(KnapsackIterator resource) {
		TreeSet<Kernel> kernelSet = new TreeSet<Kernel>(new Comparator<Kernel>() {
			@Override
			public int compare(Kernel a, Kernel b) {
				int wa = (int) weightValue(a);
				int wb = (int) weightValue(b);
				return wa - wb;
			}
		});
		for (Kernel kernel : kernels) {
			if (resource.contains(resourcePerBlock(kernel))) {
				kernelSet.add(kernel);
			}
		}
		return kernelSet;
	}

	protected abstract Kernel[] execute(FileWriter file, boolean initialRound) throws IOException;

	protected void dequeueKernel(FileWriter file, int queueIndex) throws IOException {
		GpuQueue queue = queues.get(queueIndex);
		Kernel kernel = queue.dequeue();
		resource.plus(weightResource(kernel));
		time = queue.getTimeCount();
		// file.println("Remove Kernel " + kernel.getName() + " from Queue " +
		// (queueIndex + QUEUE_START));
	}

	protected void enqueueKernel(FileWriter file, int queueIndex, RoundKernel stream, Kernel scheduledKernel) throws IOException {
		// println(file, queueIndex, scheduledKernel);
		stream.add(new StreamKernel(queueIndex, scheduledKernel));
		kernels.remove(scheduledKernel);
		queues.get(queueIndex).add(scheduledKernel);
		resource.minus(weightResource(scheduledKernel));
	}

	protected GpuKnapsackResource capacity(ResourceSet resourceSet) {
		return new GpuKnapsackResource(new int[] { 0, resourceSet.getSharedMemory(), 0 }, STEPS);
	}

	protected float[] values() {
		float[] array = new float[kernels.size()];
		for (int i = 0; i < kernels.size(); i++) {
			array[i] = value(kernels.get(i));
		}
		return array;
	}

	protected abstract float value(Kernel kernel);

	protected float weightValue(Kernel kernel) {
		float globalRegister = (float) smResource.getRegister() / smCount;
		float globalSharedMemory = (float) smResource.getSharedMemory() / smCount;
		float globalThread = (float) smResource.getThread() / smCount;
		float register = kernel.getRegister();
		float sharedMemory = kernel.getSharedMemory();
		float thread = kernel.getThread();
		float value = 0;
		if (globalRegister > 0) {
			value += register / globalRegister;
		}
		if (globalSharedMemory > 0) {
			value += sharedMemory / globalSharedMemory;
		}
		if (globalThread > 0) {
			value += thread / globalThread;
		}
		return value;
	}

	protected KnapsackResource[] weightResources() {
		KnapsackResource[] array = new KnapsackResource[kernels.size()];
		for (int i = 0; i < kernels.size(); i++) {
			Kernel kernel = kernels.get(i);
			array[i] = weightResource(kernel);
		}
		return array;
	}

	protected KnapsackResource weightResource(Kernel kernel) {
		return resourceAllBlocks(kernel);
	}

	protected KnapsackResource resourceAllBlocks(Kernel kernel) {
		int sharedMemory = kernel.getSharedMemory() * kernel.getBlocks();
		return new KnapsackResource(new int[] { 0, sharedMemory, 0 }, STEPS);
	}

	protected KnapsackResource resourcePerBlock(Kernel kernel) {
		int sharedMemory = kernel.getSharedMemory();
		return new KnapsackResource(new int[] { 0, sharedMemory, 0 }, STEPS);
	}

	protected int numberOfStream() {
		int streams = 0;
		for (int i = 0; i < queues.getQueueCount(); i++) {
			if (queues.get(i).getTimeCount() > 0) {
				streams++;
			}
		}
		return streams;
	}

	protected void println(FileWriter file, int streamIndex, Kernel kernel) throws IOException {
		String name = kernel.getName();
		int blocks = kernel.getBlocks();
		float gsm = kernel.getSharedMemory() * blocks / 1024;
		String string = String.format("\t%s Queue %d GSM: %.3fK Weight: %.3f Value: %.3f", name, (streamIndex + QUEUE_START), gsm, weightValue(kernel), value(kernel));
		file.println(string);
	}

}
