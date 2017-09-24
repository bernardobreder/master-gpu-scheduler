package escalonador.scheduler.dynamic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import escalonador.algorithm.knapsack.DynamicKnapsack;
import escalonador.algorithm.knapsack.MemoryDynamicKnapsack;
import escalonador.kernel.Kernel;
import escalonador.scheduler.GenericScheduler;
import escalonador.scheduler.util.GpuKnapsackResource;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.util.FileWriter;

public abstract class DynamicScheduler extends GenericScheduler {

	public DynamicScheduler(ResourceSet resourceSet, List<Kernel> kernelList, Resource kernelUnit, int smCount, int queueLimit, int blockCount, int kernelCount) throws IOException {
		super(resourceSet, kernelList, kernelUnit, smCount, queueLimit, blockCount, kernelCount);
	}

	protected Kernel[] execute(FileWriter file, boolean initialRound) throws IOException {
		GpuKnapsackResource currentResource = resource.createCurrentResource();
		// float smCurrent = currentResource.value(1);
		DynamicKnapsack knapsack = new MemoryDynamicKnapsack(weightResources(), currentResource, values());
		int[] scheduledKernelIndexs = knapsack.execute();
		Kernel[] array = new Kernel[scheduledKernelIndexs.length];
		// float sum = 0;
		for (int i = 0; i < array.length; i++) {
			Kernel kernel = kernels.get(scheduledKernelIndexs[i]);
			array[i] = kernel;
			// sum += kernel.getSharedMemory() * kernel.getBlocks();
		}
		// file.println(String.format("Knapsack with SharedMemory Resource %.1fK and Sum: %.1fK",
		// smCurrent / 1024, sum / 1024));
		Arrays.sort(array, new Comparator<Kernel>() {
			@Override
			public int compare(Kernel a, Kernel b) {
				return (int) value(b) - (int) value(a);
			}
		});
		return array;
	}

}
