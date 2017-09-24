package escalonador.algorithm.knapsack;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import escalonador.Log;

public class MemoryDynamicKnapsackTest {

	@Test
	public void test0() {
		KnapsackResource[] weights = KnapsackResource.array(1);
		float[] values = new float[] { 1 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test01() {
		KnapsackResource[] weights = KnapsackResource.array(1, 2);
		float[] values = new float[] { 1, 2 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test1() {
		KnapsackResource[] weights = KnapsackResource.array(1, 2, 5, 6, 7);
		float[] values = new float[] { 1, 6, 18, 22, 28 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test2() {
		int[] weightArray = { 1, 2, 5, 6, 7 };
		KnapsackResource[] weights = new KnapsackResource[weightArray.length];
		for (int i = 0; i < weightArray.length; i++) {
			weights[i] = new KnapsackResource(new int[] { weightArray[i], weightArray[i] }, new int[] { 1, 1 });
		}
		float[] values = new float[] { 1, 6, 18, 22, 28 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test3() {
		int[] weightArray = { 1, 2, 5, 6, 7 };
		KnapsackResource[] weights = new KnapsackResource[weightArray.length];
		for (int i = 0; i < weightArray.length; i++) {
			weights[i] = new KnapsackResource(new int[] { 2 * weightArray[i], weightArray[i] }, new int[] { 2, 1 });
		}
		float[] values = new float[] { 1, 6, 18, 22, 28 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test4() {
		Random random = new Random(0);
		int elements = 8 * 1024 * 1024;
		KnapsackResource capacity = new KnapsackResource(16);
		KnapsackResource[] weights = new KnapsackResource[elements];
		float[] values = new float[elements];
		for (int i = 0; i < elements; i++) {
			weights[i] = new KnapsackResource(random.nextInt(1000) + 1);
			values[i] = random.nextInt(100) + 1;
		}
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Weight %d and Value %.1f", result[r] + 1, weights[result[r]].value(0), values[result[r]]));
		}
		Log.println(String.format("Total with Weight %d and Value %.1f", weightTotal, valueTotal));
	}

	@Test
	public void test5() {
		Random random = new Random(0);
		int elements = 1024;
		int streamProcessorCount = 24;
		int sharedMemoryGlobal = 96 * 1024 * streamProcessorCount;
		KnapsackResource capacity = new KnapsackResource(new int[] { 0, sharedMemoryGlobal, 0 }, new int[] { 1, 1024, 1 });
		KnapsackResource[] weights = new KnapsackResource[elements];
		float[] values = new float[elements];
		for (int i = 0; i < elements; i++) {
			int sharedMemory = (random.nextInt(48) + 1) * 1024;
			int blocks = (random.nextInt(32) + 1);
			weights[i] = new KnapsackResource(new int[] { 0, sharedMemory * blocks, 0 }, new int[] { 1, 1, 1 });
			values[i] = random.nextInt(100) + 1;
		}
		long time = System.currentTimeMillis();
		int[] result = new MemoryDynamicKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int sharedMemoryTotal = 0, threadTotal = 0;
		float valueTotal = 0;
		for (int r = 0; r < result.length; r++) {
			sharedMemoryTotal += weights[result[r]].value(1);
			threadTotal += weights[result[r]].value(2);
			valueTotal += values[result[r]];
			Log.println(String.format("Element %d with Shared Memory %d and Blocks %d with Value %.1f", result[r] + 1, weights[result[r]].value(1), weights[result[r]].value(2), values[result[r]]));
		}
		Log.println(String.format("Alloced with Shared Memory %d and Blocks %d with Value %.1f", sharedMemoryTotal, threadTotal, valueTotal));
		Log.println(String.format("Free with Shared Memory %d and Blocks %d", sharedMemoryGlobal - sharedMemoryTotal, -1));
	}

}
