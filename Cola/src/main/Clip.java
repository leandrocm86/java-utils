package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import estruturas.Lista;
import swing.SwingUtils;
import utils.Str;


public class Clip {
	
	private Clipboard clipboard;
	private Lista<Str> items;
	
	public Clip() {
//		String myString = "This text will be copied into clipboard when running this code!";
//		StringSelection stringSelection = new StringSelection(myString);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		clipboard.setContents(stringSelection, null);
		items = new Lista<>();
	}
	
	private void fazLeitura() {
		try {
			Object conteudo = clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
			if (!items.contains(conteudo))
				items.add(new Str(conteudo));
			else {
				items.remove(conteudo);
				items.add(new Str(conteudo));
			}
		} catch (UnsupportedFlavorException | IOException e) {
			SwingUtils.showMessage("Erro ao buscar dados no Clipboard! " + e.getMessage());
		} 
	}
	
	public Lista<Str> getItems() {
		return items;
	}
}
