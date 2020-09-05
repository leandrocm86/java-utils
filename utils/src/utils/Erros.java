package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import estruturas.Lista;

public class Erros {
	
	public static Str stackTraceToStr(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return new Str(sw); // stack trace as a string
	}
	
	public static Lista<Str> stackTrace(Throwable t) {
		return stackTrace(t, t.getStackTrace().length);
	}
	
	public static Str stackTraceToStr(Throwable t, int linhas) {
		Str str = new Str();
		for (int i = 0; i < linhas && i < t.getStackTrace().length; i++) {
			str.appendLn(t.getStackTrace()[i].toString());
		}
		return str;
	}
	
	public static Lista<Str> stackTrace(Throwable t, int linhas) {
		Lista<Str> retorno = new Lista<Str>();
		for (int i = 0; i < linhas && i < t.getStackTrace().length; i++) {
			retorno.add(new Str(t.getStackTrace()[i].toString()));
		}
		return retorno;
	}
	
	public static Str resumo(Throwable t) {
		Str resumo = new Str();
		if (t.getMessage() != null)
			resumo.append(t.getMessage() + ". ");
		if (t.getCause() != null && t.getCause().getMessage() != null)
			resumo.append("Causa: " + t.getCause().getMessage());
		return resumo;
	}
}
