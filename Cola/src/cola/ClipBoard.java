package cola;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import io.Escritor;
import io.Log;
import swing.SwingUtils;
import system.SystemTrayFrame;
import system.Agenda;
import system.Sistema;
import utils.Erros;
import utils.Str;

public class ClipBoard {
	
	public static void main(String[] args) throws UnsupportedFlavorException, IOException {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				SwingUtils.showMessage("Erro ao buscar dados no Clipboard! " + e.getMessage());
				Escritor errorLog = new Escritor(Sistema.getSystemPath() + "erroClipboard.log");
				errorLog.escreve(Erros.stackTraceToStr(e));
				errorLog.terminar();
			}
		});
		
		Log.iniciar(Sistema.getSystemPath() + "clipboardDebug.log");
		Log.msgLn("Iniciando app...");
		
		Agenda.agendar(() -> executar(), 10000); // Espera um tempo pra executar para carregamento do SystemTray.
	}
	
	private static void executar() {
		try {
			Log.msgLn("Iniciando execucao...");
			Str fileName = new Str(Sistema.getSystemPath() + "clipboard.txt");
			Clip clip;
			clip = new Clip(fileName);
			SystemTrayFrame frame = new SystemTrayFrame("Clipboard", Sistema.getSystemPath() + "clipboard.png");
			clip.setAsListener(frame);
			Log.msgLn("Terminando execucao...");
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}
}
