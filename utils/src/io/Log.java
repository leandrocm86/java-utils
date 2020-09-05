package io;

import estruturas.Cache;
import swing.SwingUtils;
import utils.Data;
import utils.Erros;

public class Log {
	
	private static Escritor escritor;
	
	private static Cache<CharSequence> debugMsgs = new Cache<>(10);
	
	private static Console console = null;
	
	public static void iniciar(String path) {
		if (path != null) {
			escritor = new Escritor(path, true);
			escritor.setLimiteBuffer(1);
			escritor.enter();
		}
		msgLn("Iniciando LOG", true);
	}
	
	public static void setConsole(Console consoleParaLogar) {
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
			console.imprime(msg);
		}
	}
	
	
	public static void debug(CharSequence msg) {
		msg = new Data() + ": " + msg;
		debugMsgs.add(msg);
		if (console != null)
			console.imprime("DEBUG: " + msg);
	}
	
	public static void logaErro(Throwable e) {
		msgLn("ERRO: " + e.getClass().getName() + ": " + e.getMessage(), true);
		if (e.getCause() != null) {
			Throwable e2 = e.getCause();
			Log.msgLn("CAUSADO POR " + e2.getClass().getName() + e2.getMessage());
			Log.msgLn("Primeiras linhas da causa:");
			Log.msgLn(Erros.stackTraceToStr(e2, 5));
		}
		else {
			Log.msgLn("Primeiras linhas:");
			Log.msgLn(Erros.stackTraceToStr(e, 10));
		}
		if (debugMsgs.naoVazia()) {
			msgLn("Ultimas mensagens de nivel DEBUG antes do erro:");
			for (CharSequence msg : debugMsgs) {
				escreveLn(msg);
			}
		}
	}
	
	public static void terminar() {
		try {
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
		else
			System.out.print(msg);
	}
	
	private static void escreveLn(CharSequence msg) {
		if (escritor != null)
			escritor.escreveLn(msg);
		else
			System.out.println(msg);
	}
}
