package escalonador.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter implements Closeable {

	private RandomAccessFile file;

	public static String suffix = "";

	public static String prefix = "out";

	public FileWriter(String filename) throws IOException {
		Path path = Paths.get(prefix + suffix, filename);
		path.getParent().toFile().mkdirs();
		this.file = new RandomAccessFile(path.toFile(), "rw");
		this.file.setLength(0);
	}

	public FileWriter print(String text) throws IOException {
		file.write(text.getBytes("utf-8"));
		return this;
	}

	public FileWriter println(String text) throws IOException {
		return print(text + "\n");
	}

	public FileWriter println() throws IOException {
		return print("\n");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		file.close();
	}
}
