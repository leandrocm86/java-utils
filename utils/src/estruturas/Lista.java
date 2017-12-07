package estruturas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Lista<T> implements List<T> {
	
	public enum Tipo {ARRAY, LINKED};

	private Iterador ultimoIterador;
	
	private Collection<T> colecao;
	
	private boolean podeRepetir = true;
	
	private Tipo tipo;
	
	private Comparator<T> comparador;
	
	public Lista() {
		this(Tipo.ARRAY);
	}
	
	public Lista(boolean podeRepetir) {
		this();
		this.podeRepetir = podeRepetir;
	}
	
	public Lista(Tipo tipo) {
		this.tipo = tipo;
		this.colecao = this.criaLista(tipo);
	}
	
	public Lista(Tipo tipo, boolean podeRepetir) {
		this(tipo);
		this.podeRepetir = podeRepetir;
	}
	
	public Lista(T elementoInicial) {
		this(Tipo.ARRAY, elementoInicial);
 	}
	
	public Lista(Tipo tipo, T elementoInicial) {
		this(tipo);
		colecao.add(elementoInicial);
	}
	
	public Lista(Collection<T> colecao) {
		this.colecao = colecao;
	}
	
	private List<T> criaLista(Tipo tipo) {
		if (tipo == Tipo.ARRAY)
			return new ArrayList<T>();
		else
			return new LinkedList<T>();
	}
	
	public boolean podeRepetir() {
		return podeRepetir;
	}

	public void setPodeRepetir(boolean podeRepetir) {
		this.podeRepetir = podeRepetir;
	}

	public T ultimo() {
		return ((List<T>)colecao).get(colecao.size() - 1);
	}
	
	public void troca(int index, T element) {
		((List<T>)colecao).set(index, element);
	}
	
	public void trocaAtual(T element) {
		this.troca(this.ultimoIterador.index, element);
	}
	
	public int ultimoIndex() {
		return colecao.size() - 1;
	}
	
	public int penultimoIndex() {
		return colecao.size() - 2;
	}
	
	public void addAll(T[] array) {
		for (T elemento : array) {
			this.add(elemento);
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		// Quando o Iterator for solicitado, por exemplo implicidamente no For aprimorado (1.5),
		// precisamos guardar sua referencia para mantermos o controle da iteracao e podermos
		// remover o elemento correto quando for solicitado.
		this.ultimoIterador = new Iterador(colecao.iterator());
		return this.ultimoIterador;
	}
	
	/**
	 * Remove o ultimo elemento iterado na Colecao.
	 */
	public void remove() {
		this.ultimoIterador.remove();
	}
	
	// -- METODOS COLLECTION COM IMPLEMENTACAO PADRAO, OU SEJA, APENAS REPASSANDO PARA A COLECAO ORIGINAL -- 
	
	/**
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		boolean retorno = false;
		if (this.podeRepetir)
			retorno = this.colecao.add(e);
		else {
			if (!this.colecao.contains(e))
				retorno =  this.colecao.add(e);
		}
		
		if (this.comparador != null)
//			long t0 = System.currentTimeMillis();
			Collections.sort((List<T>)this.colecao, comparador);
//			Log.msgLn("Ordenada lista em " + (System.currentTimeMillis() - t0));
//		}
		
		return retorno;
	}

	/**
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (this.podeRepetir)
			return this.colecao.addAll(c);
		else {
			int tamanhoInicial = this.colecao.size();
			for (T element : c)
				this.add(element);
			return tamanhoInicial != this.colecao.size();
		}
	}
	
	/**
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		this.colecao.clear();
	}

	/**
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return this.colecao.contains(o);
	}

	/**
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return this.colecao.containsAll(c);
	}

	/**
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.colecao == null || this.colecao.isEmpty();
	}

	/**
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return this.colecao.remove(o);
	}

	/**
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return this.colecao.removeAll(c);
	}

	/**
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return this.colecao.retainAll(c);
	}

	/**
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return this.colecao.size();
	}

	/**
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return this.colecao.toArray();
	}

	/**
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return this.colecao.toArray(a);
	}
	
	// -- METODOS LIST COM IMPLEMENTACAO PADRAO, OU SEJA, APENAS REPASSANDO PARA A LISTA ORIGINAL --
	
	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, T element) {
		((List<T>) this.colecao).add(index, element);
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return ((List<T>) this.colecao).addAll(index, c);
	}

	/**
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		return ((List<T>) this.colecao).get(index);
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return ((List<T>) this.colecao).indexOf(o);
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return ((List<T>) this.colecao).lastIndexOf(o);
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<T> listIterator() {
		return ((List<T>) this.colecao).listIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		return ((List<T>) this.colecao).listIterator(index);
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	@Override
	public T remove(int index) {
		return ((List<T>) this.colecao).remove(index);
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		return ((List<T>) this.colecao).set(index, element);
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return ((List<T>) this.colecao).subList(fromIndex, toIndex);
	}
	
	private class Iterador implements Iterator<T> {
		Iterator<T> iterator;
		int index;
		T elemento;
		
		Iterador(Iterator<T> iteratorOriginal) {
			iterator = iteratorOriginal;
			index = -1;
		}
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
		@Override
		public T next() {
			index++;
			elemento = iterator.next(); 
			return this.elemento;
		}
		@Override
		public void remove() {
			iterator.remove();
			index--;
		}
	}
	
	// METODOS CUSTOMIZADOS
	public boolean notEmpty() {
		return !this.isEmpty();
	}
	
	public Lista<T> primeiraMetade() {
		return new Lista<T>(this.subList(0, Math.round(this.size()/2)));
	}
	
	public Lista<T> segundaMetade() {
		return new Lista<T>(this.subList(Math.round(this.size()/2), this.size()));
	}
	
	/**
	 * Faz uma ordenação com o comparador dado e o armazena
	 * para manter a lista ordenada a cada inserção.
	 * @param comparador - Comparador a ser usado para ordenacao.
	 */
	public void manterOrdem(Comparator<T> comparador) {
//		long t0 = System.currentTimeMillis();
		Collections.sort((List<T>)this.colecao, comparador);
//		Log.msgLn("Ordenada lista em " + (System.currentTimeMillis() - t0));
		this.comparador = comparador;
	}
	
	/**
	 * A busca binaria soh eh recomendada para tipos ArrayList.
	 * Nesse caso a performance da busca eh muito alta. 
	 */
	public T buscaBinaria(T elemento, Comparator<T> comparador) {
		CondicaoBinaria<T> condicao = new CondicaoBinaria<T>() {
			public int testar(T objeto) {
				return comparador.compare(objeto, elemento);
			}
		};
		int index = this.indexBuscaBinaria(condicao);
		return index == -1 ? null : this.get(index);
	}
	
	/**
	 * Retorna o indice do dado objeto na lista, procurando binariamente.
	 * A busca binaria soh eh recomendada para tipos ArrayList.
	 * Nesse caso a performance da busca eh muito alta.
	 */
	public int indexBuscaBinaria(CondicaoBinaria<T> condicao) {
		return this.buscaBinaria(0, this.colecao.size() - 1, condicao);
	}
	
	private int buscaBinaria(int min, int max, CondicaoBinaria<T> condicao) {
		if (min > max)
			return -1; // Ja percorremos toda a lista. 
		if (min == max)
			if (condicao.testar(this.get(min)) == 0)
				return min;
			else
				return -1;
		
		int diferenca = (int)((max - min)/2);
		int proximoIndice = min + diferenca;
//		Log.msgLn("Testando indice " + proximoIndice + " (min = " + min + "; max = " + max + ")");
		int comparacao = condicao.testar(this.get(proximoIndice));
		
		if (comparacao == 0)
			return proximoIndice;
		else if (comparacao > 0)
			return buscaBinaria(min, proximoIndice-1, condicao);
		else
			return buscaBinaria(proximoIndice+1, max, condicao);
	}
	
	/**
	 * Retorna o primeiro elemento encontrado que satisfaz o predicao dado, ou nulo se nao encontrar.
	 */
	public T busca(Condicao<T> condicao) {
		for (T elemento : this.colecao)
			if (condicao.testar(elemento))
				return elemento;
		return null;
	}
	
	public Lista<T> subConjunto(Condicao<T> condicao) {
		Lista<T> subconjunto = new Lista<>(this.tipo);
		for (T elemento : this.colecao)
			if (condicao.testar(elemento))
				subconjunto.add(elemento);
		return subconjunto;
	}
	
	/**
	 * Extrai uma lista de um indice (inclusivo) ate outro (exclusivo), 
	 * podendo ser uma copia ou um espelho (apontador) para a lista original.
	 */
	public Lista<T> subLista(int inicio, int fim, boolean copia) {
		if (!copia)
			return new Lista<T>(this.subList(inicio, fim));
		
		Lista<T> subLista = new Lista<T>();
		for (int i = inicio; i < fim; i++)
			subLista.add(((List<T>)this.colecao).get(i));
		return subLista;
	}
}
