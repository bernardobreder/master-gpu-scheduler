package escalonador.scheduler;

public class Kernel implements Comparable<Kernel> {

	private final int id;

	private final int register;

	private final int sharedMemory;

	private final int blocks;

	private final int threads;

	private final int time;

	private final double value;

	public Kernel(Resource kernelGlobal, int id, int register, int sharedMemory, int blocks, int threads, int time) {
		this.id = id;
		this.register = register;
		this.sharedMemory = sharedMemory;
		this.blocks = blocks;
		this.threads = threads;
		this.time = time;
		this.value = getValue(kernelGlobal, register, sharedMemory, blocks, threads, time);
	}

	protected static double getValue(Resource kernelGlobal, int register, int sharedMemory, int blocks, int threads, int time) {
		double value = 0.0;
		if (register > 0) {
			value += (double) kernelGlobal.register / (double) register;
		}
		if (sharedMemory > 0) {
			value += (double) sharedMemory / (double) kernelGlobal.sharedMemory;
		}
		if (threads > 0) {
			value += (double) threads / (double) kernelGlobal.threads;
		}
		return value / time;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the register
	 */
	public int getRegister() {
		return register;
	}

	/**
	 * @return the sharedMemory
	 */
	public int getSharedMemory() {
		return sharedMemory;
	}

	/**
	 * @return the blocks
	 */
	public int getBlocks() {
		return blocks;
	}

	/**
	 * @return the threads
	 */
	public int getThreads() {
		return threads;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	public String getName() {
		return "kernel_" + getId() + "_" + getSharedMemory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Kernel o) {
		return getId() - o.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Kernel [id=%d, register=%d, sharedMemory=%.1fKB, blocks=%d, threads=%d, time=%.1fK, value=%.3f]", getId(), getRegister(), (float) getSharedMemory() / 1024, getBlocks(), getThreads(), (float) getTime() / 1024, getValue() * 1024 * 1024);
	}
}
