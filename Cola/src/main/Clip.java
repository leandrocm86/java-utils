package main;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import estruturas.Cache;
import swing.Fonte;
import swing.SwingUtils;
import utils.CDI;
import utils.Str;


public class Clip implements ClipboardOwner, MouseListener {
	
	private Clipboard clipboard;
	private Cache<Str> items;
	private Point posicao;
	
	public Clip() {
		CDI.set(this);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		items = new Cache<>(1000);
		this.fazLeitura();
	}
	
	private void fazLeitura() {
		System.out.println("Fazendo leitura");
		try {
			Transferable ultimaCola = clipboard.getContents(null);
			Object conteudo = ultimaCola.getTransferData(DataFlavor.stringFlavor);
			if (items.naoContem(conteudo)) {
				items.add(new Str(conteudo));
			}
			else if (items.primeiro().notEquals(conteudo)){
				System.out.println("Trazendo conteudo pra frente: " + conteudo.toString());
				items.remove(conteudo);
				items.add(new Str(conteudo));
			}
			clipboard.setContents(ultimaCola, this);
		} catch (UnsupportedFlavorException | IOException e) {
			SwingUtils.showMessage("Erro ao buscar dados no Clipboard! " + e.getMessage());
		}
	}
	
	private void exibirLista() {
		JPopupMenu trayPopup = new JPopupMenu();
		
		for (Str item : this.items) {
			System.out.println("Criando item " + item);
			trayPopup.add(criarMenuItem(item));
		}
		
		Fonte.ARIAL_20.set(trayPopup);
		trayPopup.setLocation(this.posicao.x, this.posicao.y + 15);
        trayPopup.setInvoker(trayPopup);
        trayPopup.setVisible(true);
	}

	private JMenuItem criarMenuItem(Str item) {
		JMenuItem menuItem = new JMenuItem(item.ate(60).val());
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selecionando " + item);
				StringSelection conteudoSelecionado = new StringSelection(item.val());
				clipboard.setContents(conteudoSelecionado, CDI.get(Clip.class));
				items.remove(item);
				items.add(new Str(item));
			}
		});
		return menuItem;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		fazLeitura();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicou no Clip");
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
