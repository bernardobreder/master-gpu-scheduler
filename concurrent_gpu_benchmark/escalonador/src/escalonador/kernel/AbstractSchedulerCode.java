package escalonador.kernel;

import java.util.List;
import java.util.Map.Entry;

import escalonador.scheduler.Kernel;

public abstract class AbstractSchedulerCode extends AbstractCode {

	int count = 0;

	public void printIncludes() {
		println(0, "#include <cuda.h>");
		println(0, "#include <cuda_runtime.h>");
		println(0, "#include <cuda_profiler_api.h>");
		println(0, "#include <stdio.h>");
		println(0, "");
	}

	public void printAsm() {
		println(0, "__device__ uint get_smid(void) {");
		println(1, "uint ret;");
		println(1, "asm(\"mov.u32 %0, %smid;\" : \"=r\"(ret) );");
		println(1, "return ret;");
		println(0, "}");
		println(0, "");
	}

	public void printCreateStreams(int numberOfStream, int numberOfKernel) {
		println(0, "int main() {");
		println(1, "int *d_sm[%d], *h_sm[%d];", numberOfKernel, numberOfKernel);
		println(1, "for (int i = 0; i < %d; i++) {", numberOfKernel);
		println(2, "cudaMalloc( (void**) &d_sm[i], 2 * sizeof(int) );");
		println(2, "h_sm[i] = (int*) malloc( 2 * sizeof(int) );");
		println(1, "}");
		println(1, "cudaStream_t streams[%d];", numberOfStream);
		println(1, "for (int i = 0; i < %d; i++) cudaStreamCreate(&streams[i]);", numberOfStream);
		println(1, "");
	}

	public void printCreateStream(int streamIndex) {
		println(1, "cudaStreamCreate(&streams[%d]);", streamIndex);
	}

	public void printSyncStream(int streamIndex) {
		println(1, "cudaStreamSynchronize(streams[%d]);", streamIndex);
	}

	public void printExecuteKernel(Kernel kernel, int streamIndex) {
		println(1, "{");
		println(2, "int repeat = %d;", kernel.getTime());
		println(2, "dim3 grid( %d, 1, 1 );", kernel.getBlocks());
		println(2, "dim3 block( 32, 1, 1 );");
		println(2, "%s<<<grid, block, 0, streams[%d]>>>(repeat, d_sm[%d]);", kernel.getName(), streamIndex, kernel.getId() - 1);
		println(1, "}");
	}

	public void printDestroyStreams(int numberOfStream, List<Kernel> kernels, List<Entry<Integer, Kernel>> initialKernels, List<List<Entry<Integer, Kernel>>> roundsStep) {
		println(1, "cudaProfilerStop();");
		println(1, "for (int i = 0; i < %d; i++) {", kernels.size());
		println(2, "cudaMemcpy(h_sm[i], d_sm[i], 2*sizeof(int), cudaMemcpyDeviceToHost);");
		println(1, "}");
		for (int i = 0; i < initialKernels.size(); i++) {
			Entry<Integer, Kernel> entryStreamKernel = initialKernels.get(i);
			int stream = entryStreamKernel.getKey();
			Kernel kernel = entryStreamKernel.getValue();
			print(1, "printf(\"kernel %d submetido no stream %d ocupa %.1f Kb / bloco (bloco 0 -> SM ", kernel.getId(), stream, (float) kernel.getSharedMemory() / 1024);
			print(0, "%d | bloco 1 -> SM %d)\\n\", ");
			print(0, "h_sm[%d][%d], h_sm[%d][%d]);", kernel.getId() - 1, 0, kernel.getId() - 1, 1);
			println(0, "");
		}
		for (int i = 0; i < roundsStep.size(); i++) {
			List<Entry<Integer, Kernel>> entryStreamKernelList = roundsStep.get(i);
			for (Entry<Integer, Kernel> entryStreamKernel : entryStreamKernelList) {
				int stream = entryStreamKernel.getKey();
				Kernel kernel = entryStreamKernel.getValue();
				print(1, "printf(\"kernel %d submetido no stream %d ocupa %.1f Kb / bloco (bloco 0 -> SM ", kernel.getId(), stream, (float) kernel.getSharedMemory() / 1024);
				print(0, "%d | bloco 1 -> SM %d)\\n\", ");
				print(0, "h_sm[%d][%d], h_sm[%d][%d]);", kernel.getId() - 1, 0, kernel.getId() - 1, 1);
				println(0, "");
			}
		}
		println(1, "for (int i = 0; i < %d; i++) {", numberOfStream);
		println(2, "cudaStreamDestroy(streams[i]);");
		println(2, "cudaFree ( d_sm[i] );");
		println(2, "free ( h_sm[i] );");
		println(1, "}");
		println(0, "}");
	}

	public void printDestroyStream(int streamIndex) {
		println(1, "cudaStreamDestroy(streams[%d]);", streamIndex);
	}
}
