package estruturas;

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
	 * Retorna TRUE se teve remocao, ou FALSE caso contrario.
	 * Elementos que ja se encontram na lista apenas passam para frente, nao sao duplicados.
	 */
	@Override
	public boolean add(T elemento) {
		boolean teveRemocao = false;
		int index = super.indexOf(elemento);
		if (index >= 0) {
			super.remove(index);
		}
		else if (super.size() == capacidade) {
			super.remove(super.ultimoIndex());
			teveRemocao = true;
		}
		super.add(0, elemento);
		return teveRemocao;
	}
}
