#include "thread.h"

#ifdef WIN32 

#include <windows.h>

CGBThread* CGBThreadNew(int(*function)(void*), void* data) {
	return CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)function, data, 0, 0);
}

void CGBThreadFree(CGBThread* thread) {
	CloseHandle(thread);
}

void CGBThreadJoin(CGBThread* thread) {
	WaitForSingleObject(thread, INFINITE);
}

#else

#endif