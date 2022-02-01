package io;

import java.io.PrintStream;

import estruturas.Cache;
import swing.SwingUtils;
import utils.Data;
import utils.Erros;

public class Log {
	
	private static Escritor escritor;
	
	private static Cache<CharSequence> debugMsgs = new Cache<>(10);
	
	private static PrintStream console = null;
	
	public static void iniciar(CharSequence path) {
		try {
			if (path != null && !path.toString().trim().isEmpty()) {
				escritor = new Escritor(path, true);
				escritor.setLimiteBuffer(1);
				escritor.enter();
			}
			msgLn("Iniciando LOG", true);
			enter();
		}
		catch (Throwable t) {
			System.out.println("!!! Erro: Não foi possível iniciar o log em " + path);
			System.out.println(Erros.resumo(t));
		}
	}
	
	public static void setConsole(PrintStream consoleParaLogar) {
		console = consoleParaLogar;
	}
	
	public static void msg(CharSequence msg) {
		msg(msg, false, false);
	}
	
	public static void msg(CharSequence msg, boolean destaque) {
		msg(msg, destaque, false);
	}
	
	public static void msgLn(CharSequence msg) {
		msg(msg, false, true);
	}
	
	public static void msgLn(CharSequence msg, boolean destaque) {
		msg(msg, destaque, true);
	}
	
	public static void msg(CharSequence msg, boolean destaque, boolean ln) {
		if (destaque)
			msg = "########## " + msg.toString() + " ##########";
		msg = new Data() + " " + msg;
		if (ln)
			escreveLn(msg);
		else
			escreve(msg);
		if (console != null) {
			console.println(msg);
		}
	}
	
	private static String agregaFrases(Object... strings) {
		String texto = strings[0].toString();
		if (strings.length > 1) {
			for (int i = 1; i < strings.length; i++)
				texto += " " + strings[i];
		}
		return texto;
	}
	
	public static void i(Object... textos) {
		msgLn(" [INFO] " + agregaFrases(textos), false);
	}
	
	public static void w(Object... textos) {
		msgLn(" [WARN] " + agregaFrases(textos), false);
	}
	
	public static void e(Throwable t, Object... textos) {
		msgLn(" [ERRO] " + agregaFrases(textos), false);
		logaErro(t);
	}
	
	public static void enter() {
		msg("", false, true);
	}
	
	
	public static void debug(CharSequence msg) {
		msg = new Data() + ": " + msg;
		debugMsgs.add(msg);
		if (console != null)
			console.println("DEBUG: " + msg);
	}
	
	public static void logaErro(Throwable e) {
		msgLn(Erros.resumo(e), true);
		msgLn("Stacktrace:\n" + Erros.stackTraceToStr(e));
		if (debugMsgs.naoVazia()) {
			msgLn("\nUltimas mensagens de nivel DEBUG antes do erro:");
			for (CharSequence msg : debugMsgs) {
				escreveLn(msg);
			}
			enter();
		}
	}
	
	public static void terminar() {
		try {
			enter();
			msgLn("Terminando LOG", true);
			if (escritor != null)
				escritor.terminar();
		}
		catch(Throwable t) {
			SwingUtils.showMessage(Erros.stackTraceToStr(t, 5));
		}
	}
	
	private static void escreve(CharSequence msg) {
		if (escritor != null)
			escritor.escreve(msg);
	}
	
	private static void escreveLn(CharSequence msg) {
		if (escritor != null)
			escritor.escreveLn(msg);
	}
	
	public static boolean gravando() {
		return escritor != null;
	}
}
