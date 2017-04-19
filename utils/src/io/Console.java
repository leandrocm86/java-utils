package io;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.SwingUtils;

public class Console {
	
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	
	static {
		textArea = new JTextArea("");
		textArea.setEditable(false);
		scrollPane = SwingUtils.createScrollPane(textArea, 20, true);
		scrollPane.setVisible(false);
	}
	
	public static void imprime(String s) {
		textArea.setText(textArea.getText() + "\n" + s);
	}
	
	public static JScrollPane getPanel() {
		return scrollPane;
	}
	
	public static void ligar() {
		scrollPane.setVisible(true);
	}
	
	public static String getText() {
		return textArea.getText();
	}

}