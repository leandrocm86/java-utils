package estruturas;

import java.util.Iterator;

public class Array<T> implements Iterable<T> {
	
	private T[] elementos;
	
	public Array(T... elementos) {
		this.elementos = elementos;
	}
	
	public int length() {
		return this.elementos != null ? this.elementos.length : 0;
	}
	
	public T i(int index) {
		return this.elementos[index];
	}
	
	public boolean contem(T objeto) {
		for (T elemento : elementos)
			if (elemento.equals(objeto))
				return true;
		return false;
	}
	
	public boolean naoContem(T objeto) {
		return !this.contem(objeto);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int index = -1;
			public boolean hasNext() {
				return index + 1 < elementos.length;
			}
			public T next() {
				return elementos[++index];
			}
		};
	}
	
	@Override
	public String toString() {
		String msg = "";
		for (int i = 0; i < length(); i++) {
			msg += this.elementos[i].toString();
			if (i < length() - 1)
				msg += "; ";
		}
		return msg;
	}

}
