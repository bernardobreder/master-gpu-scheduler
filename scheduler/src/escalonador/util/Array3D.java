package escalonador.util;

import java.util.Arrays;

public class Array3D {

	private double[] cells;

	private int xs;

	private int ys;

	private int zs;

	private int size;

	public Array3D(int xs, int ys, int zs) {
		if ((long) xs * (long) ys * zs > Integer.MAX_VALUE) {
			throw new OutOfMemoryError("size: " + ((long) xs * (long) ys * zs));
		}
		this.xs = xs;
		this.ys = ys;
		this.zs = zs;
		this.size = xs * ys * zs;
		this.cells = new double[size];
	}

	public double get(int x, int y, int z) {
		int index = index(x, y, z);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z }));
		}
		return cells[index];
	}

	public void set(int x, int y, int z, double value) {
		int index = index(x, y, z);
		if (index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z }));
		}
		cells[index] = value;
	}

	protected int index(int x, int y, int z) {
		return x + y * xs + xs * ys * z;
	}
}
