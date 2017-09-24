package escalonador.util;

import java.util.Arrays;

public class Array4D {

	private double[] cells;

	private int xs;

	private int ys;

	private int zs;

	private int ws;

	private int size;

	public Array4D(int xs, int ys, int zs, int ws) {
		if ((long) xs * (long) ys * zs * ws > Integer.MAX_VALUE) {
			throw new OutOfMemoryError("size: " + ((long) xs * (long) ys * zs * ws));
		}
		this.xs = xs;
		this.ys = ys;
		this.zs = zs;
		this.ws = ws;
		this.size = xs * ys * zs * ws;
		this.cells = new double[size];
	}

	public double get(int x, int y, int z, int w) {
		int index = index(x, y, z, w);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z, w }));
		}
		return cells[index];
	}

	public void set(int x, int y, int z, int w, double value) {
		int index = index(x, y, z, w);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z, w }));
		}
		cells[index] = value;
	}

	protected int index(int x, int y, int z, int w) {
		return x + y * xs + xs * ys * z + xs * ys * zs * w;
	}
}
