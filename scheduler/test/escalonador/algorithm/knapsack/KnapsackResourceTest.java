package escalonador.algorithm.knapsack;

import java.util.Arrays;

import org.junit.Test;

import escalonador.Log;

public class KnapsackResourceTest {

	@Test
	public void test1() {
		Log.println(new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 })).goTo(1, 3, 2).toIndex());
		int[] i = new int[3];
		new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 })).toVector(37, i);
		Log.println(Arrays.toString(i));
	}

	@Test
	public void test2() {
		KnapsackIterator res = new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 }));
		while (!res.finished()) {
			Log.println(res);
			res.next();
		}
	}

	@Test
	public void test3() {
		KnapsackIterator res = new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 }));
		while (!res.finished()) {
			Log.println(Arrays.toString(res.current()) + " - " + res.toIndex());
			res.next();
		}
	}

	@Test
	public void test4() {
		KnapsackIterator res = new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 }));
		int[] i = new int[3];
		while (!res.finished()) {
			res.toVector(res.toIndex(), i);
			Log.println(Arrays.toString(i));
			res.next();
		}
	}

	@Test
	public void test5() {
		Log.println(new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 })).goTo(1, 3, 1).toIndex());
		Log.println(new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 2 })).goTo(1, 3, 2).toIndex());
		Log.println(new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 1 })).goTo(1, 1, 2).toIndex());
		Log.println(new KnapsackIterator(new KnapsackResource(new int[] { 2, 4, 3 }, new int[] { 1, 2, 1 })).goTo(1, 2, 2).toIndex());
		int[] i = new int[3];
		new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 2 })).toVector(22, i);
		Log.println(Arrays.toString(i));
		new KnapsackIterator(new KnapsackResource(new int[] { 2, 4, 3 }, new int[] { 1, 2, 1 })).toVector(18, i);
		Log.println(Arrays.toString(i));
	}

	@Test
	public void test6() {
		KnapsackIterator res = new KnapsackIterator(new KnapsackResource(new int[] { 2, 3, 4 }, new int[] { 1, 1, 2 }));
		while (!res.finished()) {
			Log.println(Arrays.toString(res.current()) + " - " + res.toIndex());
			res.next();
		}
	}

	@Test
	public void test7() {
		KnapsackResource capacity = new KnapsackResource(new int[] { 0, 96 * 1024, 2 * 1024 }, new int[] { 1, 1024, 32 });
		for (KnapsackIterator res = new KnapsackIterator(capacity).begin(); !res.finished(); res.next()) {
			Log.println(Arrays.toString(res.current()) + " - " + res.toIndex());
		}
	}

	@Test
	public void test8() {
		KnapsackResource capacity = new KnapsackResource(new int[] { 0, 96 * 1024, 0 }, new int[] { 1, 1024, 1 });
		for (KnapsackIterator res = new KnapsackIterator(capacity).begin(); !res.finished(); res.next()) {
			Log.println(Arrays.toString(res.current()) + " - " + res.toIndex());
		}
	}

}
