package escalonador.algorithm.knapsack;

import java.util.Arrays;

public class KnapsackResource {

	protected final int[] values;

	protected final int[] steps;

	protected final int size;

	public KnapsackResource(int... values) {
		this(values, new int[] { 1 });
	}

	public KnapsackResource(int[] values, int[] steps) {
		if (values.length != steps.length) {
			throw new IllegalArgumentException();
		}
		this.values = values;
		this.steps = steps;
		int count = 1;
		for (int i = 0; i < values.length; i++) {
			count *= values[i] / steps[i] + 1;
		}
		size = count;
	}

	public KnapsackResource(KnapsackResource data) {
		this.values = data.values;
		this.steps = data.steps;
		this.size = data.size;
	}

	public static KnapsackResource[] array(int... values) {
		KnapsackResource[] array = new KnapsackResource[values.length];
		for (int i = 0; i < values.length; i++) {
			array[i] = new KnapsackResource(values[i]);
		}
		return array;
	}

	public int size() {
		return size;
	}

	public int[] getSteps() {
		return steps;
	}

	@Override
	public String toString() {
		return Arrays.toString(values) + "/" + Arrays.toString(steps);
	}

	public int value(int index) {
		return values[index];
	}

}
