package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import escalonador.kernel.Kernel;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.scheduler.util.RoundKernel;
import escalonador.scheduler.util.StreamKernel;

public class SequencialScheduler extends AbstractScheduler {

	public SequencialScheduler(ResourceSet resourceSet, Resource kernelUnit, List<Kernel> kernels, int spCount, int queueLimit) throws IOException {
		preparedKernels = super.prepareKernels(resourceSet, kernels);
	}

	@Override
	public void execute() throws IOException {
		numberOfStream = 1;
		initialKernels = new RoundKernel();
		for (Kernel kernel : preparedKernels) {
			initialKernels.add(new StreamKernel(0, kernel));
		}
		roundsStep = new ArrayList<>();
	}

}
