package escalonador.algorithm.knapsack;

import java.util.Arrays;

public abstract class DynamicKnapsack {

	protected final KnapsackResource[] weights;

	protected final KnapsackResource capacity;

	protected final float[] values;

	protected final int size;

	public DynamicKnapsack(KnapsackResource[] weights, KnapsackResource capacity, float[] values) {
		if (weights.length != values.length) {
			throw new IllegalArgumentException();
		}
		this.weights = weights;
		this.capacity = capacity;
		this.values = values;
		this.size = weights.length;
	}

	public int[] execute() {
		int resultMax = 0;
		int[] result = new int[size];
		int capacitySize = capacity.size();
		float[] prev = new float[capacitySize];
		float[] curr = new float[capacitySize];
		KnapsackIterator resource = new KnapsackIterator(capacity);
		{
			storeStarted();
			for (int i = 1; i <= size; i++) {
				resource.begin().next();
				for (int w = 1; !resource.finished(); w++, resource.next()) {
					float oldValue = prev[w];
					curr[w] = oldValue;
					if (resource.contains(weights[i - 1])) {
						float newValue = prev[resource.minusIndex(weights[i - 1])] + values[i - 1];
						if (newValue > oldValue) {
							curr[w] = newValue;
						}
					}
				}
				store(i - 1, curr);
				System.arraycopy(curr, 0, prev, 0, capacitySize);
			}
			storeFinished();
		}
		{
			loadStarted();
			resource.end();
			for (int i = size - 1, w = capacitySize - 1; i >= 0 && w > 0; i--) {
				System.arraycopy(prev, 0, curr, 0, capacitySize);
				if (i == 0) {
					Arrays.fill(prev, 0.0f);
				} else {
					load(i - 1, prev);
				}
				if (prev[w] != curr[w]) {
					w = resource.minus(weights[i]).toIndex();
					result[resultMax++] = i;
				}
			}
			loadFinished();
		}
		return Arrays.copyOf(result, resultMax);
	}

	protected void storeStarted() {
	}

	protected void storeFinished() {
	}

	protected void loadStarted() {
	}

	protected void loadFinished() {
	}

	protected abstract void store(int index, float[] source);

	protected abstract void load(int index, float[] target);

}
