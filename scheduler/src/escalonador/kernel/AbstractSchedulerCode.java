package escalonador.kernel;

import java.util.List;

import escalonador.Main;
import escalonador.scheduler.util.RoundKernel;
import escalonador.scheduler.util.StreamKernel;

public abstract class AbstractSchedulerCode extends AbstractCode {

	private int count = 0;

	protected boolean smDebug;

	public AbstractSchedulerCode(boolean smDebug) {
		this.smDebug = smDebug;
	}

	public void printIncludes() {
		println(0, "#include <cuda.h>");
		println(0, "#include <cuda_runtime.h>");
		println(0, "#include <cuda_profiler_api.h>");
		println(0, "#include <stdio.h>");
		println(0, "");
	}

	public void printAsm() {
		println(0, "__device__ unsigned int get_smid(void) {");
		println(1, "unsigned int ret;");
		println(1, "asm(\"mov.u32 %0, %smid;\" : \"=r\"(ret) );");
		println(1, "return ret;");
		println(0, "}");
		println(0, "");
	}

	public void printCreateStreams(int numberOfStream, List<Kernel> kernels) {
		println(0, "int main() {");
		println(1, "cudaSetDevice(%d);", Main.PROPERTIES.getDeviceId());
		if (smDebug) {
			println(1, "int *d_sm[%d], *h_sm[%d];", kernels.size(), kernels.size());
			for (int i = 0; i < kernels.size(); i++) {
				Kernel kernel = kernels.get(i);
				println(1, "cudaMalloc( (void**) &d_sm[%d], %d * sizeof(int) );", i, kernel.getBlocks());
			}
			for (int i = 0; i < kernels.size(); i++) {
				Kernel kernel = kernels.get(i);
				println(1, "h_sm[%d] = (int*) malloc( %d * sizeof(int) );", i, kernel.getBlocks());
			}
		}
		println(1, "cudaStream_t streams[%d];", numberOfStream);
		println(1, "for (int i = 0; i < %d; i++) { cudaStreamCreate(&streams[i]); }", numberOfStream);
	}

	public void printCreateStream(int streamIndex) {
		println(1, "cudaStreamCreate(&streams[%d]);", streamIndex);
	}

	public void printSyncStream(int streamIndex) {
		println(1, "cudaStreamSynchronize(streams[%d]);", streamIndex);
	}

	public void printExecuteKernel(Kernel kernel, int streamIndex) {
		int blocks = kernel.getBlocks();
		String kernelName = kernel.getName();
		int thread = 32;
		if (smDebug) {
			println(1, "%s<<<%d, %d, 0, streams[%d]>>>(%d, d_sm[%d]);", kernelName, blocks, thread, streamIndex, kernel.getTime(), kernel.getId() - 1);
		} else {
			println(1, "%s<<<%d, %d, 0, streams[%d]>>>(%d);", kernelName, blocks, thread, streamIndex, kernel.getTime());
		}
	}

	public void printDestroyStreams(int numberOfStream, List<Kernel> kernels, RoundKernel initialKernels, List<RoundKernel> roundsStep) {
		println(1, "cudaProfilerStop();");
		if (smDebug) {
			for (int i = 0; i < kernels.size(); i++) {
				Kernel kernel = kernels.get(i);
				println(1, "cudaMemcpy(h_sm[%d], d_sm[%d], %d*sizeof(int), cudaMemcpyDeviceToHost);", i, i, kernel.getBlocks());
			}
			for (int i = 0; i < initialKernels.size(); i++) {
				StreamKernel entryStreamKernel = initialKernels.get(i);
				int stream = entryStreamKernel.getStream();
				Kernel kernel = entryStreamKernel.getKernel();
				print(1, "printf(\"kernel %d submetido no stream %d ocupa %.1f Kb / bloco ", kernel.getId(), stream, (float) kernel.getSharedMemory() / 1024);
				for (int j = 0; j < kernel.getBlocks(); j++) {
					print(0, "(bloco %d -> ", j);
					print(0, "SM %d)");
				}
				print("\\n\", ");
				for (int j = 0; j < kernel.getBlocks(); j++) {
					print(0, "h_sm[%d][%d]", kernel.getId() - 1, j);
					if (j != kernel.getBlocks() - 1) {
						print(", ");
					}
				}
				println(0, ");");
			}
			for (int i = 0; i < roundsStep.size(); i++) {
				RoundKernel entryStreamKernelList = roundsStep.get(i);
				for (StreamKernel entryStreamKernel : entryStreamKernelList) {
					int stream = entryStreamKernel.getStream();
					Kernel kernel = entryStreamKernel.getKernel();
					print(1, "printf(\"kernel %d submetido no stream %d ocupa %.1f Kb / bloco (bloco 0 -> SM ", kernel.getId(), stream, (float) kernel.getSharedMemory() / 1024);
					print(0, "%d | bloco 1 -> SM %d)\\n\", ");
					print(0, "h_sm[%d][%d], h_sm[%d][%d]);", kernel.getId() - 1, 0, kernel.getId() - 1, 1);
					println(0, "");
				}
			}
		}
		println(1, "for (int i = 0; i < %d; i++) { cudaStreamDestroy(streams[i]); }", numberOfStream);
		if (smDebug) {
			println(1, "for (int i = 0; i < %d; i++) {", kernels.size());
			println(2, "cudaFree ( d_sm[i] );");
			println(2, "free ( h_sm[i] );");
			println(1, "}");
		}
		println(0, "}");
	}

	public void printDestroyStream(int streamIndex) {
		println(1, "cudaStreamDestroy(streams[%d]);", streamIndex);
	}
}
