package escalonador.kernel;

import java.util.List;

import escalonador.scheduler.Kernel;

public class RoundUnitSchedulerCode extends AbstractSchedulerCode {

	/**
	 * @param numberOfStream
	 */
	public RoundUnitSchedulerCode(List<List<Kernel>> kernelsRounds, int numberOfStream) {
		printIncludes();
		printCreateStreams(numberOfStream, numberOfKernel);
		for (List<Kernel> kernelList : kernelsRounds) {
			for (int i = 0; i < kernelList.size(); i++) {
				Kernel kernel = kernelList.get(i);
				printExecuteKernel(kernel, i);
			}
			for (int i = 0; i < kernelList.size(); i++) {
				printSyncStream(i);
			}
			println(1, "");
		}
		printDestroyStreams(numberOfStream, numberOfKernel);
	}
}
