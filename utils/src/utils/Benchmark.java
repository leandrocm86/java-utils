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
	private static HashMap<String, Integer> corridasNaoEncontradas = new HashMap<String, Integer>();
	
	public static void start(String corrida) {
		corridasEmAndamento.put(corrida, System.currentTimeMillis());
	}
	
	public static void stop(String corrida) {
		Long inicio = corridasEmAndamento.get(corrida);
		if (inicio == null) {
			registraCorridaNaoEncontrada(corrida);
		} else {
			long duracao = System.currentTimeMillis() - inicio;
			Long tempoAcumulado = temposAcumulados.get(corrida);
			if (tempoAcumulado == null)
				tempoAcumulado = 0L;
			tempoAcumulado += duracao;
			temposAcumulados.put(corrida, tempoAcumulado);
		}
	}
	
	private static void registraCorridaNaoEncontrada(String corrida) {
		Integer vezesNaoEncontrada = corridasNaoEncontradas.get(corrida);
		if (vezesNaoEncontrada == null)
			vezesNaoEncontrada = 0;
		corridasNaoEncontradas.put(corrida, ++vezesNaoEncontrada);
	}

	public static long getAcumulado(String corrida) {
		return temposAcumulados.get(corrida);
	}
	
	public static String getResultado() {
		String resultado = "";
		for (Entry<String, Long> entry : temposAcumulados.entrySet())
			resultado += entry.getKey() + ": " + Str.formataTempo(entry.getValue()) + "\n";
		if (!corridasNaoEncontradas.isEmpty()) {
			resultado += "\n!!! OBSERVAÇÃO: HOUVE PEDIDOS PARA PARAR CORRIDAS QUE NÃO ESTAVAM CORRENDO:\n";
			for (Entry<String, Integer> entry : corridasNaoEncontradas.entrySet())
				resultado += new Str("'--' (-- vezes)\n", entry.getKey(), entry.getValue());
		}
		return resultado;
	}

}
