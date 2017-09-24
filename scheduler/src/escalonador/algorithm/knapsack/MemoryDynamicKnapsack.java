package escalonador.algorithm.knapsack;

public class MemoryDynamicKnapsack extends DynamicKnapsack {

	protected float[][] rows;

	public MemoryDynamicKnapsack(KnapsackResource[] weights, KnapsackResource capacity, float[] values) {
		super(weights, capacity, values);
		this.rows = new float[size][];
	}

	@Override
	protected void store(int index, float[] source) {
		float[] array = new float[source.length];
		System.arraycopy(source, 0, array, 0, source.length);
		rows[index] = array;
	}

	@Override
	protected void load(int index, float[] target) {
		System.arraycopy(rows[index], 0, target, 0, target.length);
	}

}
