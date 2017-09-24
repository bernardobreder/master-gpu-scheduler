package escalonador.util;

import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {

	protected byte[] buf;

	protected int pos;

	protected int size;

	public ByteArrayInputStream(byte[] buf) {
		this(buf, buf.length);
	}

	public ByteArrayInputStream(byte[] buf, int size) {
		this.buf = buf;
		this.pos = 0;
		this.size = size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() {
		return (pos < size) ? (buf[pos++] & 0xff) : -1;
	}

	@Override
	public final int read(byte[] b, int off, int len) {
		if (pos >= size) {
			return -1;
		}
		if ((pos + len) > size) {
			len = (size - pos);
		}
		System.arraycopy(buf, pos, b, off, len);
		pos += len;
		return len;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long skip(long n) {
		long k = size - pos;
		if (n < k) {
			k = n < 0 ? 0 : n;
		}
		pos += k;
		return k;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int available() {
		return size - pos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean markSupported() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
	}
}