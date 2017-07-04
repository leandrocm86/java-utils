package utils;

import java.util.HashMap;

public class CDI {
	
	private static HashMap<CharSequence, Object> objetosPorNome = new HashMap<>();
	
	public static void set(CharSequence nome, Object objeto) {
		objetosPorNome.put(nome, objeto);
	}
	
	public static Object get(CharSequence nome) {
		return objetosPorNome.get(nome);
	}
}
