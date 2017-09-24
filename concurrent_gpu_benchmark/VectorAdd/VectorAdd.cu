
#include <cuda.h>
#include <cuda_runtime.h>

__global__ void addKernel01(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel02(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel03(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel04(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel05(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[i] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel06(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel07(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[48 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel08(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[32 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel09(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[40 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel10(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[16 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}

__global__ void addKernel11(int *c, int *a, int *b, int repeat)
{
	__shared__ unsigned char s[8 * 1024];
	int i = threadIdx.x;
	int j = i;
	for (int n = 0; n < repeat; n++) s[i % 64] = 1;
	for (int n = 0; n < repeat; n++) c[j] = a[i] + b[i] + s[i % 64];
}