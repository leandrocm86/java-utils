package io;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import swing.CustomFont;
import swing.RelativeLayout;
import swing.SwingUtils;
import utils.Erros;
import utils.Str;

public class Console {
	
	private static JTextArea textArea;
	private static JPanel consolePane;
	
	static {
		textArea = new JTextArea("");
		textArea.setEditable(false);
		
		consolePane = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS));
		consolePane.add(SwingUtils.createScrollPane(textArea, 20, true), new Float(9));
		JButton button = new JButton("clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		consolePane.add(button, new Float(1));
	}
	
	public static void imprime(String s) {
		textArea.setText(textArea.getText() + "\n" + s);
	}
	
	public static void imprime(Str s) {
		imprime(s.val());
	}
	
	public static JPanel getPanel() {
		return consolePane;
	}
	
	public static void ligar(boolean criarFrame) {
		consolePane.setVisible(true);
		
		if (criarFrame) {
			JFrame mainFrame = new JFrame("Console");
		    mainFrame.setLayout(new BorderLayout());
		    mainFrame.add(consolePane);
		    SwingUtils.setFont(mainFrame, new CustomFont("Arial", 0, 20));
		    mainFrame.setSize(400, 400);
		    mainFrame.setAlwaysOnTop(true);
			mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			mainFrame.setVisible(true);
		}
	}
	
	public static String getText() {
		return textArea.getText();
	}
	
	public static void imprimeErro(Throwable t) {
		imprime(Erros.stackTrace(t));
	}
}