package estruturas;

import java.util.List;

/**
 * Estrutura com capacidade limitada que mantem apenas os elementos mais recentes.
 * Os mais novos sempre estao na frente, enquanto os ultimos saem primeiro.
 */
public class Cache<T> extends Lista<T> {
	
	private int capacidade;
	
	public Cache(int capacidade) {
		super(Tipo.LINKED);
		this.capacidade = capacidade;
	}
	
	/**
	 * Inicializa um cache a partir de uma lista.
	 * Levantará exceção se a lista já exceder a capacidade escolhida para o cache.
	 * OBS: O cache manipulará a lista diretamente, sem copiá-la.
	 */
	public Cache(int capacidade, List<T> lista) {
		super(lista);
		if(lista.size() > capacidade)
			throw new IllegalArgumentException("Tentativa de inicializar cache com lista maior que a capacidade.");
		this.capacidade = capacidade;
	}
	
	/**
	 * Retorna TRUE se o elemento ja existia, ou FALSE caso contrario.
	 * Elementos que ja se encontram na lista apenas passam para frente, nao sao duplicados.
	 */
	@Override
	public boolean add(T elemento) {
		boolean elementoNovo = true;
		int index = super.indexOf(elemento);
		if (index >= 0) {
			super.remove(index);
			elementoNovo = false;
		}
		else if (super.size() == capacidade) {
			super.remove(super.ultimoIndex());
		}
		super.add(0, elemento);
		return elementoNovo;
	}
}
