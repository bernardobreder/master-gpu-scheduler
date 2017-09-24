package escalonador.scheduler.dynamic;

import java.io.IOException;
import java.util.List;

import escalonador.kernel.Kernel;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;

public class ResourceDynamicScheduler extends DynamicScheduler {

	public ResourceDynamicScheduler(ResourceSet resourceSet, List<Kernel> kernelList, Resource kernelUnit, int smCount, int queueLimit, int blockCount, int kernelCount) throws IOException {
		super(resourceSet, kernelList, kernelUnit, smCount, queueLimit, blockCount, kernelCount);
	}

	protected float value(Kernel kernel) {
		float value = 0.0f;
		float register = smResource.getRegister();
		float sharedMemory = smResource.getSharedMemory();
		float thread = smResource.getThread();
		float smCount = this.smCount;
		if (register > 0) {
			float kernelRegister = kernel.getRegister();
			value += kernelRegister / register / smCount;
		}
		if (sharedMemory > 0) {
			float kernelSharedMemory = kernel.getSharedMemory();
			value += kernelSharedMemory / sharedMemory / smCount;
		}
		if (thread > 0) {
			float kernelThread = kernel.getThread();
			value += kernelThread / thread;
		}
		value = value / 3;
		return value;
	}

}
