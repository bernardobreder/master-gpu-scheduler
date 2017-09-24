package escalonador.algorithm.knapsack;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import escalonador.Log;

public class IODynamicKnapsackTest {

	@Test
	public void test1() {
		KnapsackResource[] weights = KnapsackResource.array(1, 2, 5, 6, 7);
		float[] values = new float[] { 1, 6, 18, 22, 28 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new IODynamicKnapsack(weights, capacity, values, 1024 * 1024).execute();
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
		int[] result = new IODynamicKnapsack(weights, capacity, values, 1024 * 1024).execute();
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

}
