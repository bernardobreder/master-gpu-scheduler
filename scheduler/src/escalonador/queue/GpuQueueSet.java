package escalonador.queue;

public class GpuQueueSet {

	private final GpuQueue[] queues;

	public GpuQueueSet(int count) {
		queues = new GpuQueue[count];
		for (int i = 0; i < count; i++) {
			queues[i] = new GpuQueue();
		}
	}

	public int getQueueCount() {
		return queues.length;
	}

	public GpuQueue get(int index) {
		return queues[index];
	}

	public int findEarlyQueue() {
		int result = 0;
		for (int i = 1; i < queues.length; i++) {
			if (queues[i].getTimeCount() < queues[result].getTimeCount()) {
				result = i;
			}
		}
		return result;
	}

	public int findEarlyQueueWithKernel() {
		int result = -1;
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < queues.length; i++) {
			GpuQueue queue = queues[i];
			int timeCount = queue.getTimeCount();
			if (queue.getKernelCount() > 0 && timeCount < time) {
				result = i;
				time = timeCount;
			}
		}
		if (result < 0) {
			throw new IllegalStateException();
		}
		return result;
	}

}
