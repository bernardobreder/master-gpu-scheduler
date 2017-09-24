package escalonador.algorithm.knapsack;

import java.util.Arrays;

public class KnapsackIterator {

	protected final KnapsackResource resource;

	protected final int[] iteratorArray;

	protected int iteratorIndex;

	protected boolean finished;

	protected final int[] indexs;

	public KnapsackIterator(KnapsackResource capacity) {
		this(capacity.values, capacity.steps);
	}

	public KnapsackIterator(int[] values, int[] steps) {
		this.resource = new KnapsackResource(values, steps);
		this.iteratorArray = new int[values.length];
		this.indexs = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			int value = 1;
			for (int j = i + 1; j < values.length; j++) {
				value *= values[j] / steps[j] + 1;
			}
			this.indexs[i] = value;
		}
		this.finished = resource.size == 0;
	}

	public KnapsackIterator plus(KnapsackResource values) {
		iteratorIndex = 0;
		for (int i = 0; i < iteratorArray.length; i++) {
			iteratorArray[i] += values.values[i];
			iteratorIndex += iteratorArray[i] * indexs[i] / resource.steps[i];
		}
		return this;
	}

	public KnapsackIterator minus(KnapsackResource values) {
		iteratorIndex = 0;
		for (int i = 0; i < iteratorArray.length; i++) {
			iteratorArray[i] -= values.values[i];
			iteratorIndex += iteratorArray[i] * indexs[i] / resource.steps[i];
		}
		return this;
	}

	public int minusIndex(KnapsackResource values) {
		int value = 0;
		for (int i = 0; i < iteratorArray.length; i++) {
			value += (iteratorArray[i] - values.values[i]) * indexs[i] / resource.steps[i];
		}
		return value;
	}

	public KnapsackIterator goTo(int... array) {
		iteratorIndex = 0;
		for (int i = 0; i < iteratorArray.length; i++) {
			iteratorArray[i] = array[i];
			iteratorIndex += array[i] * indexs[i] / resource.steps[i];
		}
		return this;
	}

	public int toIndex() {
		return iteratorIndex;
	}

	public void toVector(int index, int[] idxs) {
		if (idxs.length != resource.values.length) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < idxs.length; i++) {
			int value = index / indexs[i];
			idxs[i] = value * resource.steps[i];
			index -= value * indexs[i];
		}
	}

	public KnapsackResource createCurrentResource() {
		return new KnapsackResource(iteratorArray, resource.steps);
	}

	public int[] current() {
		return iteratorArray;
	}

	public boolean contains(KnapsackResource capacity) {
		for (int i = 0; i < iteratorArray.length; i++) {
			if (iteratorArray[i] < capacity.values[i]) {
				return false;
			}
		}
		return true;
	}

	public KnapsackIterator begin() {
		for (int i = 0; i < iteratorArray.length; i++) {
			iteratorArray[i] = 0;
		}
		this.iteratorIndex = 0;
		this.finished = resource.size == 0;
		return this;
	}

	public KnapsackIterator end() {
		for (int i = 0; i < iteratorArray.length; i++) {
			iteratorArray[i] = resource.values[i];
		}
		this.iteratorIndex = resource.size;
		this.finished = resource.size == 0;
		return this;
	}

	public boolean finished() {
		return finished;
	}

	public boolean hasResource() {
		for (int i = 0; i < iteratorArray.length; i++) {
			if (iteratorArray[i] > 0) {
				return true;
			}
		}
		return false;
	}

	public KnapsackIterator next() {
		finished = ++iteratorIndex == resource.size;
		if (!finished) {
			for (int i = indexs.length - 1; i >= 0; i--) {
				iteratorArray[i] += resource.steps[i];
				if (iteratorArray[i] > resource.values[i]) {
					iteratorArray[i] = 0;
				} else {
					break;
				}
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return Arrays.toString(iteratorArray) + "/" + Arrays.toString(resource.values) + "/" + Arrays.toString(resource.steps);
	}

}
