package utils;

import java.util.HashMap;

public class CDI {
	
	private static HashMap<String, Object> objetosPorNome = new HashMap<>();
	
	public static void set(String nome, Object objeto) {
		objetosPorNome.put(nome, objeto);
	}
	
	public static Object get(String nome) {
		return objetosPorNome.get(nome);
	}
}
