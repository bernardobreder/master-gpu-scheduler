package escalonador.util;

public class Matrix2D {

	public int[] cells;

	private int rows;

	private int columns;

	public Matrix2D(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.cells = new int[rows * columns];
	}

	public int get(int row, int column) {
		return cells[row * columns + column];
	}

	public void set(int row, int column, int value) {
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
