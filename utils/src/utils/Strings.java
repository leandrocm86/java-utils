package utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Strings {
	
	private static SimpleDateFormat TIME_FORMAT;
	
	public static boolean empty(String s) {
		return s == null || s.equals("");
	}
	
	public static String stackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}
	
	public static Date parseTime(String time) throws ParseException {
		if (TIME_FORMAT == null)
			TIME_FORMAT = new SimpleDateFormat("HH:mm:ss,SSS"); // H maisculo = 0-23.
		if (time.length() == 5)
			time += ":00,000";
		else if (time.length() == 8)
			time += ",000";
		return TIME_FORMAT.parse(time);
	}
	
	public static String substringDesde(String texto, String inicio, boolean inicioIncluso) {
		return substring(texto, inicio, null, inicioIncluso, false);
	}
	
	public static String substringAte(String texto, String fim, boolean fimIncluso) {
		return substring(texto, null, fim, false, fimIncluso);
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
			indexFim = texto.indexOf(fim);
			if (indexFim == -1)
				indexFim = texto.length();
			else if (fimIncluso)
				indexFim += fim.length();
		}
		
		return texto.substring(indexInicio, indexFim);
	}
}
