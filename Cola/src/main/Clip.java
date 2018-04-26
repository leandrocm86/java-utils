package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import estruturas.Lista;
import swing.SwingUtils;
import system.Agenda;
import utils.Str;


public class Clip implements FlavorListener {
	
	private Clipboard clipboard;
	private Lista<Str> items;
	
	public Clip() {
//		String myString = "This text will be copied into clipboard when running this code!";
//		StringSelection stringSelection = new StringSelection(myString);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		clipboard.setContents(stringSelection, null);
		items = new Lista<>();
		
		clipboard.addFlavorListener(this);
		
		Agenda.agendarCiclo(() -> fazLeitura(), 1000);
	}
	
	private void fazLeitura() {
		try {
			Object conteudo = clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
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
		} catch (UnsupportedFlavorException | IOException e) {
			SwingUtils.showMessage("Erro ao buscar dados no Clipboard! " + e.getMessage());
		} 
	}
	
	public Lista<Str> getItems() {
		return items;
	}

	@Override
	public void flavorsChanged(FlavorEvent e) {
		System.out.println(e.getSource().toString());
	}
}
