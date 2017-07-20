package io;

import utils.Data;

public class Log {
	
	private static Escritor escritor;
	
	public static void iniciar(String path) {
		escritor = new Escritor(path, true);
		escritor.setLimiteBuffer(1);
		msgLn("Iniciando LOG", true);
	}
	
	public static void msg(CharSequence msg) {
		msg(msg, false);
	}
	
	public static void msg(CharSequence msg, boolean destaque) {
		if (destaque)
			msg = "########## " + msg.toString() + " ##########";
		escritor.escreve(new Data() + " " + msg);
	}
	
	public static void msgLn(CharSequence msg) {
		msgLn(msg, false);
	}
	
	public static void msgLn(CharSequence msg, boolean destaque) {
		msg(msg+"\n", destaque);
		escritor.enter();
	}
	
	public static void terminar() {
		msgLn("Terminando LOG", true);
		escritor.terminar();
	}
}
