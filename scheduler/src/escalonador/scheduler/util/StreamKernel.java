package escalonador.scheduler.util;

import escalonador.kernel.Kernel;

public class StreamKernel {

	private final int stream;

	private final Kernel kernel;

	public StreamKernel(int streamIndex, Kernel kernel) {
		super();
		this.stream = streamIndex;
		this.kernel = kernel;
	}

	public int getStream() {
		return stream;
	}

	public Kernel getKernel() {
		return kernel;
	}

	@Override
	public String toString() {
		return "[stream=" + stream + ", kernel=" + kernel + "]";
	}

}
