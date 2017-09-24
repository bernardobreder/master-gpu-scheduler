package escalonador.scheduler;

public class ResourceSet {

	private Resource[] resources;

	private int smIndex;

	private int register;

	private int sharedMemory;

	private int thread;

	public ResourceSet(ResourceSet copy) {
		int length = copy.resources.length;
		this.resources = new Resource[length];
		for (int i = 0; i < length; i++) {
			this.resources[i] = new Resource(copy.resources[i]);
		}
		this.smIndex = copy.smIndex;
		this.register = copy.register;
		this.sharedMemory = copy.sharedMemory;
		this.thread = copy.thread;
	}

	public ResourceSet(int smCount, Resource example) {
		this.resources = new Resource[smCount];
		for (int i = 0; i < smCount; i++) {
			resources[i] = new Resource(example.register, example.sharedMemory, example.threads);
		}
		this.register = example.register;
		this.sharedMemory = example.sharedMemory;
		this.thread = example.threads;
	}

	public int getResourceCount() {
		return resources.length;
	}

	/**
	 * @return the smIndex
	 */
	public int getSmIndex() {
		return smIndex;
	}

	/**
	 * @param smIndex
	 *            the smIndex to set
	 */
	public void setSmIndex(int smIndex) {
		this.smIndex = smIndex;
	}

	public int incSmIndex() {
		return smIndex++ % resources.length;
	}

	public Resource[] getResources() {
		return resources;
	}

	public int getRegister() {
		return register;
	}

	public int getSharedMemory() {
		return sharedMemory;
	}

	public int getThread() {
		return thread;
	}

}
