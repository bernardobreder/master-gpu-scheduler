package escalonador.util;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Implementa��o do {@link TreeMap} com chave de inteiro. Esse implementa��o
 * otimiza muito o uso de mem�ria e cpu.
 * 
 * @author Tecgraf
 * @param <V>
 */
public class Int4TreeMap<V> {

	/** Cor Red */
	protected static final boolean RED = false;
	/** Cor Black */
	protected static final boolean BLACK = true;
	/** Root */
	protected Int4TreeMapEntry<V> root = null;
	/** Tamanho */
	protected int size = 0;

	/**
	 * @return tamanho do mapa
	 */
	public int size() {
		return size;
	}

	/**
	 * Retorna o valor de uma chave
	 * 
	 * @param key1
	 * @param key2
	 * @param key4
	 * @param key5
	 * @return valor da chave
	 */
	public V get(int key1, int key2, int key4, int key5) {
		Int4TreeMapEntry<V> p = getEntry(key1, key2, key4, key5);
		return (p == null ? null : p.value);
	}

	/**
	 * Associa uma chave a um valor
	 * 
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param key4
	 * @param value
	 * @return valor antigo
	 */
	public V put(int key1, int key2, int key3, int key4, V value) {
		Int4TreeMapEntry<V> t = root;
		if (t == null) {
			root = new Int4TreeMapEntry<V>(key1, key2, key3, key4, value, null);
			size = 1;
			return null;
		}
		int cmp;
		Int4TreeMapEntry<V> parent;
		do {
			parent = t;
			cmp = key1 - t.key1;
			if (cmp == 0) {
				cmp = key2 - t.key2;
				if (cmp == 0) {
					cmp = key3 - t.key3;
					if (cmp == 0) {
						cmp = key4 - t.key4;
						if (cmp == 0) {
							return t.value = value;
						} else if (cmp < 0) {
							t = t.left;
						} else if (cmp > 0) {
							t = t.right;
						}
					} else if (cmp < 0) {
						t = t.left;
					} else if (cmp > 0) {
						t = t.right;
					}
				} else if (cmp < 0) {
					t = t.left;
				} else if (cmp > 0) {
					t = t.right;
				}
			} else if (cmp < 0) {
				t = t.left;
			} else if (cmp > 0) {
				t = t.right;
			}
		} while (t != null);
		Int4TreeMapEntry<V> e = new Int4TreeMapEntry<V>(key1, key2, key3, key4, value, parent);
		if (cmp < 0) {
			parent.left = e;
		} else {
			parent.right = e;
		}
		fixAfterInsertion(e);
		size++;
		return null;
	}

	/**
	 * Remove todos os elementos
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Remove um elemento do mapa
	 * 
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param key4
	 * @return elemento removido
	 */
	public V remove(int key1, int key2, int key3, int key4) {
		Int4TreeMapEntry<V> p = getEntry(key1, key2, key3, key4);
		if (p == null) {
			return null;
		}
		V oldValue = p.value;
		deleteEntry(p);
		return oldValue;
	}

	/**
	 * Retorna o primeiro elemento
	 * 
	 * @return elemento
	 */
	protected Int4TreeMapEntry<V> getFirstEntry() {
		Int4TreeMapEntry<V> p = root;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
		}
		return p;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		if (root != null) {
			sb.append(toString(root));
		}
		sb.append('}');
		return sb.toString();
	}

	/**
	 * @param node
	 * @return string
	 */
	public String toString(Int4TreeMapEntry<V> node) {
		StringBuilder sb = new StringBuilder();
		String left = node.left == null ? "" : toString(node.left);
		String right = node.right == null ? "" : toString(node.right);
		sb.append(left);
		if (left.length() > 0) {
			sb.append(',');
		}
		String nodeString = node.toString();
		if (nodeString.length() > 0) {
			sb.append(nodeString);
		}
		if (right.length() > 0) {
			sb.append(',');
		}
		sb.append(right);
		return sb.toString();
	}

	/**
	 * Retorna a entidade de uma chave
	 * 
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param key4
	 * @return entry entidade da chave ou nulo
	 */
	protected Int4TreeMapEntry<V> getEntry(int key1, int key2, int key3, int key4) {
		Int4TreeMapEntry<V> p = root;
		while (p != null) {
			int cmp = key1 - p.key1;
			if (cmp == 0) {
				cmp = key2 - p.key2;
				if (cmp == 0) {
					cmp = key3 - p.key3;
					if (cmp == 0) {
						cmp = key4 - p.key4;
						if (cmp == 0) {
							return p;
						} else if (cmp < 0) {
							p = p.left;
						} else if (cmp > 0) {
							p = p.right;
						}
					} else if (cmp < 0) {
						p = p.left;
					} else if (cmp > 0) {
						p = p.right;
					}
				} else if (cmp < 0) {
					p = p.left;
				} else if (cmp > 0) {
					p = p.right;
				}
			} else if (cmp < 0) {
				p = p.left;
			} else if (cmp > 0) {
				p = p.right;
			}
		}
		return null;
	}

	/**
	 * Delete node p, and then rebalance the tree.
	 * 
	 * @param p
	 */
	protected void deleteEntry(Int4TreeMapEntry<V> p) {
		size--;
		if (p.left != null && p.right != null) {
			Int4TreeMapEntry<V> s = successor(p);
			p.key1 = s.key1;
			p.key2 = s.key2;
			p.key3 = s.key3;
			p.key4 = s.key4;
			p.value = s.value;
			p = s;
		}
		Int4TreeMapEntry<V> replacement = (p.left != null ? p.left : p.right);
		if (replacement != null) {
			replacement.parent = p.parent;
			if (p.parent == null) {
				root = replacement;
			} else if (p == p.parent.left) {
				p.parent.left = replacement;
			} else {
				p.parent.right = replacement;
			}
			p.left = p.right = p.parent = null;
			if (p.color == BLACK) {
				fixAfterDeletion(replacement);
			}
		} else if (p.parent == null) {
			root = null;
		} else {
			if (p.color == BLACK) {
				fixAfterDeletion(p);
			}
			if (p.parent != null) {
				if (p == p.parent.left) {
					p.parent.left = null;
				} else if (p == p.parent.right) {
					p.parent.right = null;
				}
				p.parent = null;
			}
		}
	}

	/**
	 * From CLR
	 * 
	 * @param x
	 */
	protected void fixAfterInsertion(Int4TreeMapEntry<V> x) {
		x.color = RED;
		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Int4TreeMapEntry<V> y = rightOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Int4TreeMapEntry<V> y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}

	/**
	 * From CLR
	 * 
	 * @param x
	 */
	protected void fixAfterDeletion(Int4TreeMapEntry<V> x) {
		while (x != root && colorOf(x) == BLACK) {
			if (x == leftOf(parentOf(x))) {
				Int4TreeMapEntry<V> sib = rightOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}

				if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			} else { // symmetric
				Int4TreeMapEntry<V> sib = leftOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}

				if (colorOf(rightOf(sib)) == BLACK && colorOf(leftOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}

		setColor(x, BLACK);
	}

	/**
	 * @param p
	 * @return cor
	 */
	protected static <V> boolean colorOf(Int4TreeMapEntry<V> p) {
		return (p == null ? BLACK : p.color);
	}

	/**
	 * @param p
	 * @return pai
	 */
	protected static <V> Int4TreeMapEntry<V> parentOf(Int4TreeMapEntry<V> p) {
		return (p == null ? null : p.parent);
	}

	/**
	 * @param p
	 * @param c
	 *            cor
	 */
	protected static <V> void setColor(Int4TreeMapEntry<V> p, boolean c) {
		if (p != null) {
			p.color = c;
		}
	}

	/**
	 * @param p
	 * @return left
	 */
	protected static <V> Int4TreeMapEntry<V> leftOf(Int4TreeMapEntry<V> p) {
		return (p == null) ? null : p.left;
	}

	/**
	 * @param p
	 * @return right
	 */
	protected static <V> Int4TreeMapEntry<V> rightOf(Int4TreeMapEntry<V> p) {
		return (p == null) ? null : p.right;
	}

	/**
	 * From CLR
	 * 
	 * @param p
	 */
	protected void rotateLeft(Int4TreeMapEntry<V> p) {
		if (p != null) {
			Int4TreeMapEntry<V> r = p.right;
			p.right = r.left;
			if (r.left != null) {
				r.left.parent = p;
			}
			r.parent = p.parent;
			if (p.parent == null) {
				root = r;
			} else if (p.parent.left == p) {
				p.parent.left = r;
			} else {
				p.parent.right = r;
			}
			r.left = p;
			p.parent = r;
		}
	}

	/**
	 * From CLR
	 * 
	 * @param p
	 */
	protected void rotateRight(Int4TreeMapEntry<V> p) {
		if (p != null) {
			Int4TreeMapEntry<V> l = p.left;
			p.left = l.right;
			if (l.right != null) {
				l.right.parent = p;
			}
			l.parent = p.parent;
			if (p.parent == null) {
				root = l;
			} else if (p.parent.right == p) {
				p.parent.right = l;
			} else {
				p.parent.left = l;
			}
			l.right = p;
			p.parent = l;
		}
	}

	/**
	 * Returns the successor of the specified Entry, or null if no such.
	 * 
	 * @param t
	 * @return entry
	 */
	protected static <V> Int4TreeMapEntry<V> successor(Int4TreeMapEntry<V> t) {
		if (t == null) {
			return null;
		} else if (t.right != null) {
			Int4TreeMapEntry<V> p = t.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			Int4TreeMapEntry<V> p = t.parent;
			Int4TreeMapEntry<V> ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	/**
	 * 
	 * 
	 * @author Tecgraf
	 * @param <V>
	 */
	public static class IntTreeMapIterator<V> implements Iterator<Int4TreeMapEntry<V>> {

		/** Mapa */
		private Int4TreeMap<V> map;
		/** Root */
		private Int4TreeMapEntry<V> entry;
		/** Root */
		private Int4TreeMapEntry<V> back;

		/**
		 * @param map
		 * @param first
		 */
		public IntTreeMapIterator(Int4TreeMap<V> map, Int4TreeMapEntry<V> first) {
			this.map = map;
			this.entry = first;
			this.back = null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return this.entry != null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Int4TreeMapEntry<V> next() {
			this.back = this.entry;
			this.entry = successor(back);
			return back;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			if (back != null) {
				this.map.remove(back.key1, back.key2, back.key3, back.key4);
			}
		}

	}

	/**
	 * Node in the Tree. Doubles as a means to pass key-value pairs back to user
	 * (see Map.Entry).
	 * 
	 * @param <V>
	 */
	public static class Int4TreeMapEntry<V> {

		/** Chave */
		protected int key1;
		/** Chave */
		protected int key2;
		/** Chave */
		protected int key3;
		/** Chave */
		protected int key4;
		/** Valor */
		protected V value;
		/** Left */
		protected Int4TreeMapEntry<V> left;
		/** Right */
		protected Int4TreeMapEntry<V> right;
		/** Parent */
		protected Int4TreeMapEntry<V> parent;
		/** Cor */
		protected boolean color = BLACK;

		/**
		 * Construtor da entidade
		 * 
		 * @param a
		 * @param b
		 * @param c
		 * @param d
		 * @param value
		 * @param parent
		 */
		protected Int4TreeMapEntry(int a, int b, int c, int d, V value, Int4TreeMapEntry<V> parent) {
			this.key1 = a;
			this.key2 = b;
			this.key3 = c;
			this.key4 = d;
			this.value = value;
			this.parent = parent;
		}

		/**
		 * Retorna
		 * 
		 * @return a
		 */
		public int getKey1() {
			return key1;
		}

		/**
		 * Retorna
		 * 
		 * @return b
		 */
		public int getKey2() {
			return key2;
		}

		/**
		 * Retorna
		 * 
		 * @return c
		 */
		public int getKey3() {
			return key3;
		}

		/**
		 * Retorna
		 * 
		 * @return d
		 */
		public int getKey4() {
			return key4;
		}

		/**
		 * @return valor
		 */
		public V getValue() {
			return value;
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
