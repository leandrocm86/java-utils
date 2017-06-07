package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Lista<T> implements List<T> {
	
	public enum Tipo {ARRAY, LINKED};

	private Iterador ultimoIterador;
	
	private Collection<T> colecao;
	
	private boolean podeRepetir = true;
	
	public Lista() {
		this(Tipo.ARRAY);
	}
	
	public Lista(Tipo tipo) {
		if (tipo == Tipo.ARRAY)
			this.colecao = new ArrayList<T>();
		else if (tipo == Tipo.LINKED)
			this.colecao = new LinkedList<T>();
		else this.colecao = new HashSet<T>();
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
		this.ultimoIterador.iterator.remove();
	}
	
	// -- METODOS COLLECTION COM IMPLEMENTACAO PADRAO, OU SEJA, APENAS REPASSANDO PARA A COLECAO ORIGINAL -- 
	
	/**
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		if (this.podeRepetir)
			return this.colecao.add(e);
		else {
			if (!this.colecao.contains(e))
				return this.colecao.add(e);
			else
				return false;
		}
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
		
		Iterador(Iterator<T> iterator) {
			this.iterator = iterator;
			this.index = -1;
		}
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
		@Override
		public T next() {
			this.index++;
			this.elemento = iterator.next(); 
			return this.elemento;
		}
		@Override
		public void remove() {
			this.iterator.remove();
			this.index--;
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
	
	public T buscaBinaria(T objeto, Comparator<T> comparador) {
		if (objeto.toString().equals("agudo"))
			System.out.println("aeow");
		return this.buscaBinaria(0, this.colecao.size() - 1, objeto, comparador, '-');
	}
	
	private T buscaBinaria(int min, int max, T objeto, Comparator<T> comparador, char direcao) {
		if (min > max)
			return null; // Ja percorremos toda a lista. 
		if (min == max)
			if (comparador.compare(this.get(min), objeto) == 0)
				return this.get(min);
			else
				return null;
		
		int diferenca = (int)((max - min)/2);
		int proximoIndice = direcao == '-' ? min + diferenca : max - diferenca;
		int comparacao = comparador.compare(this.get(proximoIndice), objeto);
		
		if (comparacao == 0)
			return this.get(min);
		else if (comparacao > 0)
			return buscaBinaria(min, proximoIndice - 1, objeto, comparador, '-');
		else
			return buscaBinaria(proximoIndice + 1, max, objeto, comparador, '+');
	}
}
