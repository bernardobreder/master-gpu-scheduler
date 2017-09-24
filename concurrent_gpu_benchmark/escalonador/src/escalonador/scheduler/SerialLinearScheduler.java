package escalonador.scheduler;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import escalonador.util.Array4D;

public class SerialLinearScheduler extends AbstractSerialScheduler {

	public SerialLinearScheduler(ResourceSet resourceSet, Resource kernelUnit, List<Kernel> kernels, int spCount, int streamLimit) throws IOException {
		kernels = new ArrayList<>(kernels);

		List<GpuQueue> queues = new ArrayList<>();
		for (int i = 0; i < 32; i++) {
			queues.add(new GpuQueue());
		}
		numberOfStream = queues.size();

		initialKernels = new ArrayList<>();
		List<Kernel> step = kernels;
		for (int i = 0; i < Math.min(32, step.size()); i++) {
			Kernel kernel = step.get(i);
			initialKernels.add(new SimpleEntry<>(i, kernel));
			kernels.remove(kernel);
		}

		roundsStep = new ArrayList<>();
		int smIndex = 0;
		while (!kernels.isEmpty()) {
			List<Entry<Integer, Kernel>> list = new ArrayList<>();
			for (int i = 0; i < Math.min(32, kernels.size()); i++) {
				list.add(new SimpleEntry<>(smIndex++ % 32, kernels.remove(0)));
			}
			roundsStep.add(list);
		}
	}

	private int findFreeResourceIndex(ResourceSet resourceSet, Kernel kernel) {
		int ri = 0;
		for (int i = 1; i < resourceSet.getResourceCount(); i++) {
			Resource resource = resourceSet.getResources()[i];
			if (resource.sharedMemory > resourceSet.getResources()[ri].sharedMemory) {
				if (kernel != null && resource.has(kernel)) {
					ri = i;
				}
			}
		}
		return ri;
	}

	private int findEarlyQueue(List<GpuQueue> queues) {
		int qi = 0;
		for (int i = 1; i < queues.size(); i++) {
			if (queues.get(i).timeCount < queues.get(qi).timeCount) {
				qi = i;
			}
		}
		return qi;
	}

	protected List<Kernel> processStep(ResourceSet resourceSet, Resource kernelUnit, List<Kernel> kernels) {
		resourceSet = new ResourceSet(resourceSet);
		List<Kernel> result = new ArrayList<Kernel>();
		kernels = new ArrayList<Kernel>(kernels);
		for (int res = 0; res < resourceSet.getResourceCount(); res++) {
			Resource resource = resourceSet.getResources()[res];
			int globalRegisterScaled = resource.getRegister();
			int globalSharedMemoryScaled = resource.getSharedMemory() / kernelUnit.sharedMemory;
			int globalThreadScaled = resource.getThread() / kernelUnit.threads;
			Array4D map = new Array4D(kernels.size() + 1, globalRegisterScaled + 1, globalSharedMemoryScaled + 1, globalThreadScaled + 1);
			for (int k = 1; k <= kernels.size(); k++) {
				Kernel kernel = kernels.get(k - 1);
				for (int reg = kernelUnit.register; reg <= resource.getRegister(); reg += kernelUnit.register) {
					for (int sm = kernelUnit.sharedMemory; sm <= resource.getSharedMemory(); sm += kernelUnit.sharedMemory) {
						for (int th = kernelUnit.threads; th <= resource.getThread(); th += kernelUnit.threads) {
							int regDelta = reg - kernel.getRegister();
							int smDelta = sm - kernel.getSharedMemory();
							int thDelta = th - kernel.getThreads();
							int regScaled = reg / kernelUnit.register;
							int smScaled = sm / kernelUnit.sharedMemory;
							int thScaled = th / kernelUnit.threads;
							double withoutValue = map.get(k - 1, regScaled, smScaled, thScaled);
							if (regDelta < 0 || smDelta < 0 || thDelta < 0) {
								map.set(k, regScaled, smScaled, thScaled, withoutValue);
							} else {
								int regDeltaScaled = regDelta / kernelUnit.register;
								int smDeltaScaled = smDelta / kernelUnit.sharedMemory;
								int thDeltaScaled = thDelta / kernelUnit.threads;
								double withValue = map.get(k - 1, regDeltaScaled, smDeltaScaled, thDeltaScaled);
								map.set(k, regScaled, smScaled, thScaled, Math.max(withoutValue, withValue + kernel.getValue()));
							}
						}
					}
				}
			}
			for (int i = kernels.size(); i >= 1; i--) {
				int regScaled = resource.getRegister() / kernelUnit.register;
				int smScaled = resource.getSharedMemory() / kernelUnit.sharedMemory;
				int thScaled = resource.getThread() / kernelUnit.threads;
				double left = map.get(i, regScaled, smScaled, thScaled);
				double right = map.get(i - 1, regScaled, smScaled, thScaled);
				if (left != right) {
					Kernel kernel = kernels.get(i - 1);
					resource.decRegister(kernel.getRegister());
					resource.decSharedMemory(kernel.getSharedMemory());
					resource.decThreads(kernel.getThreads());
					result.add(kernel);
					kernels.remove(kernel);
				}
			}
		}
		return result;
	}

	public static class GpuQueue {

		private int timeCount;

		private LinkedList<Kernel> kernels = new LinkedList<>();

		@Override
		public String toString() {
			return "GpuQueue [timeCount=" + timeCount + ", kernels=" + kernels + "]";
		}

	}

}
