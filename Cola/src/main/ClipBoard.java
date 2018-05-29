package main;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import io.Escritor;
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
				new Escritor(Sistema.getSystemPath() + "erroClipboard.log").escreveTudo(Erros.stackTrace(e));
			}
		});
		
		Agenda.agendar(() -> executar(), 2000); // Espera um tempo pra executar para carregamento do SystemTray.
	}
	
	private static void executar() {
		try {
			Str fileName = new Str(Sistema.getSystemPath() + "clipboard.txt");
			Clip clip;
			clip = new Clip(fileName);
			SystemTrayFrame frame = new SystemTrayFrame("Clipboard", Sistema.getSystemPath() + "clipboard.png");
			frame.addListener(clip);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}
}
