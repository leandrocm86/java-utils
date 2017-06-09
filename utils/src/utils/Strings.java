package utils;

public class Strings {
	
	public static boolean empty(String s) {
		return s == null || s.equals("");
	}
	
	public static String substringDesde(String texto, String inicio, boolean inicioIncluso) {
		return substring(texto, inicio, null, inicioIncluso, false);
	}
	
	public static String substringAte(String texto, String fim, boolean fimIncluso) {
		return substring(texto, null, fim, false, fimIncluso);
	}
	
	public static String substringAte(String texto, char[] possiveisFins, boolean fimIncluso) {
		String retorno = "";
		for (char c : texto.toCharArray()) {
			for (Character C : possiveisFins)
				if (C == c)
					return retorno;
			retorno += c;
		}
		return retorno;
	}
	
	public static String substring(String texto, String inicio, String fim, boolean inicioIncluso, boolean fimIncluso) {
		int indexInicio = 0;
		if (inicio != null) {
			indexInicio = texto.indexOf(inicio);
			if (indexInicio == -1)
				return "";
			else if (!inicioIncluso)
				indexInicio += inicio.length();
		}
		int indexFim = texto.length();
		if (fim != null) {
			indexFim = texto.indexOf(fim, indexInicio);
			if (indexFim == -1)
				indexFim = texto.length();
			else if (fimIncluso)
				indexFim += fim.length();
		}
		
		return texto.substring(indexInicio, indexFim);
	}
}
