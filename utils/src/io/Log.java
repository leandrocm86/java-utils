package io;

import utils.Data;
import utils.Str;

public class Log {
	
	private static Escritor escritor;
	
	public static void iniciar(String path) {
		escritor = new Escritor(path, true);
		msgLn("Iniciando LOG", true);
	}
	
	public static void msg(String msg) {
		msg(msg, false);
	}
	
	public static void msg(Str msg) {
		msg(msg.toString());
	}
	
	public static void msg(String msg, boolean destaque) {
		if (destaque)
			msg = "########## " + msg + " ##########";
		escritor.escreve(new Data() + " " + msg);
	}
	
	public static void msg(Str msg, boolean destaque) {
		msg(msg.val(), destaque);
	}
	
	public static void msgLn(String msg) {
		msgLn(msg, false);
	}
	
	public static void msgLn(Str msg) {
		msgLn(msg.val());
	}
	
	public static void msgLn(String msg, boolean destaque) {
		msg(msg, destaque);
		escritor.enter();
	}
	
	public static void msgLn(Str msg, boolean destaque) {
		msg(msg.val(), destaque);
	}
	
	public static void terminar() {
		msgLn("Terminando LOG", true);
		escritor.terminar();
	}
}
