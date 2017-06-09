package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Erros {
	
	public static Str stackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return new Str(sw); // stack trace as a string
	}
	
	public static Str stackTrace(Throwable t, int linhas) {
		Str str = new Str();
		for (int i = 0; i < linhas && i < t.getStackTrace().length; i++) {
			str.appendLn(t.getStackTrace()[i].toString());
		}
		return str;
	}
}
