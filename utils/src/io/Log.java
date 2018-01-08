package io;

import estruturas.Lista;
import estruturas.Lista.Tipo;
import utils.Data;
import utils.Erros;

public class Log {
	
	private static Escritor escritor;
	
	private static Lista<CharSequence> debugMsgs = new Lista<>(Tipo.LINKED);
	
	private static boolean consoleLigado = false;
	
	public static void iniciar(String path) {
		escritor = new Escritor(path, true);
		escritor.setLimiteBuffer(1);
		msgLn("Iniciando LOG", true);
	}
	
	public static void setConsoleLigado() {
		consoleLigado = true;
	}
	
	public static void msg(CharSequence msg) {
		msg(msg, false);
	}
	
	public static void msg(CharSequence msg, boolean destaque) {
		if (destaque)
			msg = "########## " + msg.toString() + " ##########";
		msg = new Data() + " " + msg;
		escritor.escreve(msg);
		if (consoleLigado)
			Console.imprime(msg);
	}
	
	public static void msgLn(CharSequence msg) {
		msgLn(msg, false);
	}
	
	public static void msgLn(CharSequence msg, boolean destaque) {
		msg(msg, destaque);
		escritor.enter();
	}
	
	public static void debug(CharSequence msg) {
		if (debugMsgs.size() == 10)
			debugMsgs.remove(0);
		msg = new Data() + ": " + msg;
		debugMsgs.add(msg);
		if (consoleLigado)
			Console.imprime("DEBUG: " + msg);
	}
	
	public static void logaErro(Throwable e) {
		msgLn("ERRO: " + e.getClass().getName() + ": " + e.getMessage(), true);
		if (e.getCause() != null) {
			Throwable e2 = e.getCause();
			Log.msgLn("CAUSADO POR " + e2.getClass().getName() + e2.getMessage());
			Log.msgLn("Primeiras linhas da causa:");
			Log.msgLn(Erros.stackTrace(e2, 5));
		}
		else {
			Log.msgLn("Primeiras linhas:");
			Log.msgLn(Erros.stackTrace(e, 5));
		}
		if (debugMsgs.naoVazia()) {
			msgLn("Ultimas mensagens de nivel DEBUG antes do erro:");
			for (CharSequence msg : debugMsgs) {
				escritor.escreveLn(msg);
			}
		}
	}
	
	public static void terminar() {
		msgLn("Terminando LOG", true);
		escritor.terminar();
	}
}
