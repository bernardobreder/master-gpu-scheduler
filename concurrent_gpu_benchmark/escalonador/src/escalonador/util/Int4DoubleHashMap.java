package escalonador.util;

import java.util.TreeMap;

/**
 * Implementa��o do {@link TreeMap} com chave de inteiro. Esse implementa��o
 * otimiza muito o uso de mem�ria e cpu.
 * 
 * @author Tecgraf
 */
public class Int4DoubleHashMap {

	private Node[] table;

	private int count;

	private int threshold;

	private final float loadFactor;

	public Int4DoubleHashMap(int initialCapacity, float loadFactor) {
		this.loadFactor = loadFactor;
		this.threshold = (int) (initialCapacity * loadFactor);
		this.table = new Node[initialCapacity];
	}

	public void put(int key1, int key2, int key3, int key4, double value) {
		int index = hash(key1, key2, key3, key4);
		for (Node e = table[index], prev = null; e != null; e = e.next) {
			if (e.hash == index && e.key1 == key1 && e.key2 == key2 && e.key3 == key3 && e.key4 == key4) {
				if (table[index] != e) {
					prev.next = e.next;
					e.next = table[index];
					table[index] = e;
				}
				e.value = value;
				return;
			}
			prev = e;
		}
		if (++count >= threshold) {
			rehash();
			index = hash(key1, key2, key3, key4);
		}
		table[index] = new Node(key1, key2, key3, key4, index, value, table[index]);
	}

	public void putAll(double value) {
		for (int index = 0; index < table.length; index++) {
			for (Node e = table[index]; e != null; e = e.next) {
				e.value = value;
			}
		}
	}

	public double get(int key1, int key2, int key3, int key4) {
		int index = hash(key1, key2, key3, key4);
		for (Node e = table[index], prev = null; e != null; e = e.next) {
			if (e.hash == index && e.key1 == key1 && e.key2 == key2 && e.key3 == key3 && e.key4 == key4) {
				if (table[index] != e) {
					prev.next = e.next;
					e.next = table[index];
					table[index] = e;
				}
				return e.value;
			}
			prev = e;
		}
		return 0.0;
	}

	/**
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param key4
	 * @return hash
	 */
	protected int hash(int key1, int key2, int key3, int key4) {
		int h = 1;
		h = 7919 * h + key1;
		h = 7907 * h + key2;
		h = 7901 * h + key3;
		h = 7883 * h + key4;
		return Math.abs(h % table.length);
	}

	protected void rehash() {
		int oldCapacity = table.length;
		Node oldMap[] = table;
		int newCapacity = oldCapacity + oldCapacity / 2 + 1;
		Node newMap[] = new Node[newCapacity];
		threshold = (int) (newCapacity * loadFactor);
		table = newMap;
		for (int i = oldCapacity; i-- > 0;) {
			for (Node old = oldMap[i]; old != null;) {
				Node e = old;
				old = old.next;
				int index = hash(e.key1, e.key2, e.key3, e.key4);
				e.next = newMap[index];
				newMap[index] = e;
			}
		}
	}

	public void clear(int x, int y, int z, int w) {
		put(x, y, z, w, 0.0);
	}

	protected static class Node {

		private final int key1;

		private final int key2;

		private final int key3;

		private final int key4;

		private final int hash;

		private double value;

		private Node next;

		public Node(int key1, int key2, int key3, int key4, int hash, double value) {
			this(key1, key2, key3, key4, hash, value, null);
		}

		public Node(int key1, int key2, int key3, int key4, int hash, double value, Node next) {
			super();
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.key4 = key4;
			this.hash = hash;
			this.value = value;
			this.next = next;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + hash;
			result = prime * result + key1;
			result = prime * result + key2;
			result = prime * result + key3;
			result = prime * result + key4;
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			long temp;
			temp = Double.doubleToLongBits(value);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return "[" + key1 + ", " + key2 + ", " + key3 + ", " + key4 + "]";
		}
	}
}
