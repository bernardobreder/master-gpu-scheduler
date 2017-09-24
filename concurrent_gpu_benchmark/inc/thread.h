#ifndef __CONCURRENT_GPU_BENCHMARK__
#define __CONCURRENT_GPU_BENCHMARK__

typedef void CGBThread;

CGBThread* CGBThreadNew(int(*function)(void*), void* data);

void CGBThreadFree(CGBThread* thread);

void CGBThreadJoin(CGBThread* thread);

#endif