package io;
import java.awt.BorderLayout;
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
	
	private static JTextArea textArea;
	private static JPanel consolePane;
	
	static {
		textArea = new JTextArea("");
		textArea.setEditable(false);
	}

	@SuppressWarnings("deprecation")
	private static void createPane(boolean botaoClear) {
		consolePane = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS));
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
	
	public static void imprime(CharSequence s) {
		textArea.append("\n" + s.toString());
	}
	
	public static JPanel getPanel(boolean botaoClear) {
		if (consolePane == null)
			createPane(botaoClear);
		return consolePane;
	}
	
	public static void ligar(boolean criarFrame) {
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
	
	public static String getText() {
		return textArea.getText();
	}
	
	public static void imprimeErro(Throwable t) {
		Str erros = Erros.stackTraceToStr(t);
		imprime(erros);
		System.out.println(erros);
	}
}