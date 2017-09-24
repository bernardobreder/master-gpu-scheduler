package escalonador.algorithm.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderScheduler {

	protected final List<SchedulerProcess> processes;

	protected final int capacity;

	protected final int size;

	protected final boolean[] processUsing;

	protected final OrderScheduler[] schedulers;

	protected int time;

	public OrderScheduler(List<SchedulerProcess> processes, int capacity) {
		// TODO Processos devem estar ordenados somente na raiz da execução
		this.processes = processes;
		this.capacity = capacity;
		this.size = processes.size();
		this.processUsing = new boolean[size];
		this.schedulers = new OrderScheduler[size];
	}

	public List<List<SchedulerProcess>> executeAll() {
		execute(0);
		return queue();
	}

	int queueCount = 0;

	List<List<SchedulerProcess>> queues = new ArrayList<>();

	protected List<List<SchedulerProcess>> queue() {
		queues.add(new ArrayList<SchedulerProcess>());
		// queueIn(queues, this, 0, processes .size());
		return queues;
	}

	protected void queueIn(List<List<SchedulerProcess>> queues, OrderScheduler scheduler, int index, int size) {
		for (int i = 0, p = index; i < processes.size(); i++) {
			if (scheduler.processUsing[i]) {
				List<SchedulerProcess> queue = queues.get(index);
				if (p != index) {
					queues.add(queue = new ArrayList<SchedulerProcess>());
				}
				queue.add(scheduler.processes.get(i));
				OrderScheduler next = scheduler.schedulers[i];
				if (next != null) {
					queueIn(queues, next, i, next.processes.size());
				}
				p++;
			}
		}
	}

	protected boolean execute(int using) {
		for (int j = 0; j < processes.size(); j++) {
			time += processes.get(j).getTime();
			processUsing[j] = true;
		}
		boolean[] bitSet = new boolean[processes.size()];
		return execute(bitSet, 0, 0, using);
	}

	protected boolean execute(boolean[] processUsing, int bitSetCount, int index, int using) {
		boolean flag = false;
		if (index == size) {
			if (bitSetCount > 0) {
				return executeLeaf(processUsing, using, size);
			}
		} else {
			int weight = processes.get(index).getWeight();
			if (using + weight <= capacity) {
				processUsing[index] = true;
				flag |= execute(processUsing, bitSetCount + 1, index + 1, using + weight);
				processUsing[index] = false;
			}
			flag |= execute(processUsing, bitSetCount, index + 1, using);
		}
		return flag;
	}

	private boolean executeLeaf(boolean[] processUsing, int using, int size) {
		// Log.println(Arrays.toString(processUsing));
		// List<SchedulerProcess> currProcesses = new ArrayList<>(size);
		// List<SchedulerProcess> nextProcesses = new ArrayList<>(size);
		// for (int j = 0; j < size; j++) {
		// SchedulerProcess process = processes.get(j);
		// if (!processUsing[j]) {
		// nextProcesses.add(process);
		// } else {
		// currProcesses.add(process);
		// }
		// }
		// if (!nextProcesses.isEmpty()) {
		// for (int i = 0; i < size; i++) {
		// if (processUsing[i]) {
		// SchedulerProcess process = processes.get(i);
		// using -= process.getWeight();
		// OrderScheduler scheduler = new OrderScheduler(nextProcesses,
		// capacity);
		// if (scheduler.execute(using)) {
		// if (process.getTime() + scheduler.time < time) {
		// time = process.getTime() + scheduler.time;
		// schedulers[i] = scheduler;
		// System.arraycopy(processUsing, 0, this.processUsing, 0, size);
		// }
		// }
		// }
		// }
		// }
		return true;
	}

	public static class SchedulerProcess implements Comparable<SchedulerProcess> {

		private final int processIndex;

		private final int weight;

		private final int time;

		public SchedulerProcess(int processIndex, int weight, int time) {
			super();
			this.processIndex = processIndex;
			this.weight = weight;
			this.time = time;
		}

		public int getProcessIndex() {
			return processIndex;
		}

		public int getWeight() {
			return weight;
		}

		public int getTime() {
			return time;
		}

		@Override
		public int compareTo(SchedulerProcess o) {
			return time - o.time;
		}

		@Override
		public String toString() {
			return "SchedulerProcess [processIndex=" + processIndex + ", weight=" + weight + ", time=" + time + "]";
		}

	}

	public static class IntArray {

		private int[] array;

		private int size;

		public IntArray() {
			this(8);
		}

		public IntArray(int size) {
			this.array = new int[size];
		}

		public IntArray(int... values) {
			this(values.length);
			size = values.length;
			System.arraycopy(values, 0, array, 0, values.length);
		}

		public void add(int value) {
			if (size == array.length) {
				array = Arrays.copyOf(array, size + (size >> 1));
			}
			array[size++] = value;
		}

		public int get(int index) {
			return array[index];
		}

		public int size() {
			return size;
		}

		public void copy(int[] target) {
			System.arraycopy(array, 0, target, 0, size);
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(array, size));
		}

	}
}
