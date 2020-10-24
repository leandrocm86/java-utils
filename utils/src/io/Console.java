package io;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import swing.Fonte;
import swing.RelativeLayout;
import swing.SwingUtils;
import utils.Erros;
import utils.Str;

public class Console {
	
	private JTextArea textArea;
	private JPanel consolePane;
	private static Color corFundo = Color.BLACK;
	private static Color corTexto = Color.WHITE;
	
	public Console() {
		this.textArea = new JTextArea("");
		this.textArea.setEditable(false);
		this.textArea.setBackground(corFundo);
		this.textArea.setForeground(corTexto);
	}
	
	public static void mudarFundoPadrao(Color color) {
		corFundo = color;
	}
	
	public static void mudarTextoPadrao(Color color) {
		corTexto = color;
	}

	private void createPane(boolean botaoClear) {
		consolePane = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS));
		consolePane.setBackground(corFundo);
		consolePane.add(SwingUtils.createScrollPane(textArea, 20, true), new Float(9));
		if (botaoClear) {
			JButton button = new JButton("clear");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea.setText("");
				}
			});
			consolePane.add(button, new Float(1));
		}
		consolePane.setVisible(false); // Console escondido enquanto nao for ligado.
	}
	
	public void imprime(CharSequence s) {
		textArea.append(Str.LN + s.toString());
	}
	
	public JPanel getPanel(boolean botaoClear) {
		if (consolePane == null)
			createPane(botaoClear);
		return consolePane;
	}
	
	public void ligar(boolean criarFrame) {
		if (criarFrame) {
			if (consolePane == null)
				createPane(true);
			JFrame mainFrame = new JFrame("Console");
		    mainFrame.setLayout(new BorderLayout());
		    mainFrame.add(consolePane);
		    Fonte.ARIAL_20.set(mainFrame);
		    mainFrame.setSize(400, 400);
		    mainFrame.setAlwaysOnTop(true);
			mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			mainFrame.setVisible(true);
		}
		consolePane.setVisible(true);
	}
	
	public String getText() {
		return textArea.getText();
	}
	
	public void imprimeErro(Throwable t) {
		Str erros = Erros.stackTraceToStr(t);
		imprime(erros);
		System.out.println(erros);
	}
}