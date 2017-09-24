package escalonador.scheduler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import escalonador.algorithm.greed.GreedKnapsack;
import escalonador.kernel.Kernel;
import escalonador.scheduler.util.GpuKnapsackResource;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.util.FileWriter;

public class GreedScheduler2 extends GreedScheduler {

	public GreedScheduler2(ResourceSet resourceSet, List<Kernel> kernelList, Resource kernelUnit, int smCount, int queueLimit, int blockCount, int kernelCount) throws IOException {
		super(resourceSet, kernelList, kernelUnit, smCount, queueLimit, blockCount, kernelCount);
	}

	protected Kernel[] execute(FileWriter file, boolean initialRound) throws IOException {
		GpuKnapsackResource currentResource = resource.createCurrentResource();
		float smCurrent = currentResource.value(1);
		GreedKnapsack knapsack = new GreedKnapsack(weightResources(), currentResource, values());
		int[] scheduledKernelIndexs = knapsack.execute();
		Kernel[] array = new Kernel[scheduledKernelIndexs.length];
		float sum = 0;
		for (int i = 0; i < array.length; i++) {
			Kernel kernel = kernels.get(scheduledKernelIndexs[i]);
			array[i] = kernel;
			sum += kernel.getSharedMemory() * kernel.getBlocks();
		}
		file.println(String.format("Greed with SharedMemory Resource %.1fK and Sum: %.1fK", smCurrent / 1024, sum / 1024));
		Arrays.sort(array, new Comparator<Kernel>() {
			@Override
			public int compare(Kernel a, Kernel b) {
				int wa = (int) value(a);
				int wb = (int) value(b);
				return wb - wa;
			}
		});
		return array;
	}

	protected float value(Kernel kernel) {
		float value = 0.0f;
		float register = smResource.getRegister();
		float sharedMemory = smResource.getSharedMemory();
		float thread = smResource.getThread();
		float smCount = this.smCount;
		if (register > 0) {
			float kernelRegister = kernel.getRegister();
			value += kernelRegister / register / smCount;
		}
		if (sharedMemory > 0) {
			float kernelSharedMemory = kernel.getSharedMemory();
			value += kernelSharedMemory / sharedMemory / smCount;
		}
		if (thread > 0) {
			float kernelThread = kernel.getThread();
			value += kernelThread / thread;
		}
		return value;
	}

}
