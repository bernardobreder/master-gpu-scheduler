package escalonador.scheduler;

public class Resource {

	public int register;

	public int sharedMemory;

	public int threads;

	public Resource(Resource copy) {
		this(copy.register, copy.sharedMemory, copy.threads);
	}

	public Resource(int register, int sharedMemory, int threads) {
		super();
		this.register = register;
		this.sharedMemory = sharedMemory;
		this.threads = threads;
	}

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public int getSharedMemory() {
		return sharedMemory;
	}

	public void setSharedMemory(int sharedMemory) {
		this.sharedMemory = sharedMemory;
	}

	public int getThread() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public void incRegister(int count) {
		this.register += count;
	}

	public void incSharedMemory(int count) {
		this.sharedMemory += count;
	}

	public void incThreads(int count) {
		this.threads += count;
	}

	public void decRegister(int count) {
		this.register -= count;
	}

	public void decSharedMemory(int count) {
		this.sharedMemory -= count;
	}

	public void decThreads(int count) {
		this.threads -= count;
	}

	public void dec(Kernel kernel) {
		decRegister(kernel.getRegister());
		decSharedMemory(kernel.getSharedMemory());
		decThreads(kernel.getThreads());
	}

	public void inc(Kernel kernel) {
		incRegister(kernel.getRegister());
		incSharedMemory(kernel.getSharedMemory());
		incThreads(kernel.getThreads());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Resource [register=%d, sharedMemory=%.1fKB, threads=%d]", register, (float) sharedMemory / 1024, threads);
	}

	public boolean has(Kernel kernel) {
		return register >= kernel.getRegister() && sharedMemory >= kernel.getSharedMemory() && threads >= kernel.getThreads();
	}
}
