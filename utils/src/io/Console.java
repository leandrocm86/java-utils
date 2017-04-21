package io;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.RelativeLayout;
import utils.Strings;
import utils.SwingUtils;

public class Console {
	
	private static JTextArea textArea;
	private static JPanel consolePane;
	
	static {
		textArea = new JTextArea("");
		textArea.setEditable(false);
	}
	
	public static void imprime(String s) {
		textArea.setText(textArea.getText() + "\n" + s);
	}
	
	public static JPanel getPanel() {
		return getPanel(false);
	}
	
	public static JPanel getPanel(boolean clearButton) {
		if (consolePane == null) {
			if (clearButton) {
				consolePane = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS, 10, true));
				consolePane.add(SwingUtils.createScrollPane(textArea, 20, true), new Float(9));
					JButton button = new JButton("clear");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							textArea.setText("");
						}
					});
				consolePane.add(button, new Float(1));
			}
			else {
				consolePane = new JPanel();
				consolePane.add(SwingUtils.createScrollPane(textArea, 20, true));
			}
		}
		
		return consolePane;
	}
	
	public static void ligar() {
		consolePane.setVisible(true);
	}
	
	public static String getText() {
		return textArea.getText();
	}
	
	public static void imprimeErro(Throwable t) {
		imprime(Strings.stackTrace(t));
	}
}