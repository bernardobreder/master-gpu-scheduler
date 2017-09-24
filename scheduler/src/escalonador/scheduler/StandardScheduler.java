package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import escalonador.kernel.Kernel;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.scheduler.util.RoundKernel;
import escalonador.scheduler.util.StreamKernel;

public class StandardScheduler extends AbstractScheduler {

	public StandardScheduler(ResourceSet resourceSet, Resource kernelUnit, List<Kernel> kernels, int spCount, int queueLimit) throws IOException {
		preparedKernels = super.prepareKernels(resourceSet, kernels);
	}

	@Override
	public void execute() throws IOException {
		numberOfStream = 32;
		List<Kernel> kernels = new ArrayList<>(preparedKernels);
		{
			initialKernels = new RoundKernel();
			List<Kernel> kernelToRemove = new ArrayList<>();
			List<Kernel> step = kernels;
			for (int i = 0; i < Math.min(32, step.size()); i++) {
				Kernel kernel = step.get(i);
				initialKernels.add(new StreamKernel(i, kernel));
				kernelToRemove.add(kernel);
			}
			kernels.removeAll(kernelToRemove);
		}
		{
			roundsStep = new ArrayList<>();
			while (!kernels.isEmpty()) {
				List<Kernel> kernelToRemove = new ArrayList<>();
				RoundKernel list = new RoundKernel();
				for (int i = 0; i < Math.min(32, kernels.size()); i++) {
					Kernel kernel = kernels.get(i);
					list.add(new StreamKernel(i, kernel));
					kernelToRemove.add(kernel);
				}
				kernels.removeAll(kernelToRemove);
				roundsStep.add(list);
			}
		}
	}

}
