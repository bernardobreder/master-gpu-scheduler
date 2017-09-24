package escalonador.kernel;

public abstract class AbstractCode {

	private StringBuilder sb = new StringBuilder();

	public void println(int level, String format, Object... objects) {
		print(level, format + "\n", objects);
	}

	public void print(int level, String format, Object... objects) {
		for (int n = 0; n < level; n++) {
			format = '\t' + format;
		}
		print(format, objects);
	}

	public void print(String format, Object... objects) {
		if (objects == null || objects.length == 0) {
			sb.append(format);
		} else {
			sb.append(String.format(format, objects));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return sb.toString();
	}
}
