package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Lista<T> implements List<T> {

	private Iterator<T> ultimoIterador;
	
	private List<T> lista;
	
	public Lista() {
		this(false);
	}
	
	public Lista(boolean linked) {
		if (linked)
			this.lista = new LinkedList<>();
		else this.lista = new ArrayList<>();
	}
	
	public Lista(T elementoInicial) {
		this(false, elementoInicial);
	}
	
	public Lista(boolean linked, T elementoInicial) {
		this(linked);
		lista.add(elementoInicial);
	}
	
	public T ultimo() {
		return lista.get(lista.size() - 1);
	}
	
	public void troca(int index, T element) {
		lista.remove(index);
		lista.add(index, element);
	}
	
	public int ultimoIndex() {
		return lista.size() - 1;
	}
	
	public int penultimoIndex() {
		return lista.size() - 2;
	}
	
	public void addAll(T[] array) {
		for (T elemento : array) {
			lista.add(elemento);
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		// Quando o Iterator for solicitado, por exemplo implicidamente no For aprimorado (1.5),
		// precisamos guardar sua referencia para mantermos o controle da iteracao e podermos
		// remover o elemento correto quando for solicitado.
		this.ultimoIterador = lista.iterator();
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
		return this.lista.add(e);
	}

	/**
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		return this.lista.addAll(c);
	}

	/**
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		this.lista.clear();
	}

	/**
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return this.lista.contains(o);
	}

	/**
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return this.lista.containsAll(c);
	}

	/**
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.lista.isEmpty();
	}

	/**
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return this.lista.remove(o);
	}

	/**
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return this.lista.removeAll(c);
	}

	/**
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return this.lista.retainAll(c);
	}

	/**
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return this.lista.size();
	}

	/**
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return this.lista.toArray();
	}

	/**
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return this.lista.toArray(a);
	}
	
	// -- METODOS LIST COM IMPLEMENTACAO PADRAO, OU SEJA, APENAS REPASSANDO PARA A LISTA ORIGINAL --
	
	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, T element) {
		((List<T>) this.lista).add(index, element);
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return ((List<T>) this.lista).addAll(index, c);
	}

	/**
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		return ((List<T>) this.lista).get(index);
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return ((List<T>) this.lista).indexOf(o);
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return ((List<T>) this.lista).lastIndexOf(o);
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<T> listIterator() {
		return ((List<T>) this.lista).listIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		return ((List<T>) this.lista).listIterator(index);
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	@Override
	public T remove(int index) {
		return ((List<T>) this.lista).remove(index);
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		return ((List<T>) this.lista).set(index, element);
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return ((List<T>) this.lista).subList(fromIndex, toIndex);
	}
}
