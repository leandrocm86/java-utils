package main;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import estruturas.Lista;
import swing.SwingUtils;
import utils.Str;


public class Clip implements ClipboardOwner, MouseListener {
	
	private Clipboard clipboard;
	private Lista<Str> items;
	private Point posicao;
	
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
	
	private void exibirLista() {
		JPopupMenu trayPopup = new JPopupMenu();
		
		for (Str item : this.items)
			trayPopup.add(new JMenuItem(item.val()));
		
		trayPopup.setLocation(this.posicao);
        trayPopup.setInvoker(trayPopup);
        trayPopup.setVisible(true);
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		fazLeitura();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.posicao = e.getLocationOnScreen();
		this.exibirLista();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
