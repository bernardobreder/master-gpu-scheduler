package escalonador.scheduler.util;

public class ResourceSet {

	private Resource[] resources;

	private int register;

	private int sharedMemory;

	private int thread;

	public ResourceSet(ResourceSet copy) {
		int length = copy.resources.length;
		this.resources = new Resource[length];
		for (int i = 0; i < length; i++) {
			this.resources[i] = new Resource(copy.resources[i]);
		}
		this.register = copy.register;
		this.sharedMemory = copy.sharedMemory;
		this.thread = copy.thread;
	}

	public ResourceSet(int smCount, Resource example) {
		this.resources = new Resource[smCount];
		for (int i = 0; i < smCount; i++) {
			resources[i] = new Resource(example.register, example.sharedMemory, example.threads);
		}
		this.register = smCount * example.register;
		this.sharedMemory = smCount * example.sharedMemory;
		this.thread = smCount * example.threads;
	}

	public int getResourceCount() {
		return resources.length;
	}

	public Resource[] getResources() {
		return resources;
	}

	public Resource getResource(int i) {
		return resources[i];
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
