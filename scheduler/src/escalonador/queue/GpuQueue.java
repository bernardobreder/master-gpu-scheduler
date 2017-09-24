package escalonador.queue;

import java.util.LinkedList;

import escalonador.kernel.Kernel;

public class GpuQueue {

	private int timeCount;

	private LinkedList<Kernel> kernels = new LinkedList<>();

	@Override
	public String toString() {
		return "GpuQueue [timeCount=" + getTimeCount() + ", kernels=" + kernels + "]";
	}

	public void add(Kernel kernel) {
		timeCount = getTimeCount() + kernel.getTime();
		kernels.add(kernel);
	}

	public Kernel getLast() {
		return kernels.isEmpty() ? null : kernels.getLast();
	}

	public Kernel dequeue() {
		if (kernels.isEmpty()) {
			throw new IllegalStateException();
		}
		return kernels.removeLast();
	}

	public int getTimeCount() {
		return timeCount;
	}

	public int getKernelCount() {
		return kernels.size();
	}

}