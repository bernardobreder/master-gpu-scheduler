package escalonador.util;

import java.util.Arrays;

public class Array5D {

	private double[] cells;

	private int as;

	private int bs;

	private int cs;

	private int ds;

	private int es;

	private int size;

	public Array5D(int as, int bs, int cs, int ds, int es) {
		if ((long) as * (long) bs * (long) cs * (long) ds * (long) es > Integer.MAX_VALUE) {
			throw new OutOfMemoryError("size: " + ((long) as * (long) bs * cs * ds));
		}
		this.as = as;
		this.bs = bs;
		this.cs = cs;
		this.ds = ds;
		this.es = es;
		this.size = as * bs * cs * ds * es;
		this.cells = new double[size];
	}

	public double get(int a, int b, int c, int d, int e) {
		int index = index(a, b, c, d, e);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { a, b, c, d, e }));
		}
		return cells[index];
	}

	public void set(int a, int b, int c, int d, int e, double value) {
		int index = index(a, b, c, d, e);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { a, b, c, d, e }));
		}
		cells[index] = value;
	}

	protected int index(int a, int b, int c, int d, int e) {
		return a + as * b + as * bs * c + as * bs * cs * d + as * bs * cs * ds * e;
	}
}
