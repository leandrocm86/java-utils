package estruturas;

import java.util.HashMap;

/**
 * HashMap com lista (objetos com mesma chave sao agrupados em lista).
 */
public class MapaLista<S, T> extends HashMap<S, Lista<T>> {

	private static final long serialVersionUID = 1L;
	
	private int totalElementos = 0;
	
	/**
	 * Adiciona o valor na lista contida na dada chave do mapa.
	 */
	public T add(S chave, T valor) {
		Lista<T> lista = super.get(chave);
		if (lista == null) {
			lista = new Lista<>();
			super.put(chave, lista);
		}
		lista.add(valor);
		this.totalElementos++;
		return valor;
	}
	
	/**
	 * Limpa a lista de uma dada chave.
	 */
	public void limpaChave(S chave) {
		super.put(chave, null);
	}
	
	public interface CondicaoBusca<T> {
		boolean testa(T elem);
	}
	
	/**
	 * Busca um elemento em todo o map (procura em cada lista).
	 */
	public T busca(Object valor) {
		return this.busca(elem -> valor.equals(elem));
	}
	
	/**
	 * Busca o primeiro objeto que encontrar em todo o map que satisfaca a condicao dada.
	 */
	public T busca(CondicaoBusca<T> condicao) {
		for (Lista<T> lista : super.values())
			for (T elemento : lista)
				if (condicao.testa(elemento))
					return elemento;
		return null;
	}
	
	/**
	 * Busca o primeiro objeto de uma dada chave no map que satisfaca a condicao dada.
	 */
	public T busca(S chave, CondicaoBusca<T> condicao) {
		Lista<T> lista = super.get(chave);
		if (lista != null) {
			for (T elemento : lista)
				if (condicao.testa(elemento)) {
					return elemento;
				}
		}
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
	
	/**
	 * Retorna uma lista com todos os elementos no mapa, ie, todos elementos de cada lista do mapa juntos.
	 */
	public Lista<T> valores() {
		Lista<T> listaTotal = new Lista<T>();
		for (Lista<T> lista : super.values())
			listaTotal.addAll(lista);
		return listaTotal;
	}
	
	/**
	 * Retorna o total de elementos na estrutura, ou seja, a soma de todos elementos em cada lista.
	 */
	public int total() {
		return this.totalElementos;
	}
}
