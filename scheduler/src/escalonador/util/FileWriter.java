package escalonador.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter extends OutputStream implements Closeable {

	private RandomAccessFile file;

	public static String suffix = "";

	public static String prefix = "out/";

	public FileWriter(String filename) throws IOException {
		Path path = Paths.get(prefix + suffix, filename);
		path.getParent().toFile().mkdirs();
		this.file = new RandomAccessFile(path.toFile(), "rw");
		this.file.setLength(0);
	}

	public static File file(String filename) {
		return Paths.get(prefix + suffix, filename).toFile();
	}

	public FileWriter print(String text) throws IOException {
		file.write(text.getBytes("utf-8"));
		return this;
	}

	public FileWriter printf(Object format, Object... objects) throws IOException {
		if (objects != null && objects.length > 0) {
			format = String.format(format.toString(), objects);
		}
		file.write(format.toString().getBytes("utf-8"));
		return this;
	}

	public FileWriter printfln(Object format, Object... objects) throws IOException {
		return printf(format + "\n", objects);
	}

	public FileWriter println(String text) throws IOException {
		return print(text + "\n");
	}

	public FileWriter println() throws IOException {
		return print("\n");
	}

	@Override
	public void flush() throws IOException {
	}

	public void write(byte[] bytes, int off, int len) throws IOException {
		file.write(bytes, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		file.write(b);
	}

	@Override
	public void write(int c) throws IOException {
		file.write(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		file.close();
	}
}
