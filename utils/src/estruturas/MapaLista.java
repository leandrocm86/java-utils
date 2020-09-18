package estruturas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * HashMap com lista (objetos com mesma chave sao agrupados em lista).
 */
public class MapaLista<S, T> implements Map<S, Lista<T>>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<S, Lista<T>> mapa;
	
	private HashMap<S, Integer> tamanhosListas = new HashMap<S, Integer>();
	
	public MapaLista() {
		mapa = new HashMap<S, Lista<T>>(); // Por default, usamos um HashMap. Pode ser trocado por TreeMap se for solicitado ordenamento.
	}
	
	public MapaLista(Map<S, Lista<T>> mapa) {
		this.mapa = mapa;
		for (Entry<S, Lista<T>> entry : mapa.entrySet()) {
			this.incrementaTamanho(entry.getKey(), entry.getValue().size());
		}
	}

	/**
	 * Adiciona o valor na lista contida na dada chave do mapa.
	 */
	public T add(S chave, T valor) {
		Lista<T> lista = mapa.get(chave);
		if (lista == null) {
			lista = new Lista<>();
			mapa.put(chave, lista);
		}
		lista.add(valor);
		this.incrementaTamanho(chave, 1);
		return valor;
	}
	
	private void incrementaTamanho(S chave, int incremento) {
		Integer tamanho = tamanhosListas.get(chave);
		if (tamanho == null)
			tamanho = 0;
		tamanhosListas.put(chave, tamanho + incremento);
	}
	
	/**
	 * Limpa a lista de uma dada chave.
	 */
	public void limpaChave(S chave) {
		mapa.put(chave, null);
		tamanhosListas.put(chave, null);
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
		for (Lista<T> lista : mapa.values())
			for (T elemento : lista)
				if (condicao.testa(elemento))
					return elemento;
		return null;
	}
	
	/**
	 * Busca o primeiro objeto de uma dada chave no map que satisfaca a condicao dada.
	 */
	public T busca(S chave, CondicaoBusca<T> condicao) {
		Lista<T> lista = mapa.get(chave);
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
		Lista<T> lista = mapa.get(chave);
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
		for (Lista<T> lista : mapa.values())
			listaTotal.addAll(lista);
		return listaTotal;
	}
	
	/**
	 * Retorna o total de elementos na estrutura, ou seja, a soma de todos elementos em cada lista.
	 */
	public int total() {
		int total = 0;
		for (int size : tamanhosListas.values())
			total += size;
		return total;
	}
	
	public void ordenar(Comparator<S> comparador) {
		TreeMap<S, Lista<T>> treeMap = new TreeMap<S, Lista<T>>(comparador);
		treeMap.putAll(this.mapa);
		this.mapa = treeMap;
	}
	
	/**
	 * Ordena o mapa decrescentemente pelos tamanhos das listas.
	 */
	public void ordenaPorTamanho() {
		ordenar(new Comparator<S>() {
			@Override
			public int compare(S arg0, S arg1) {
				int diferencaTamanho = tamanhosListas.get(arg1) - tamanhosListas.get(arg0);
				return diferencaTamanho != 0 ? diferencaTamanho : arg0.toString().compareTo(arg1.toString());
			}
		});
	}
	
	/**
	 * Retorna uma copia do mapa com apenas os N top resultados, ou o proprio mapa se o tamanho total for menor.
	 * O Mapa precisa ter sido ordenado previamente!
	 * @param tamanho - tamanho maximo do mapa a ser retornado.
	 */
	public MapaLista<S, T> top(int tamanho) {
		if (this.mapa instanceof TreeMap<?, ?>) {
			if (this.mapa.size() > tamanho) {
				TreeMap<S, Lista<T>> mapaOrdenado = (TreeMap<S, Lista<T>>) this.mapa;
				@SuppressWarnings("unchecked")
				SortedMap<S, Lista<T>> tops = mapaOrdenado.subMap(mapaOrdenado.firstKey(), (S)mapaOrdenado.keySet().toArray()[tamanho]);
				TreeMap<S, Lista<T>> treeMapCortado = new TreeMap<S, Lista<T>>(mapaOrdenado.comparator());
				treeMapCortado.putAll(tops);
				return new MapaLista<S, T>(treeMapCortado);
			}
			else {
				return this;
			}
		}
		else
			throw new IllegalStateException("Pedindo top " + tamanho + " resultos de mapa que nao foi ordenado!");
	}
	
	public Lista<S> getListaChaves() {
		return new Lista<S>(mapa.keySet());
	}

	@Override
	public void clear() {
		mapa.clear();
		tamanhosListas.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return mapa.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return mapa.containsValue(arg0);
	}

	@Override
	public Set<Entry<S, Lista<T>>> entrySet() {
		return mapa.entrySet();
	}

	@Override
	public Lista<T> get(Object arg0) {
		Lista<T> retorno = mapa.get(arg0);
		// Tratamento para algumas vezes em que o Get nao parece funcionar bem (culpa do TreeMap?).
		if (retorno == null) {
			for (Entry<S, Lista<T>> entry : this.entrySet())
				if (entry.getKey().equals(arg0))
					return entry.getValue();
		}
		return retorno;
	}
	
	public T getPrimeiro(S chave) {
		Lista<T> elementos = this.get(chave);
		if (elementos != null && elementos.naoVazia())
			return elementos.primeiro();
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		return mapa.isEmpty();
	}

	@Override
	public Set<S> keySet() {
		return mapa.keySet();
	}
	
	@Override
	public Lista<T> put(S arg0, Lista<T> arg1) {
		incrementaTamanho(arg0, arg1.size());
		return mapa.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends S, ? extends Lista<T>> arg0) {
		mapa.putAll(arg0);
		for (S chave : arg0.keySet())
			incrementaTamanho(chave, arg0.get(chave).size());
	}

	/**
	 * Esse remove precisa de retrabalho (esta considerando remocao de lista inteira, o que provavelmente sera inutil). Nao esquecer de decrementar o hash com tamanhos das listas.
	 */
	@Override
	public Lista<T> remove(Object arg0) {
		return mapa.remove(arg0);
	}

	@Override
	public int size() {
		return mapa.size();
	}

	@Override
	public Collection<Lista<T>> values() {
		return mapa.values();
	}
	
	public int getTamanho(S chave) {
		return tamanhosListas.get(chave) != null ? tamanhosListas.get(chave) : 0;
	}
	
	@Override
	public String toString() {
		return this.mapa.toString();
	}
	
	public S primeiroIndice() {
		if (this.mapa instanceof TreeMap<?, ?>)
			return ((TreeMap<S, Lista<T>>)this.mapa).firstKey();
		else
			throw new IllegalStateException("Solicitando primeiro indice de mapa nao ordenado!");
	}
	
	public S ultimoIndice() {
		if (this.mapa instanceof TreeMap<?, ?>)
			return ((TreeMap<S, Lista<T>>)this.mapa).lastKey();
		else
			throw new IllegalStateException("Solicitando primeiro indice de mapa nao ordenado!");
	}
}
