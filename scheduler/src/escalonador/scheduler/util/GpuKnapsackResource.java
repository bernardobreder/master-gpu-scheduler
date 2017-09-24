package escalonador.scheduler.util;

import escalonador.algorithm.knapsack.KnapsackResource;

public class GpuKnapsackResource extends KnapsackResource {

	public GpuKnapsackResource(int... values) {
		super(values);
	}

	public GpuKnapsackResource(int[] values, int[] steps) {
		super(values, steps);
	}

	public GpuKnapsackResource(KnapsackResource data) {
		super(data);
	}

	public int getRegister() {
		return values[0];
	}

	public int getSharedMemory() {
		return values[1];
	}

	public int getThread() {
		return values[2];
	}

	public GpuKnapsackResource setRegister(int value) {
		values[0] = value;return this;
	}

	public GpuKnapsackResource setSharedMemory(int value) {
		values[1] = value;return this;
	}

	public GpuKnapsackResource setThread(int value) {
		values[2] = value;return this;
	}

}
