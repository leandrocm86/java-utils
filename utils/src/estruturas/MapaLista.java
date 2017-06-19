package estruturas;

import java.util.HashMap;

/**
 * HashMap com lista (objetos com mesma chave sao agrupados em lista).
 */
public class MapaLista<S, T> extends HashMap<S, Lista<T>>{

	private static final long serialVersionUID = 1L;
	
	public void add(S chave, T valor) {
		Lista<T> lista = super.get(chave);
		if (lista == null) {
			lista = new Lista<>();
			super.put(chave, lista);
		}
		lista.add(valor);
	}
	
	/**
	 * Busca um objeto em todo o map (procura em cada lista).
	 */
	public T busca(Object valor) {
		for (Lista<T> lista : super.values())
			for (T elemento : lista)
				if (elemento.equals(valor))
					return elemento;
		return null;
	}
	
	/**
	 * Busca um objeto em uma lista especifica do map, atraves da chave dada.
	 */
	public T busca(S chave, Object valor) {
		Lista<T> lista = super.get(chave);
		if (lista != null) {
			for (T elemento : lista)
				if (elemento.equals(valor))
					return elemento;
		}
		return null;
	}

}
