package escalonador.algorithm.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import escalonador.Log;
import escalonador.algorithm.scheduler.OrderScheduler.SchedulerProcess;

public class OrderSchedulerTest {

	private Random random = new Random(0);

	@Test
	@Ignore
	public void test() {
		List<SchedulerProcess> processes = new ArrayList<>();
		processes.add(new SchedulerProcess(0, 25, 2));
		processes.add(new SchedulerProcess(1, 50, 2));
		processes.add(new SchedulerProcess(2, 75, 4));
		processes.add(new SchedulerProcess(3, 25, 5));
		Collections.sort(processes);
		OrderScheduler scheduler = new OrderScheduler(processes, 100);
		Log.println(scheduler.executeAll());
	}

	@Test
	public void testBig() {
		for (int i = 1; i < 128; i++) {
			int size = i;
			List<SchedulerProcess> processes = new ArrayList<>(size);
			for (int j = 0; j < size; j++) {
				int weight = (random.nextInt(10) + 1) * 10;
				int time = random.nextInt(10) + 1;
				SchedulerProcess process = new SchedulerProcess(j, weight, time);
				processes.add(process);
				// Log.println(process);
			}
			Collections.sort(processes);
			long time = System.currentTimeMillis();
			new OrderScheduler(processes, 100).executeAll();
			// Log.println((System.currentTimeMillis() - time));
		}
	}

}
