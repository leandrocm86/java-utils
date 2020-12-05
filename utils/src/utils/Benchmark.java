package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Clase utilitaria que contabiliza o total de tempo gasto em determinados pontos de codigo.
 * Cada trecho de codigo eh associado a um nome de corrida, e cada nome vai acumulando o tempo de seus intervalos start/stop.
 */
public class Benchmark {
	private static HashMap<String, Long> temposAcumulados = new LinkedHashMap<>();
	private static HashMap<String, Long> corridasEmAndamento = new HashMap<String, Long>();
	
	public static void start(String corrida) {
		corridasEmAndamento.put(corrida, System.currentTimeMillis());
	}
	
	public static void stop(String corrida) {
		long duracao = System.currentTimeMillis() - corridasEmAndamento.get(corrida);
		Long tempoAcumulado = temposAcumulados.get(corrida);
		if (tempoAcumulado == null)
			tempoAcumulado = 0L;
		tempoAcumulado += duracao;
		temposAcumulados.put(corrida, tempoAcumulado);
	}
	
	public static String getResultado() {
		String resultado = "";
		for (Entry<String, Long> entry : temposAcumulados.entrySet())
			resultado += entry.getKey() + ": " + Str.formataTempo(entry.getValue()) + "\n";
		return resultado;
	}

}
