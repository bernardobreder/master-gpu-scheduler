package escalonador.algorithm.greed;

import java.util.Arrays;
import java.util.Comparator;

import escalonador.algorithm.knapsack.KnapsackIterator;
import escalonador.algorithm.knapsack.KnapsackResource;

public class GreedKnapsack {

	protected final KnapsackResource[] weights;

	protected final KnapsackResource capacity;

	protected final float[] values;

	protected final Integer[] itens;

	protected final int size;

	public GreedKnapsack(KnapsackResource[] weights, KnapsackResource capacity, final float[] values) {
		this.weights = weights;
		this.capacity = capacity;
		this.values = values;
		this.size = weights.length;
		this.itens = new Integer[weights.length];
		for (int i = 0; i < itens.length; i++) {
			itens[i] = i;
		}
		Arrays.sort(itens, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return ((int) values[o2.intValue()]) - ((int) values[o1.intValue()]);
			}
		});
	}

	public int[] execute() {
		int resultMax = 0;
		int[] result = new int[size];
		KnapsackIterator resource = new KnapsackIterator(capacity).end();
		for (int i = 0; i < weights.length; i++) {
			if (resource.contains(weights[itens[i]])) {
				resource.minus(weights[itens[i]]);
				result[resultMax++] = itens[i];
			}
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

}
