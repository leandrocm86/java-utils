package estruturas;

import java.util.Comparator;

/**
 * Extrai um objeto Comparator a partir de uma classe comparavel.
 */
public class Comparador<T extends Comparable<? super T>> implements Comparator<T> {
	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
