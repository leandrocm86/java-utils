package swing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import system.SystemTrayFrame;

public class Widget {
	
	private SystemTrayFrame frame;
	private Color textColor = Color.WHITE;
	private Fonte fonte = new Fonte("Arial", 1, 15);
	private CharSequence text;
	
	public Widget(String title) {
		frame = new SystemTrayFrame(title);
	}
	
	public void setText(CharSequence text) {
		this.text = text;
	}
	
	public void setTextColor(Color color) {
		this.textColor = color;
	}
	
	public void setFont(Fonte fonte) {
		this.fonte = fonte;
	}
	
	public void updateIcon() {
		BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = bi.createGraphics();
	    g.setColor(this.textColor);
	    g.setFont(this.fonte);
	    g.drawString(this.text.toString(), 0, 15);
	    g.dispose();
	    frame.setTrayImage(bi, frame.getTitle());
	}
}
