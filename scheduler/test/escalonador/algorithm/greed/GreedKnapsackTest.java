package escalonador.algorithm.greed;

import java.util.Arrays;

import org.junit.Test;

import escalonador.Log;
import escalonador.algorithm.knapsack.KnapsackResource;

public class GreedKnapsackTest {

	@Test
	public void test() {
		KnapsackResource[] weights = KnapsackResource.array(1, 2, 5, 6, 7);
		float[] values = new float[] { 1, 6, 18, 22, 28 };
		KnapsackResource capacity = new KnapsackResource(new int[] { 11 }, new int[] { 1 });
		long time = System.currentTimeMillis();
		int[] result = new GreedKnapsack(weights, capacity, values).execute();
		time = System.currentTimeMillis() - time;
		Log.println(Arrays.toString(result) + " in " + time + " milisegs");
		int weightTotal = 0;
		for (int r = 0; r < result.length; r++) {
			weightTotal += weights[result[r]].value(0);
			Log.println(String.format("Element %d with Weight %d", result[r] + 1, weights[result[r]].value(0)));
		}
		Log.println(String.format("Total with Weight %d", weightTotal));
	}

}
