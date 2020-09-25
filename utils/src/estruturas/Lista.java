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
	
	private List<T> lista;
	
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
		this.lista = this.criaLista(tipo);
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
		lista.add(elementoInicial);
	}
	
	public Lista(List<T> lista) {
		this.lista = lista;
	}
	
	public Lista(Collection<T> colecao) {
		this();
		this.lista.addAll(colecao);
	}
	
	public Lista(T[] array) {
		this.lista = new ArrayList<>(array.length);
		for (T elemento : array)
			this.lista.add(elemento);
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
		return lista.get(lista.size() - 1);
	}
	
	public T primeiro() {
		return lista.get(0);
	}
	
	/**
	 * Substitui um elemento no dado index por outro passado como parametro.
	 * @return Elemento que estava na posicao indicada, e que foi removido da lista para ser substituido.
	 */
	public T troca(int index, T element) {
		return lista.set(index, element);
	}
	
	public void trocaAtual(T element) {
		this.troca(this.ultimoIterador.index, element);
	}
	
	public int ultimoIndex() {
		return lista.size() - 1;
	}
	
	public int penultimoIndex() {
		return lista.size() - 2;
	}
	
	public void addAll(T[] array) {
		for (T elemento : array) {
			this.add(elemento);
		}
	}
	
	private boolean inverteProximoIterador = false;
	
	public void inverteProximoIterador() {
		this.inverteProximoIterador = true;
	}
	
	@Override
	public Iterator<T> iterator() {
		// Quando o Iterator for solicitado, por exemplo implicidamente no For aprimorado (1.5),
		// precisamos guardar sua referencia para mantermos o controle da iteracao e podermos
		// remover o elemento correto quando for solicitado.
		if (!inverteProximoIterador)
			this.ultimoIterador = new Iterador(lista.iterator());
		else {
			this.ultimoIterador = new IteradorReverso(lista.listIterator(this.size()));
			this.inverteProximoIterador = false;
		}
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
			retorno = this.lista.add(e);
		else {
			if (!this.lista.contains(e))
				retorno =  this.lista.add(e);
		}
		
		if (this.comparador != null)
			Collections.sort(this.lista, comparador);
		
		return retorno;
	}

	/**
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (this.podeRepetir)
			return this.lista.addAll(c);
		else {
			int tamanhoInicial = this.lista.size();
			for (T element : c)
				this.add(element);
			return tamanhoInicial != this.lista.size();
		}
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
		return this.lista == null || this.lista.isEmpty();
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
		this.lista.add(index, element);
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return this.lista.addAll(index, c);
	}
	
	@SafeVarargs
	public final void addAll(Collection<? extends T>... colecoes) {
		for (Collection<? extends T> colecao : colecoes)
			this.addAll(colecao);
	}

	/**
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		return this.lista.get(index);
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return this.lista.indexOf(o);
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return this.lista.lastIndexOf(o);
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<T> listIterator() {
		return this.lista.listIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		return this.lista.listIterator(index);
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	@Override
	public T remove(int index) {
		return this.lista.remove(index);
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		return this.lista.set(index, element);
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return this.lista.subList(fromIndex, toIndex);
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
	
	private class IteradorReverso extends Iterador {
		
		IteradorReverso(ListIterator<T> iteratorOriginal) {
			super(iteratorOriginal);
			index = lista.size();
		}
		
		@Override
		public boolean hasNext() {
			return ((ListIterator<T>) iterator).hasPrevious();
		}
		@Override
		public T next() {
			index--;
			elemento = (T)((ListIterator<T>) iterator).previous(); 
			return this.elemento;
		}
		@Override
		public void remove() {
			iterator.remove();
		}
	}
	
	// METODOS CUSTOMIZADOS
	public boolean naoVazia() {
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
		Collections.sort(this.lista, comparador);
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
		return this.buscaBinaria(0, this.lista.size() - 1, condicao);
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
		for (T elemento : this.lista)
			if (condicao.testar(elemento))
				return elemento;
		return null;
	}
	
	public Lista<T> subConjunto(Condicao<T> condicao) {
		Lista<T> subconjunto = new Lista<>(this.tipo);
		for (T elemento : this.lista)
			if (condicao.testar(elemento))
				subconjunto.add(elemento);
		return subconjunto;
	}
	
	/**
	 * Extrai uma lista de um indice (inclusivo) ate outro (exclusivo), 
	 * podendo ser uma copia ou um espelho (apontador) para a lista original.
	 */
	public Lista<T> subLista(int inicio, int fim, boolean copia) {
		if (!copia) {
			this.lista = this.subList(inicio, fim);
			return this;
		}
		
		Lista<T> subLista = new Lista<T>();
		for (int i = inicio; i < fim; i++)
			subLista.add(this.lista.get(i));
		return subLista;
	}
	
	/**
	 * Esse metodo difere do 'contains' de Collection, na medida que
	 * ele usa o equals dos elementos da lista recebendo o parametro buscado,
	 * enquanto o 'contains' usa o equals do objeto buscado passando os elementos da lista como parametro.
	 */
	public boolean contem(Object elemento) {
		for (T elem : this.lista)
			if (elem.equals(elemento))
				return true;
		return false;
	}
	
	/**
	 * @see #contem(Object elemento)
	 */
	public boolean naoContem(Object elemento) {
		return !this.contem(elemento);
	}
	
	/**
	 * Agrupa elementos da lista em um MapaLista, segundo o criterio passado como parametro.
	 * @param classeChave Classe da chave a ser usada no mapa (que eh a classe retornada pelo metodo no outro parametro).
	 * @param metodo (lambda) Metodo a ser executado para extrair a chave que um elemento tera no mapa.
	 */
	@SuppressWarnings("unchecked")
	public <S> MapaLista<S, T> agrupar(Class<S> classeChave, Metodo<T> metodo) {
		MapaLista<S, T> mapaLista = new MapaLista<S, T>();
		for (T elemento : this.lista)
			mapaLista.add((S)metodo.executar(elemento), elemento);
		return mapaLista;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.lista.size(); i++) {
			sb.append(this.get(i).toString());
			if (i+1 < this.lista.size())
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void inverter() {
		for (int i = this.lista.size() - 1, j = 0; i >= this.lista.size()/2; i--, j++) {
			T removido = this.troca(j, this.lista.get(i));
			this.troca(i, removido);
		}
	}

	/**
	 * Mostra o proximo elemento da iteracao, sem mover o iterador.
	 */
	public T proximo() {
		if (this.ultimoIterador != null && this.ultimoIterador.index + 1 < this.lista.size())
			return this.lista.get(this.ultimoIterador.index + 1);
		else
			return null;
	}
	
	/**
	 * Faz o iterador descartar os proximos X indices.
	 */
	public void pulaIterador(int pulos) {
		while (pulos-- > 0) {
			this.ultimoIterador.next();
		}
	}

	public int indiceIteracao() {
		return this.ultimoIterador.index;
	}
	
	public float extrairMedia(MetodoInt<T> metodo) {
		float media = 0;
		for (T item : this.lista)
			media += metodo.executar(item);
		return media / this.lista.size();
	}
	
	public int extrairMediaRedonda(MetodoInt<T> metodo) {
		return Math.round(this.extrairMedia(metodo));
	}
	
	public T max(MetodoInt<T> metodo) {
		assert(this.lista.size() > 0);
		int max = metodo.executar(lista.get(0));
		T maior = lista.get(0);
		for (T elemento : this.lista) {
			if (metodo.executar(elemento) > max) {
				max = metodo.executar(elemento);
				maior = elemento;
			}
		}
		return maior;
	}
}
