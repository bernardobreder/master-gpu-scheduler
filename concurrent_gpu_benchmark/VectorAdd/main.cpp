
#include <stdio.h>
#include <cuda.h>
#include <cuda_runtime.h>
#include <cuda_profiler_api.h>

void addKernel01(int *c, int *a, int *b, int repeat);
void addKernel02(int *c, int *a, int *b, int repeat);
void addKernel03(int *c, int *a, int *b, int repeat);
void addKernel04(int *c, int *a, int *b, int repeat);
void addKernel05(int *c, int *a, int *b, int repeat);
void addKernel06(int *c, int *a, int *b, int repeat);
void addKernel07(int *c, int *a, int *b, int repeat);
void addKernel08(int *c, int *a, int *b, int repeat);
void addKernel09(int *c, int *a, int *b, int repeat);
void addKernel10(int *c, int *a, int *b, int repeat);
void addKernel11(int *c, int *a, int *b, int repeat);

int main() {
	cudaError_t cudaStatus = cudaSuccess;
	int size = 128 * 1024;
	int nStreams = 9;
	int nRepeat = 32 * 1024;
	int streamSize = size / nStreams;
	
	int* hostMemory = nullptr;
	if ((cudaStatus = cudaHostAlloc(&hostMemory, 3 * size * sizeof(int), cudaHostAllocMapped)) != cudaSuccess) {
		fprintf(stderr, "cudaStreamCreate failed!");
		goto Error;
	}
	int* a = ((int*)hostMemory) + 0 * size;
	int* b = ((int*)hostMemory) + 1 * size;
	int* c = ((int*)hostMemory) + 2 * size;

	for (int i = 0; i < size; i++) {
		a[i] = i + 1;
		b[i] = (i + 1) * 10;
		c[i] = 0;
	}

	cudaStream_t* streams = (cudaStream_t*)malloc(nStreams * sizeof(cudaStream_t));
	
	for (int i = 0; i < nStreams; ++i) streams[i] = NULL;

	if ((cudaStatus = cudaSetDevice(0)) != cudaSuccess) {
		fprintf(stderr, "cudaSetDevice failed!  Do you have a CUDA-capable GPU installed?");
		goto Error;
	}

	for (int i = 0; i < nStreams; i++) {
		if ((cudaStatus = cudaStreamCreate(&streams[i])) != cudaSuccess) {
			fprintf(stderr, "cudaStreamCreate failed!");
			goto Error;
		}
	}

	int* dev_c = 0;
	if ((cudaStatus = cudaMalloc(&dev_c, size * sizeof(int))) != cudaSuccess) {
		fprintf(stderr, "cudaMalloc failed!");
		goto Error;
	}

	int* dev_a = 0;
	if ((cudaStatus = cudaMalloc(&dev_a, size * sizeof(int))) != cudaSuccess) {
		fprintf(stderr, "cudaMalloc failed!");
		goto Error;
	}

	int* dev_b = 0;
	if ((cudaStatus = cudaMalloc(&dev_a, size * sizeof(int))) != cudaSuccess) {
		fprintf(stderr, "cudaMalloc failed!");
		goto Error;
	}

	for (int i = 0; i < nStreams; i++) {
		int offset = i * streamSize;

		if ((cudaStatus = cudaMemcpyAsync(dev_a, &a[offset], streamSize * sizeof(int), cudaMemcpyHostToDevice, streams[i])) != cudaSuccess) {
			fprintf(stderr, "cudaMemcpy failed!");
			goto Error;
		}

		if ((cudaStatus = cudaMemcpyAsync(dev_b, &b[offset], streamSize * sizeof(int), cudaMemcpyHostToDevice, streams[i])) != cudaSuccess) {
			fprintf(stderr, "cudaMemcpy failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel01, grid, block, args, 0, streams[0])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	} 

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel02, grid, block, args, 0, streams[1])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	} 

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel03, grid, block, args, 0, streams[2])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel04, grid, block, args, 0, streams[3])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel05, grid, block, args, 0, streams[4])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel06, grid, block, args, 0, streams[5])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel07, grid, block, args, 0, streams[6])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		{
			dim3 grid = { 2, 1, 1 };
			dim3 block = { 32, 1, 1 };
			void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
			if ((cudaStatus = cudaLaunchKernel(addKernel08, grid, block, args, 0, streams[7])) != cudaSuccess) {
				fprintf(stderr, "cudaLaunchKernel failed!");
				goto Error;
			}

		}
		{
			dim3 grid = { 2, 1, 1 };
			dim3 block = { 32, 1, 1 };
			void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
			if ((cudaStatus = cudaLaunchKernel(addKernel09, grid, block, args, 0, streams[8])) != cudaSuccess) {
				fprintf(stderr, "cudaLaunchKernel failed!");
				goto Error;
			}

		}
	}

	for (int i = 0; i < nStreams; i++) {
		int offset = i * streamSize;
		cudaStreamSynchronize(streams[i]);
		if ((cudaStatus = cudaMemcpyAsync(&c[offset], dev_c, streamSize * sizeof(int), cudaMemcpyDeviceToHost, streams[i])) != cudaSuccess) {
			fprintf(stderr, "cudaMemcpy failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel01, grid, block, args, 0, streams[0])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel02, grid, block, args, 0, streams[1])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel03, grid, block, args, 0, streams[2])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel04, grid, block, args, 0, streams[3])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	} 

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel05, grid, block, args, 0, streams[4])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel06, grid, block, args, 0, streams[5])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
		if ((cudaStatus = cudaLaunchKernel(addKernel07, grid, block, args, 0, streams[6])) != cudaSuccess) {
			fprintf(stderr, "cudaLaunchKernel failed!");
			goto Error;
		}
	}

	{
		{
			dim3 grid = { 2, 1, 1 };
			dim3 block = { 32, 1, 1 };
			void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
			if ((cudaStatus = cudaLaunchKernel(addKernel10, grid, block, args, 0, streams[7])) != cudaSuccess) {
				fprintf(stderr, "cudaLaunchKernel failed!");
				goto Error;
			}

		}
		{
			dim3 grid = { 2, 1, 1 };
			dim3 block = { 32, 1, 1 };
			void* args[] = { &dev_c, &dev_a, &dev_b, (void**)&nRepeat };
			if ((cudaStatus = cudaLaunchKernel(addKernel11, grid, block, args, 0, streams[8])) != cudaSuccess) {
				fprintf(stderr, "cudaLaunchKernel failed!");
				goto Error;
			}

		}
	}

	for (int i = 0; i < nStreams; i++) {
		int offset = i * streamSize;
		cudaStreamSynchronize(streams[i]);
		if ((cudaStatus = cudaMemcpyAsync(&c[offset], dev_c, streamSize * sizeof(int), cudaMemcpyDeviceToHost, streams[i])) != cudaSuccess) {
			fprintf(stderr, "cudaMemcpy failed!");
			goto Error;
		}
	}

	cudaProfilerStop();

Error:
	for (int i = 0; streams && i < nStreams && streams[i]; ++i) {
		cudaStreamDestroy(streams[i]);
	}
	if (streams) cudaFreeHost(streams);

	cudaFree(dev_c);
	cudaFree(dev_a);
	cudaFree(dev_b);
	cudaFreeHost(hostMemory);
	return cudaStatus;
}