package utils;

public class Cronometro {
	
	public static long ultimaParada;
	public static CharSequence nomeCheckpoint;
	
	public static void start() {
		System.out.println(new Data() + " Comecando nova corrida...");
		ultimaParada = System.currentTimeMillis();
		nomeCheckpoint = "Inicio";
	}
	
	public static void check(CharSequence nome) {
		System.out.println((System.currentTimeMillis() - ultimaParada) + "ms de " + nomeCheckpoint + " ate " + nome);
		ultimaParada = System.currentTimeMillis();
		nomeCheckpoint = nome;
	}
	
	public static void stop() {
		System.out.println((System.currentTimeMillis() - ultimaParada) + "ms de " + nomeCheckpoint + " ate o fim.");
		System.out.println(new Data() + " Corrida terminada.");
	}
	
}
