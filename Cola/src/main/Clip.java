package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import estruturas.Lista;
import swing.SwingUtils;
import utils.Str;


public class Clip implements ClipboardOwner {
	
	private Clipboard clipboard;
	private Lista<Str> items;
	
	public Clip() {
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		items = new Lista<>();
		this.fazLeitura();
	}
	
	private void fazLeitura() {
		try {
			Transferable ultimaCola = clipboard.getContents(null);
			Object conteudo = ultimaCola.getTransferData(DataFlavor.stringFlavor);
			if (items.naoContem(conteudo)) {
				if (items.naoVazia())
					items.ultimo().equals(conteudo);
				System.out.println("Adicionando novo conteudo: " + conteudo.toString());
				items.add(new Str(conteudo));
			}
			else if (items.ultimo().notEquals(conteudo)){
				System.out.println("Trazendo conteudo pra frente: " + conteudo.toString());
				items.remove(conteudo);
				items.add(new Str(conteudo));
			}
			clipboard.setContents(ultimaCola, this);
		} catch (UnsupportedFlavorException | IOException e) {
			SwingUtils.showMessage("Erro ao buscar dados no Clipboard! " + e.getMessage());
		}
	}
	
	public Lista<Str> getItems() {
		return items;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		fazLeitura();
	}
}
