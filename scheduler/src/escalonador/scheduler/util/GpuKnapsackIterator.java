package escalonador.scheduler.util;

import escalonador.algorithm.knapsack.KnapsackIterator;
import escalonador.algorithm.knapsack.KnapsackResource;

public class GpuKnapsackIterator extends KnapsackIterator {

	public GpuKnapsackIterator(int[] values, int[] steps) {
		super(values, steps);
	}

	public GpuKnapsackIterator(KnapsackResource capacity) {
		super(capacity);
	}

	@Override
	public GpuKnapsackIterator plus(KnapsackResource values) {
		super.plus(values);
		return this;
	}

	@Override
	public GpuKnapsackIterator minus(KnapsackResource values) {
		super.minus(values);
		return this;
	}

	@Override
	public GpuKnapsackIterator goTo(int... array) {
		super.goTo(array);
		return this;
	}

	@Override
	public GpuKnapsackResource createCurrentResource() {
		return new GpuKnapsackResource(super.createCurrentResource());
	}

	@Override
	public GpuKnapsackIterator begin() {
		super.begin();
		return this;
	}

	@Override
	public GpuKnapsackIterator end() {
		super.end();
		return this;
	}

	@Override
	public GpuKnapsackIterator next() {
		super.next();
		return this;
	}

}
