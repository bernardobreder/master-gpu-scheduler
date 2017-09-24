package escalonador.util;

public class Array2D {

	public double[] cells;

	private int rows;

	private int columns;

	public Array2D(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.cells = new double[rows * columns];
	}

	public double get(int row, int column) {
		return cells[row * columns + column];
	}

	public void set(int row, int column, double value) {
		cells[row * columns + column] = value;
	}

	/**
	 * Retorna
	 * 
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Retorna
	 * 
	 * @return columns
	 */
	public int getColumns() {
		return columns;
	}

}
