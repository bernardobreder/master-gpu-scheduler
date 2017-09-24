#include <cuda.h>
#include <cuda_runtime.h>
#include <cuda_profiler_api.h>
#include <stdio.h>

__global__ void kernel_1(int repeat) {
	__shared__ unsigned char s[12288];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_2(int repeat) {
	__shared__ unsigned char s[34816];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_3(int repeat) {
	__shared__ unsigned char s[28672];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_4(int repeat) {
	__shared__ unsigned char s[26624];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_5(int repeat) {
	__shared__ unsigned char s[40960];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_6(int repeat) {
	__shared__ unsigned char s[25600];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_7(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_8(int repeat) {
	__shared__ unsigned char s[27648];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_9(int repeat) {
	__shared__ unsigned char s[24576];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_10(int repeat) {
	__shared__ unsigned char s[4096];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_11(int repeat) {
	__shared__ unsigned char s[33792];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_12(int repeat) {
	__shared__ unsigned char s[4096];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_13(int repeat) {
	__shared__ unsigned char s[25600];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_14(int repeat) {
	__shared__ unsigned char s[20480];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_15(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_16(int repeat) {
	__shared__ unsigned char s[13312];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_17(int repeat) {
	__shared__ unsigned char s[36864];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_18(int repeat) {
	__shared__ unsigned char s[12288];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_19(int repeat) {
	__shared__ unsigned char s[25600];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_20(int repeat) {
	__shared__ unsigned char s[39936];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_21(int repeat) {
	__shared__ unsigned char s[23552];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_22(int repeat) {
	__shared__ unsigned char s[1024];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_23(int repeat) {
	__shared__ unsigned char s[21504];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_24(int repeat) {
	__shared__ unsigned char s[26624];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_25(int repeat) {
	__shared__ unsigned char s[19456];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_26(int repeat) {
	__shared__ unsigned char s[6144];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_27(int repeat) {
	__shared__ unsigned char s[35840];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_28(int repeat) {
	__shared__ unsigned char s[22528];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_29(int repeat) {
	__shared__ unsigned char s[24576];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_30(int repeat) {
	__shared__ unsigned char s[41984];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_31(int repeat) {
	__shared__ unsigned char s[5120];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_32(int repeat) {
	__shared__ unsigned char s[18432];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_33(int repeat) {
	__shared__ unsigned char s[10240];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_34(int repeat) {
	__shared__ unsigned char s[36864];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_35(int repeat) {
	__shared__ unsigned char s[30720];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_36(int repeat) {
	__shared__ unsigned char s[21504];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_37(int repeat) {
	__shared__ unsigned char s[11264];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_38(int repeat) {
	__shared__ unsigned char s[34816];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_39(int repeat) {
	__shared__ unsigned char s[28672];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_40(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_41(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_42(int repeat) {
	__shared__ unsigned char s[13312];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_43(int repeat) {
	__shared__ unsigned char s[21504];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_44(int repeat) {
	__shared__ unsigned char s[35840];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_45(int repeat) {
	__shared__ unsigned char s[23552];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_46(int repeat) {
	__shared__ unsigned char s[31744];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_47(int repeat) {
	__shared__ unsigned char s[10240];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_48(int repeat) {
	__shared__ unsigned char s[12288];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_49(int repeat) {
	__shared__ unsigned char s[34816];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_50(int repeat) {
	__shared__ unsigned char s[23552];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_51(int repeat) {
	__shared__ unsigned char s[27648];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_52(int repeat) {
	__shared__ unsigned char s[1024];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_53(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_54(int repeat) {
	__shared__ unsigned char s[16384];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_55(int repeat) {
	__shared__ unsigned char s[30720];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_56(int repeat) {
	__shared__ unsigned char s[24576];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_57(int repeat) {
	__shared__ unsigned char s[19456];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_58(int repeat) {
	__shared__ unsigned char s[27648];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_59(int repeat) {
	__shared__ unsigned char s[25600];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_60(int repeat) {
	__shared__ unsigned char s[20480];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_61(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_62(int repeat) {
	__shared__ unsigned char s[20480];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_63(int repeat) {
	__shared__ unsigned char s[27648];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_64(int repeat) {
	__shared__ unsigned char s[22528];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_65(int repeat) {
	__shared__ unsigned char s[18432];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_66(int repeat) {
	__shared__ unsigned char s[20480];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_67(int repeat) {
	__shared__ unsigned char s[18432];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_68(int repeat) {
	__shared__ unsigned char s[19456];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_69(int repeat) {
	__shared__ unsigned char s[2048];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_70(int repeat) {
	__shared__ unsigned char s[19456];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_71(int repeat) {
	__shared__ unsigned char s[40960];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_72(int repeat) {
	__shared__ unsigned char s[1024];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_73(int repeat) {
	__shared__ unsigned char s[3072];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_74(int repeat) {
	__shared__ unsigned char s[34816];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_75(int repeat) {
	__shared__ unsigned char s[38912];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_76(int repeat) {
	__shared__ unsigned char s[13312];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_77(int repeat) {
	__shared__ unsigned char s[41984];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_78(int repeat) {
	__shared__ unsigned char s[23552];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_79(int repeat) {
	__shared__ unsigned char s[26624];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_80(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_81(int repeat) {
	__shared__ unsigned char s[19456];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_82(int repeat) {
	__shared__ unsigned char s[24576];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_83(int repeat) {
	__shared__ unsigned char s[23552];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_84(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_85(int repeat) {
	__shared__ unsigned char s[7168];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_86(int repeat) {
	__shared__ unsigned char s[37888];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_87(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_88(int repeat) {
	__shared__ unsigned char s[28672];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_89(int repeat) {
	__shared__ unsigned char s[10240];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_90(int repeat) {
	__shared__ unsigned char s[40960];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_91(int repeat) {
	__shared__ unsigned char s[39936];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_92(int repeat) {
	__shared__ unsigned char s[37888];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_93(int repeat) {
	__shared__ unsigned char s[29696];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_94(int repeat) {
	__shared__ unsigned char s[14336];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_95(int repeat) {
	__shared__ unsigned char s[24576];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_96(int repeat) {
	__shared__ unsigned char s[16384];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_97(int repeat) {
	__shared__ unsigned char s[38912];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_98(int repeat) {
	__shared__ unsigned char s[17408];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_99(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_100(int repeat) {
	__shared__ unsigned char s[33792];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_101(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_102(int repeat) {
	__shared__ unsigned char s[10240];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_103(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_104(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_105(int repeat) {
	__shared__ unsigned char s[17408];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_106(int repeat) {
	__shared__ unsigned char s[32768];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_107(int repeat) {
	__shared__ unsigned char s[11264];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_108(int repeat) {
	__shared__ unsigned char s[28672];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_109(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_110(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_111(int repeat) {
	__shared__ unsigned char s[9216];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_112(int repeat) {
	__shared__ unsigned char s[34816];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_113(int repeat) {
	__shared__ unsigned char s[6144];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_114(int repeat) {
	__shared__ unsigned char s[1024];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_115(int repeat) {
	__shared__ unsigned char s[11264];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_116(int repeat) {
	__shared__ unsigned char s[17408];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_117(int repeat) {
	__shared__ unsigned char s[30720];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_118(int repeat) {
	__shared__ unsigned char s[26624];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_119(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_120(int repeat) {
	__shared__ unsigned char s[29696];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_121(int repeat) {
	__shared__ unsigned char s[30720];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_122(int repeat) {
	__shared__ unsigned char s[15360];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_123(int repeat) {
	__shared__ unsigned char s[29696];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_124(int repeat) {
	__shared__ unsigned char s[4096];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_125(int repeat) {
	__shared__ unsigned char s[6144];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_126(int repeat) {
	__shared__ unsigned char s[13312];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_127(int repeat) {
	__shared__ unsigned char s[8192];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

__global__ void kernel_128(int repeat) {
	__shared__ unsigned char s[11264];
	int i = threadIdx.x;
	s[i] = 0;
	for (int n = 0; n < 45; n++) {
		for (int n = 0; n < repeat; n++) s[i]++;
		for (int n = 0; n < repeat; n++) s[i]--;
		for (int n = 0; n < repeat; n++) s[i]++;
	}
}

int main() {
	cudaStream_t streams[128];
	for (int i = 0; i < 128; i++) cudaStreamCreate(&streams[i]);

	{
		int repeat = 33792;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_93, grid, block, args, 0, streams[0]);
	}
	{
		int repeat = 48128;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_18, grid, block, args, 0, streams[1]);
	}
	{
		int repeat = 34816;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_32, grid, block, args, 0, streams[2]);
	}
	{
		int repeat = 43008;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_29, grid, block, args, 0, streams[3]);
	}
	{
		int repeat = 36864;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_106, grid, block, args, 0, streams[4]);
	}
	{
		int repeat = 54272;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_12, grid, block, args, 0, streams[5]);
	}
	{
		int repeat = 46080;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_98, grid, block, args, 0, streams[6]);
	}
	{
		int repeat = 35840;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_13, grid, block, args, 0, streams[7]);
	}
	{
		int repeat = 78848;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_72, grid, block, args, 0, streams[8]);
	}
	{
		int repeat = 46080;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_71, grid, block, args, 0, streams[9]);
	}
	{
		int repeat = 39936;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_57, grid, block, args, 0, streams[10]);
	}
	{
		int repeat = 46080;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_25, grid, block, args, 0, streams[11]);
	}
	{
		int repeat = 64512;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_127, grid, block, args, 0, streams[12]);
	}
	{
		int repeat = 49152;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_2, grid, block, args, 0, streams[13]);
	}
	{
		int repeat = 72704;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_124, grid, block, args, 0, streams[14]);
	}
	{
		int repeat = 51200;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_112, grid, block, args, 0, streams[15]);
	}
	{
		int repeat = 45056;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_88, grid, block, args, 0, streams[16]);
	}
	{
		int repeat = 87040;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_22, grid, block, args, 0, streams[17]);
	}
	{
		int repeat = 104448;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_69, grid, block, args, 0, streams[18]);
	}
	{
		int repeat = 61440;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_15, grid, block, args, 0, streams[19]);
	}
	{
		int repeat = 163840;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_114, grid, block, args, 0, streams[20]);
	}
	{
		int repeat = 62464;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_45, grid, block, args, 0, streams[21]);
	}
	{
		int repeat = 55296;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_38, grid, block, args, 0, streams[22]);
	}
	{
		int repeat = 86016;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_10, grid, block, args, 0, streams[23]);
	}
	{
		int repeat = 92160;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_89, grid, block, args, 0, streams[24]);
	}
	{
		int repeat = 93184;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_80, grid, block, args, 0, streams[25]);
	}
	{
		int repeat = 88064;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_95, grid, block, args, 0, streams[26]);
	}
	{
		int repeat = 47104;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_24, grid, block, args, 0, streams[27]);
	}
	{
		int repeat = 102400;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_116, grid, block, args, 0, streams[28]);
	}
	{
		int repeat = 132096;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_125, grid, block, args, 0, streams[29]);
	}
	{
		int repeat = 51200;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_35, grid, block, args, 0, streams[30]);
	}
	{
		int repeat = 102400;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_66, grid, block, args, 0, streams[31]);
	}
	{
		int repeat = 63488;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_4, grid, block, args, 0, streams[32]);
	}
	{
		int repeat = 100352;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_7, grid, block, args, 0, streams[33]);
	}
	{
		int repeat = 68608;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_99, grid, block, args, 0, streams[34]);
	}
	{
		int repeat = 167936;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_52, grid, block, args, 0, streams[35]);
	}
	{
		int repeat = 75776;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_103, grid, block, args, 0, streams[36]);
	}
	{
		int repeat = 77824;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_41, grid, block, args, 0, streams[37]);
	}
	{
		int repeat = 189440;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_31, grid, block, args, 0, streams[38]);
	}
	{
		int repeat = 211968;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_73, grid, block, args, 0, streams[39]);
	}
	{
		int repeat = 75776;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_117, grid, block, args, 0, streams[40]);
	}
	{
		int repeat = 113664;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_54, grid, block, args, 0, streams[41]);
	}
	{
		int repeat = 119808;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_82, grid, block, args, 0, streams[42]);
	}
	{
		int repeat = 119808;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_19, grid, block, args, 0, streams[43]);
	}
	{
		int repeat = 159744;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_111, grid, block, args, 0, streams[44]);
	}
	{
		int repeat = 81920;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_120, grid, block, args, 0, streams[45]);
	}
	{
		int repeat = 111616;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_49, grid, block, args, 0, streams[46]);
	}
	{
		int repeat = 144384;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_44, grid, block, args, 0, streams[47]);
	}
	{
		int repeat = 92160;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_92, grid, block, args, 0, streams[48]);
	}
	{
		int repeat = 124928;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_55, grid, block, args, 0, streams[49]);
	}
	{
		int repeat = 110592;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_62, grid, block, args, 0, streams[50]);
	}
	{
		int repeat = 112640;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_126, grid, block, args, 0, streams[51]);
	}
	{
		int repeat = 103424;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_34, grid, block, args, 0, streams[52]);
	}
	{
		int repeat = 205824;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_109, grid, block, args, 0, streams[53]);
	}
	{
		int repeat = 146432;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_47, grid, block, args, 0, streams[54]);
	}
	{
		int repeat = 229376;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_26, grid, block, args, 0, streams[55]);
	}
	{
		int repeat = 130048;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_110, grid, block, args, 0, streams[56]);
	}
	{
		int repeat = 117760;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_81, grid, block, args, 0, streams[57]);
	}
	{
		int repeat = 126976;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_42, grid, block, args, 0, streams[58]);
	}
	{
		int repeat = 142336;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_21, grid, block, args, 0, streams[59]);
	}
	{
		int repeat = 240640;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_113, grid, block, args, 0, streams[60]);
	}
	{
		int repeat = 130048;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_16, grid, block, args, 0, streams[61]);
	}
	{
		int repeat = 84992;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_75, grid, block, args, 0, streams[62]);
	}
	{
		int repeat = 150528;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_115, grid, block, args, 0, streams[63]);
	}
	{
		int repeat = 129024;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_51, grid, block, args, 0, streams[64]);
	}
	{
		int repeat = 174080;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_33, grid, block, args, 0, streams[65]);
	}
	{
		int repeat = 158720;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_60, grid, block, args, 0, streams[66]);
	}
	{
		int repeat = 176128;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_64, grid, block, args, 0, streams[67]);
	}
	{
		int repeat = 156672;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_79, grid, block, args, 0, streams[68]);
	}
	{
		int repeat = 154624;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_6, grid, block, args, 0, streams[69]);
	}
	{
		int repeat = 184320;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_101, grid, block, args, 0, streams[70]);
	}
	{
		int repeat = 190464;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_107, grid, block, args, 0, streams[71]);
	}
	{
		int repeat = 183296;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_63, grid, block, args, 0, streams[72]);
	}
	{
		int repeat = 149504;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_40, grid, block, args, 0, streams[73]);
	}
	{
		int repeat = 176128;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_87, grid, block, args, 0, streams[74]);
	}
	{
		int repeat = 233472;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_85, grid, block, args, 0, streams[75]);
	}
	{
		int repeat = 183296;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_9, grid, block, args, 0, streams[76]);
	}
	{
		int repeat = 159744;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_105, grid, block, args, 0, streams[77]);
	}
	{
		int repeat = 184320;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_65, grid, block, args, 0, streams[78]);
	}
	{
		int repeat = 211968;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_102, grid, block, args, 0, streams[79]);
	}
	{
		int repeat = 187392;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_96, grid, block, args, 0, streams[80]);
	}
	{
		int repeat = 232448;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_1, grid, block, args, 0, streams[81]);
	}
	{
		int repeat = 182272;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_83, grid, block, args, 0, streams[82]);
	}
	{
		int repeat = 173056;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_23, grid, block, args, 0, streams[83]);
	}
	{
		int repeat = 188416;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_68, grid, block, args, 0, streams[84]);
	}
	{
		int repeat = 195584;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_122, grid, block, args, 0, streams[85]);
	}
	{
		int repeat = 231424;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_84, grid, block, args, 0, streams[86]);
	}
	{
		int repeat = 211968;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_118, grid, block, args, 0, streams[87]);
	}
	{
		int repeat = 231424;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_128, grid, block, args, 0, streams[88]);
	}
	{
		int repeat = 246784;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_104, grid, block, args, 0, streams[89]);
	}
	{
		int repeat = 244736;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_61, grid, block, args, 0, streams[90]);
	}
	{
		int repeat = 181248;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_36, grid, block, args, 0, streams[91]);
	}
	{
		int repeat = 209920;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_14, grid, block, args, 0, streams[92]);
	}
	{
		int repeat = 201728;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_50, grid, block, args, 0, streams[93]);
	}
	{
		int repeat = 225280;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_59, grid, block, args, 0, streams[94]);
	}
	{
		int repeat = 215040;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_78, grid, block, args, 0, streams[95]);
	}
	{
		int repeat = 216064;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_70, grid, block, args, 0, streams[96]);
	}
	{
		int repeat = 182272;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_11, grid, block, args, 0, streams[97]);
	}
	{
		int repeat = 184320;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_100, grid, block, args, 0, streams[98]);
	}
	{
		int repeat = 215040;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_94, grid, block, args, 0, streams[99]);
	}
	{
		int repeat = 199680;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_76, grid, block, args, 0, streams[100]);
	}
	{
		int repeat = 258048;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_119, grid, block, args, 0, streams[101]);
	}
	{
		int repeat = 246784;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_43, grid, block, args, 0, streams[102]);
	}
	{
		int repeat = 242688;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_67, grid, block, args, 0, streams[103]);
	}
	{
		int repeat = 242688;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_37, grid, block, args, 0, streams[104]);
	}
	{
		int repeat = 250880;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_48, grid, block, args, 0, streams[105]);
	}
	{
		int repeat = 230400;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_56, grid, block, args, 0, streams[106]);
	}
	{
		int repeat = 208896;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_46, grid, block, args, 0, streams[107]);
	}
	{
		int repeat = 203776;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_121, grid, block, args, 0, streams[108]);
	}
	{
		int repeat = 195584;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_39, grid, block, args, 0, streams[109]);
	}
	{
		int repeat = 212992;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_123, grid, block, args, 0, streams[110]);
	}
	{
		int repeat = 122880;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_17, grid, block, args, 0, streams[111]);
	}
	{
		int repeat = 195584;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_27, grid, block, args, 0, streams[112]);
	}
	{
		int repeat = 201728;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_53, grid, block, args, 0, streams[113]);
	}
	{
		int repeat = 220160;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_74, grid, block, args, 0, streams[114]);
	}
	{
		int repeat = 216064;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_3, grid, block, args, 0, streams[115]);
	}
	{
		int repeat = 236544;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_28, grid, block, args, 0, streams[116]);
	}
	{
		int repeat = 218112;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_108, grid, block, args, 0, streams[117]);
	}
	{
		int repeat = 259072;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_58, grid, block, args, 0, streams[118]);
	}
	{
		int repeat = 261120;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_8, grid, block, args, 0, streams[119]);
	}
	{
		int repeat = 95232;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_90, grid, block, args, 0, streams[120]);
	}
	{
		int repeat = 118784;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_77, grid, block, args, 0, streams[121]);
	}
	{
		int repeat = 130048;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_97, grid, block, args, 0, streams[122]);
	}
	{
		int repeat = 141312;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_5, grid, block, args, 0, streams[123]);
	}
	{
		int repeat = 175104;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_20, grid, block, args, 0, streams[124]);
	}
	{
		int repeat = 194560;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_30, grid, block, args, 0, streams[125]);
	}
	{
		int repeat = 221184;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_86, grid, block, args, 0, streams[126]);
	}
	{
		int repeat = 241664;
		dim3 grid = { 2, 1, 1 };
		dim3 block = { 32, 1, 1 };
		void* args[] = { (void**)&repeat };
		cudaLaunchKernel(kernel_91, grid, block, args, 0, streams[127]);
	}
	cudaStreamSynchronize(streams[0]);
	cudaStreamSynchronize(streams[1]);
	cudaStreamSynchronize(streams[2]);
	cudaStreamSynchronize(streams[3]);
	cudaStreamSynchronize(streams[4]);
	cudaStreamSynchronize(streams[5]);
	cudaStreamSynchronize(streams[6]);
	cudaStreamSynchronize(streams[7]);
	cudaStreamSynchronize(streams[8]);
	cudaStreamSynchronize(streams[9]);
	cudaStreamSynchronize(streams[10]);
	cudaStreamSynchronize(streams[11]);
	cudaStreamSynchronize(streams[12]);
	cudaStreamSynchronize(streams[13]);
	cudaStreamSynchronize(streams[14]);
	cudaStreamSynchronize(streams[15]);
	cudaStreamSynchronize(streams[16]);
	cudaStreamSynchronize(streams[17]);
	cudaStreamSynchronize(streams[18]);
	cudaStreamSynchronize(streams[19]);
	cudaStreamSynchronize(streams[20]);
	cudaStreamSynchronize(streams[21]);
	cudaStreamSynchronize(streams[22]);
	cudaStreamSynchronize(streams[23]);
	cudaStreamSynchronize(streams[24]);
	cudaStreamSynchronize(streams[25]);
	cudaStreamSynchronize(streams[26]);
	cudaStreamSynchronize(streams[27]);
	cudaStreamSynchronize(streams[28]);
	cudaStreamSynchronize(streams[29]);
	cudaStreamSynchronize(streams[30]);
	cudaStreamSynchronize(streams[31]);
	cudaStreamSynchronize(streams[32]);
	cudaStreamSynchronize(streams[33]);
	cudaStreamSynchronize(streams[34]);
	cudaStreamSynchronize(streams[35]);
	cudaStreamSynchronize(streams[36]);
	cudaStreamSynchronize(streams[37]);
	cudaStreamSynchronize(streams[38]);
	cudaStreamSynchronize(streams[39]);
	cudaStreamSynchronize(streams[40]);
	cudaStreamSynchronize(streams[41]);
	cudaStreamSynchronize(streams[42]);
	cudaStreamSynchronize(streams[43]);
	cudaStreamSynchronize(streams[44]);
	cudaStreamSynchronize(streams[45]);
	cudaStreamSynchronize(streams[46]);
	cudaStreamSynchronize(streams[47]);
	cudaStreamSynchronize(streams[48]);
	cudaStreamSynchronize(streams[49]);
	cudaStreamSynchronize(streams[50]);
	cudaStreamSynchronize(streams[51]);
	cudaStreamSynchronize(streams[52]);
	cudaStreamSynchronize(streams[53]);
	cudaStreamSynchronize(streams[54]);
	cudaStreamSynchronize(streams[55]);
	cudaStreamSynchronize(streams[56]);
	cudaStreamSynchronize(streams[57]);
	cudaStreamSynchronize(streams[58]);
	cudaStreamSynchronize(streams[59]);
	cudaStreamSynchronize(streams[60]);
	cudaStreamSynchronize(streams[61]);
	cudaStreamSynchronize(streams[62]);
	cudaStreamSynchronize(streams[63]);
	cudaStreamSynchronize(streams[64]);
	cudaStreamSynchronize(streams[65]);
	cudaStreamSynchronize(streams[66]);
	cudaStreamSynchronize(streams[67]);
	cudaStreamSynchronize(streams[68]);
	cudaStreamSynchronize(streams[69]);
	cudaStreamSynchronize(streams[70]);
	cudaStreamSynchronize(streams[71]);
	cudaStreamSynchronize(streams[72]);
	cudaStreamSynchronize(streams[73]);
	cudaStreamSynchronize(streams[74]);
	cudaStreamSynchronize(streams[75]);
	cudaStreamSynchronize(streams[76]);
	cudaStreamSynchronize(streams[77]);
	cudaStreamSynchronize(streams[78]);
	cudaStreamSynchronize(streams[79]);
	cudaStreamSynchronize(streams[80]);
	cudaStreamSynchronize(streams[81]);
	cudaStreamSynchronize(streams[82]);
	cudaStreamSynchronize(streams[83]);
	cudaStreamSynchronize(streams[84]);
	cudaStreamSynchronize(streams[85]);
	cudaStreamSynchronize(streams[86]);
	cudaStreamSynchronize(streams[87]);
	cudaStreamSynchronize(streams[88]);
	cudaStreamSynchronize(streams[89]);
	cudaStreamSynchronize(streams[90]);
	cudaStreamSynchronize(streams[91]);
	cudaStreamSynchronize(streams[92]);
	cudaStreamSynchronize(streams[93]);
	cudaStreamSynchronize(streams[94]);
	cudaStreamSynchronize(streams[95]);
	cudaStreamSynchronize(streams[96]);
	cudaStreamSynchronize(streams[97]);
	cudaStreamSynchronize(streams[98]);
	cudaStreamSynchronize(streams[99]);
	cudaStreamSynchronize(streams[100]);
	cudaStreamSynchronize(streams[101]);
	cudaStreamSynchronize(streams[102]);
	cudaStreamSynchronize(streams[103]);
	cudaStreamSynchronize(streams[104]);
	cudaStreamSynchronize(streams[105]);
	cudaStreamSynchronize(streams[106]);
	cudaStreamSynchronize(streams[107]);
	cudaStreamSynchronize(streams[108]);
	cudaStreamSynchronize(streams[109]);
	cudaStreamSynchronize(streams[110]);
	cudaStreamSynchronize(streams[111]);
	cudaStreamSynchronize(streams[112]);
	cudaStreamSynchronize(streams[113]);
	cudaStreamSynchronize(streams[114]);
	cudaStreamSynchronize(streams[115]);
	cudaStreamSynchronize(streams[116]);
	cudaStreamSynchronize(streams[117]);
	cudaStreamSynchronize(streams[118]);
	cudaStreamSynchronize(streams[119]);
	cudaStreamSynchronize(streams[120]);
	cudaStreamSynchronize(streams[121]);
	cudaStreamSynchronize(streams[122]);
	cudaStreamSynchronize(streams[123]);
	cudaStreamSynchronize(streams[124]);
	cudaStreamSynchronize(streams[125]);
	cudaStreamSynchronize(streams[126]);
	cudaStreamSynchronize(streams[127]);
	cudaProfilerStop();
	for (int i = 0; i < 128; i++) cudaStreamDestroy(streams[i]);
}

