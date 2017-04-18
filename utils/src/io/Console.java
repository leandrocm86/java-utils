package io;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.SwingUtils;

public class Console {
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public Console() {
		textArea = new JTextArea("");
		textArea.setEditable(false);
		scrollPane = SwingUtils.createScrollPane(textArea, 20, true);
		scrollPane.setVisible(false);
	}
	
	public void imprime(String s) {
		textArea.setText(textArea.getText() + "\n" + s);
	}
	
	public JScrollPane getPanel() {
		return scrollPane;
	}
	
	public void ligar() {
		scrollPane.setVisible(true);
	}
	
	public String getText() {
		return textArea.getText();
	}

}