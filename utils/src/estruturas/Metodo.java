package estruturas;

/**
 * Interface generica para representar metodos que executam com parametro do tipo T e retornam um tipo S.
 */
public interface Metodo<S, T> {
	public S executar(T objeto);
}
