package escalonador.kernel;

import escalonador.scheduler.util.Resource;

public class Kernel implements Comparable<Kernel> {

	private final int id;

	private int register;

	private int sharedMemory;

	private int blocks;

	private int thread;

	private int time;

	private double value;

	public Kernel(Resource kernelGlobal, int id, int register, int sharedMemory, int blocks, int threads, int time) {
		this.id = id;
		this.register = register;
		this.sharedMemory = sharedMemory;
		this.blocks = blocks;
		this.thread = threads;
		this.time = time;
		this.value = getValue(kernelGlobal, register, sharedMemory, blocks, threads, time);
	}

	public Kernel(Kernel data) {
		this.id = data.id;
		this.register = data.register;
		this.sharedMemory = data.sharedMemory;
		this.blocks = data.blocks;
		this.thread = data.thread;
		this.time = data.time;
		this.value = data.value;
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
	public int getThread() {
		return thread;
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
		return String.format("kernel_%04d_sm%02dk_block%02d_weight%04d", id, sharedMemory / 1024, blocks, (int) ((float) sharedMemory * blocks / 1024));
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public void setSharedMemory(int sharedMemory) {
		this.sharedMemory = sharedMemory;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public void setThread(int threads) {
		this.thread = threads;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kernel other = (Kernel) obj;
		if (id != other.id)
			return false;
		return true;
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
		return String.format("Kernel [id=%d, register=%d, sharedMemory=%.1fKB, blocks=%d, threads=%d, time=%.1fK, value=%.3f]", getId(), getRegister(), (float) getSharedMemory() / 1024, getBlocks(), getThread(), (float) getTime() / 1024, getValue() * 1024 * 1024);
	}

}
