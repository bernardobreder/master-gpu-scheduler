package escalonador.profile;

import escalonador.kernel.Kernel;

public class KernelExecution {

	private Kernel kernel;

	private double start;

	private double duration;

	public KernelExecution(Kernel kernel, double start, double duration) {
		super();
		this.kernel = kernel;
		this.start = start;
		this.duration = duration;
	}

	/**
	 * Retorna
	 * 
	 * @return kernel
	 */
	public Kernel getKernel() {
		return kernel;
	}

	/**
	 * @param kernel
	 */
	public void setKernel(Kernel kernel) {
		this.kernel = kernel;
	}

	/**
	 * Retorna
	 * 
	 * @return start
	 */
	public double getStart() {
		return start;
	}

	/**
	 * @param start
	 */
	public void setStart(double start) {
		this.start = start;
	}

	/**
	 * Retorna
	 * 
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		return kernel.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KernelExecution other = (KernelExecution) obj;
		if (kernel == null) {
			if (other.kernel != null)
				return false;
		} else if (!kernel.equals(other.kernel))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[kernel=" + kernel + ", start=" + start + ", duration=" + duration + "]";
	}

}
