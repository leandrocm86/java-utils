package utils;

public class ArrayList<T> extends java.util.ArrayList<T> {

	private static final long serialVersionUID = 1L;
	
	public T ultimo() {
		return super.get(super.size() - 1);
	}
	
	public void troca(int index, T element) {
		super.remove(index);
		super.add(index, element);
	}
	
	public int ultimoIndex() {
		return super.size() - 1;
	}
	
	public int penultimoIndex() {
		return super.size() - 2;
	}
	
	public void addAll(T[] array) {
		for (T elemento : array) {
			super.add(elemento);
		}
	}
}
