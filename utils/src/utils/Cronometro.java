package utils;

public class Cronometro {
	
	public static long inicio, ultimaParada;
	
	/**
	 * Inicia uma contagem gravando o timestamp corrente.
	 */
	public static void start() {
		inicio = ultimaParada = System.currentTimeMillis();
	}
	
	/**
	 * Retorna o tempo decorrido desde o check anterior ou o início.
	 */
	public static long check() {
		long checkpoint = System.currentTimeMillis() - ultimaParada;
		ultimaParada = System.currentTimeMillis();
		return checkpoint;
	}
	
	/**
	 * Zera o cronometro e retorna o tempo total transcorrido desde o início.
	 */
	public static long stop() {
		long total = System.currentTimeMillis() - inicio;
		inicio = ultimaParada = 0;
		return total;
	}
	
}
