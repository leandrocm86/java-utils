package utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class SwingUtils {
	
	private static final CustomFont defaultFont = new CustomFont("Arial", 0, 40);
	
	private static HashMap<String, Component> componentsByName = new HashMap<>();
	
	static {
		javax.swing.UIManager.put("OptionPane.messageFont", defaultFont);
		javax.swing.UIManager.put("OptionPane.buttonFont", defaultFont);
		
		UIManager.put("Tree.closedIcon", new ImageIcon(SwingUtils.class.getResource("mais.jpg")));
		UIManager.put("Tree.openIcon", new ImageIcon(SwingUtils.class.getResource("menos.png")));
		UIManager.put("Tree.leafIcon", new ImageIcon(SwingUtils.class.getResource("dot.png")));
	}
	
	/**
	 * Seta a fonte para um componente e todos os seus filhos.
	 * Os filhos que ja tiverem uma fonte customizada nao sao alterados.
	 */
	public static void setFont(Component component, CustomFont font) {
		component.setFont(font);
	    if (component instanceof Container)
	    {
	        for (Component child : ((Container) component).getComponents())
	        {
	        	if (child.getFont() == null || !(child.getFont() instanceof CustomFont))
	        		setFont(child, font);
	        }
	    }
	}
	
	public static void setFont(CustomFont font, Component... components) {
		for (Component component : components)
			setFont(component, font);
	}
	
	public static Font getDefaultFont() {
		return defaultFont;
	}
	
	public static void setDefaultFont(Component component) {
	    setFont(component, defaultFont);
	}
	
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static RelativeLayout createLayout(int axis, int scale) {
		return createLayout(axis, scale, false);
	}
	
	public static RelativeLayout createLayout(int axis, int scale, boolean fill) {
		RelativeLayout layout = new RelativeLayout(axis, scale);
		layout.setBorderGap(0);
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
	
	public static void registerComponent(String name, Component component) {
		componentsByName.put(name, component);
	}
	
	public static Component getComponent(String name) {
		return componentsByName.get(name);
	}
}
