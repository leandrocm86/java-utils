package swing;

import java.awt.Color;

import javax.swing.JTextPane;

import system.SystemTrayFrame;

public class Widget {
	
	private SystemTrayFrame frame;
	private JTextPane pane;
	
	public Widget(String title) {
		frame = new SystemTrayFrame(title);
		pane = new JTextPane();
		frame.add(pane);
		frame.setUndecorated(true);
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
	}
	
	public void setSize(int largura, int altura) {
		frame.setSize(largura, altura);
	}
	
	public void setPosition(int x, int y) {
		frame.setLocation(x, y);
	}
	
	public void setText(CharSequence text) {
		pane.setText(text.toString());
	}
	
	public void setTextColor(Color color) {
		pane.setForeground(color);
	}
	
	public void setFont(Fonte font) {
		pane.setFont(font);
	}
}
