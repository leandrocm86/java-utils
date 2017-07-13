package utils;

import java.util.HashMap;

public class CDI {
	
	private static HashMap<Class<?>, Object> objetosPorClasse = new HashMap<>();
	
	/**
	 * Grava um objeto como referencia de sua classe CONCRETA.
	 */
	public static void set(Object objeto) {
		if (objetosPorClasse.get(objeto.getClass()) != null)
			throw new IllegalStateException("Tentativa de registro de um tipo ja existente no CDI! Eh precisso especificar um nome.");
		objetosPorClasse.put(objeto.getClass(), objeto);
	}
	
	/**
	 * Grava um objeto como referencia da classe especificada por parametro.
	 */
	public static void set(Object objeto, Class<?> tipo) {
		if (!tipo.isInstance(objeto))
			throw new IllegalArgumentException("Tentativa de registrar objeto por classe incompativel no CDI!");
		objetosPorClasse.put(tipo, objeto);
	}
	
	/**
	 * Grava um objeto como referencia da sua classe CONCRETA e com o nome especificado.
	 * Esta eh a unica maneira de gravar no CDI 2+ objetos com a mesma classe concreta,
	 * devendo ser diferenciados por nome.
	 */
	public static void set(Object objeto, CharSequence nome) {
		Object existentes = objetosPorClasse.get(objeto.getClass());
		if (existentes != null) {
			if (!(existentes instanceof HashMap<?, ?>))
				throw new IllegalStateException("Tentativa de registro de um tipo ja existente no CDI que nao foi identificado.");
		}
		else {
			existentes = new HashMap<CharSequence, Object>();
			objetosPorClasse.put(objeto.getClass(), existentes);
		}
		((HashMap<CharSequence, Object>)existentes).put(nome, objeto);
	}
	
	/**
	 * Recupera do CDI o objeto representante do tipo passado por parametro.
	 */
	public static <T> T get(Class<T> tipo) {
		return (T) objetosPorClasse.get(tipo);
	}
	
	/**
	 * Recupera do CDI o objeto representante do tipo passado por parametro e com o nome especificado.
	 */
	public static <T> T get(Class<T> tipo, CharSequence nome) {
		HashMap<CharSequence, Object> mapa = (HashMap<CharSequence, Object>) objetosPorClasse.get(tipo);
		if (mapa != null) {
			return (T) mapa.get(nome);
		}
		return null;
	}
}
