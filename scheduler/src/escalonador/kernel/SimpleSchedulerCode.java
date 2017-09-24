package escalonador.kernel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import escalonador.scheduler.util.RoundKernel;
import escalonador.scheduler.util.StreamKernel;

public class SimpleSchedulerCode extends AbstractSchedulerCode {

	public SimpleSchedulerCode(List<Kernel> kernels, RoundKernel initialKernels, List<RoundKernel> roundsStep, int numberOfStream, boolean smDebug) {
		super(smDebug);
		printIncludes();
		if (smDebug) {
			printAsm();
		}
		Map<Integer, Kernel> kernelMap = new HashMap<>();
		for (Kernel kernel : kernels) {
			kernelMap.put(kernel.getId(), kernel);
			println(0, new KernelCode(kernel.getName(), kernel.getSharedMemory(), smDebug).toString());
		}
		printCreateStreams(numberOfStream, kernels);
		for (int i = 0; i < initialKernels.size(); i++) {
			StreamKernel entryStreamKernel = initialKernels.get(i);
			Kernel kernel = entryStreamKernel.getKernel();
			kernel = kernelMap.get(kernel.getId());
			printExecuteKernel(kernel, entryStreamKernel.getStream());
		}
		for (int i = 0; i < roundsStep.size(); i++) {
			RoundKernel entryStreamKernelList = roundsStep.get(i);
			for (StreamKernel entryStreamKernel : entryStreamKernelList) {
				Kernel kernel = entryStreamKernel.getKernel();
				kernel = kernelMap.get(kernel.getId());
				printExecuteKernel(kernel, entryStreamKernel.getStream());
			}
		}
		for (int i = 0; i < numberOfStream; i++) {
			printSyncStream(i);
		}
		printDestroyStreams(numberOfStream, kernels, initialKernels, roundsStep);
	}
}
