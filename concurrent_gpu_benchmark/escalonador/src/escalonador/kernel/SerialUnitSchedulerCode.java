package escalonador.kernel;

import java.util.List;
import java.util.Map.Entry;

import escalonador.scheduler.Kernel;

public class SerialUnitSchedulerCode extends AbstractSchedulerCode {

	public SerialUnitSchedulerCode(List<Kernel> kernels, List<Entry<Integer, Kernel>> initialKernels, List<List<Entry<Integer, Kernel>>> roundsStep, int numberOfStream) {
		printIncludes();
		printAsm();
		for (Kernel kernel : kernels) {
			println(0, new KernelCode("kernel_" + kernel.getId() + "_" + kernel.getSharedMemory(), kernel.getSharedMemory()).toString());
		}
		printCreateStreams(numberOfStream, kernels.size());
		for (int i = 0; i < initialKernels.size(); i++) {
			Entry<Integer, Kernel> entryStreamKernel = initialKernels.get(i);
			printExecuteKernel(entryStreamKernel.getValue(), entryStreamKernel.getKey());
		}
		for (int i = 0; i < roundsStep.size(); i++) {
			List<Entry<Integer, Kernel>> entryStreamKernelList = roundsStep.get(i);
			for (Entry<Integer, Kernel> entryStreamKernel : entryStreamKernelList) {
				printExecuteKernel(entryStreamKernel.getValue(), entryStreamKernel.getKey());
			}
		}
		for (int i = 0; i < numberOfStream; i++) {
			printSyncStream(i);
		}
		printDestroyStreams(numberOfStream, kernels, initialKernels, roundsStep);
	}
}
