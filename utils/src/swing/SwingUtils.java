package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwingUtils {
	
	private static final Fonte defaultFont = Fonte.ARIAL_40;
	
	static {
		javax.swing.UIManager.put("OptionPane.messageFont", defaultFont);
		javax.swing.UIManager.put("OptionPane.buttonFont", defaultFont);
		
//		UIManager.put("Tree.closedIcon", new ImageIcon(SwingUtils.class.getResource("mais.jpg")));
//		UIManager.put("Tree.openIcon", new ImageIcon(SwingUtils.class.getResource("menos.png")));
//		UIManager.put("Tree.leafIcon", new ImageIcon(SwingUtils.class.getResource("dot.png")));
	}
	
	public static Font getDefaultFont() {
		return defaultFont;
	}
	
	public static void setDefaultFont(Component component) {
	    defaultFont.set(component);
	}
	
	public static void setBackgroundColor(JPanel panel, Color color) {
		panel.setBackground(color);
		for (Component component : panel.getComponents()) {
			if (component instanceof JPanel)
				setBackgroundColor((JPanel) component, color);
			else
				component.setBackground(color);
		}
	}
	
	public static void showMessage(CharSequence message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static RelativeLayout createLayout(int axis) {
		return createLayout(axis, 0);
	}
	
	public static RelativeLayout createLayout(int axis, int gap) {
		return createLayout(axis, gap, true);
	}
	
	public static RelativeLayout createLayout(int axis, int gap, int borderGap) {
		return createLayout(axis, gap, borderGap, true);
	}
	
	public static RelativeLayout createLayout(int axis, int gap, boolean fill) {
		return createLayout(axis, gap, 0, fill);
	}
	
	public static RelativeLayout createLayout(int axis, int gap, int borderGap, boolean fill) {
		RelativeLayout layout = new RelativeLayout(axis, gap);
		layout.setBorderGap(borderGap);
	    if (fill)
	    	layout.setFill(true);
	    return layout;
	}
	
	/**
	 * Cria um ScrollPane apenas vertical.
	 */
	public static JScrollPane createScrollPane(Component content, int scrollSize) {
		return createScrollPane(content, scrollSize, false);
	}
	
	public static JScrollPane createScrollPane(Component content, int scrollSize, boolean horizontal) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(content);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(scrollSize, 0));
		
		if (horizontal)
			scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, scrollSize));
		else
			scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
		
		return scrollPane;
	}
	
	public static void centralizarJanela(Window window) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
	}
	
	public static int getScreenWidth() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	public static int getScreenHeight() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	
	public static JButton createImageButton(CharSequence imageUrl) throws IOException {
		JButton button = new JButton();
//		Image img = Toolkit.getDefaultToolkit().getImage(imageUrl.toString());
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIcon(new StretchIcon(imageUrl.toString(), false));
		return button;
	}
	
	/**
	 * Adiciona um dado listener a um componente e todos os seus filhos.
	 */
	public static void addListener(EventListener listener, Component component) {
		addListenerToSingleComponent(listener, component);
		if (component instanceof Container)
			for (Component filho : ((Container) component).getComponents())
				addListener(listener, filho);
	}
	
	private static void addListenerToSingleComponent(EventListener listener, Component component) {
		if (listener instanceof MouseListener)
			component.addMouseListener((MouseListener) listener);
		if (listener instanceof MouseMotionListener)
			component.addMouseMotionListener((MouseMotionListener) listener);
	}
}
