package estruturas;

import java.util.Iterator;

public class Array implements Iterable<Object> {
	
	private Object[] elementos;
	
	public Array(Object... elementos) {
		this.elementos = elementos;
	}
	
	public int length() {
		return this.elementos.length;
	}
	
	public boolean contem(Object objeto) {
		for (Object elemento : elementos)
			if (elemento.equals(objeto))
				return true;
		return false;
	}
	
	public boolean naoContem(Object objeto) {
		return !this.contem(objeto);
	}

	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			private int index = -1;
			public boolean hasNext() {
				return index + 1 < elementos.length;
			}
			public Object next() {
				return elementos[++index];
			}
		};
	}

}
