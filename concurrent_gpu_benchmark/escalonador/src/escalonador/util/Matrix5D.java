package escalonador.util;

import java.util.Arrays;

public class Matrix5D {

	private final double[] cells;

	private final int xs;

	private final int ys;

	private final int zs;

	private final int ws;

	private final int is;

	private final int size;

	public Matrix5D(int xs, int ys, int zs, int ws, int is) {
		if ((long) xs * (long) ys * zs * ws * is > Integer.MAX_VALUE) {
			throw new OutOfMemoryError("size: " + ((long) xs * (long) ys * zs * ws));
		}
		this.xs = xs;
		this.ys = ys;
		this.zs = zs;
		this.ws = ws;
		this.is = is;
		this.size = xs * ys * zs * ws * is;
		this.cells = new double[size];
	}

	public double get(int x, int y, int z, int w, int i) {
		int index = index(x, y, z, w, i);
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z, w, i }));
		}
		return cells[index];
	}

	public void set(int x, int y, int z, int w, int i, double value) {
		int index = index(x, y, z, w, i);
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Arrays.toString(new int[] { x, y, z, w, i }));
		}
		cells[index] = value;
	}

	protected int index(int x, int y, int z, int w, int i) {
		return x + y * xs + xs * ys * z + xs * ys * zs * w + xs * ys * zs * ws * i;
	}

	/**
	 * Retorna
	 * 
	 * @return xs
	 */
	public int getXs() {
		return xs;
	}

	/**
	 * Retorna
	 * 
	 * @return ys
	 */
	public int getYs() {
		return ys;
	}

	/**
	 * Retorna
	 * 
	 * @return zs
	 */
	public int getZs() {
		return zs;
	}

	/**
	 * Retorna
	 * 
	 * @return ws
	 */
	public int getWs() {
		return ws;
	}

	/**
	 * Retorna
	 * 
	 * @return is
	 */
	public int getIs() {
		return is;
	}

	/**
	 * Retorna
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}
}
