package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EventListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class SwingUtils {
	
	private static Fonte defaultFont = getScreenHeight() > 1080 ? Fonte.ARIAL_40 : Fonte.ARIAL_20;
	
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
	    if (fill) {
	    	layout.setFill(true);
	    	layout.setFillGap(0);
	    }
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
	
	/**
	 * Retorna a largura (em pixels) de uma dada porcentagem da tela.
	 */
	public static int getWidth(float porcentagemTela) {
		return Math.round(porcentagemTela * getScreenWidth() / 100);
	}
	
	/**
	 * Retorna a altura (em pixels) de uma dada porcentagem da tela.
	 */
	public static int getHeight(float porcentagemTela) {
		return Math.round(porcentagemTela * getScreenHeight() / 100);
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
	
	public static void scaleCheckBoxIcon(JCheckBox checkbox){
	    boolean previousState = checkbox.isSelected();
	    checkbox.setSelected(false);
	    FontMetrics boxFontMetrics =  checkbox.getFontMetrics(checkbox.getFont());
	    Icon boxIcon = UIManager.getIcon("CheckBox.icon");
	    BufferedImage boxImage = new BufferedImage(
	        boxIcon.getIconWidth(), boxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
	    );
	    Graphics graphics = boxImage.createGraphics();
	    try{
	        boxIcon.paintIcon(checkbox, graphics, 0, 0);
	    }finally{
	        graphics.dispose();
	    }
	    ImageIcon newBoxImage = new ImageIcon(boxImage);
	    Image finalBoxImage = newBoxImage.getImage().getScaledInstance(
	        boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
	    );
	    checkbox.setIcon(new ImageIcon(finalBoxImage));

	    checkbox.setSelected(true);
	    Icon checkedBoxIcon = UIManager.getIcon("CheckBox.icon");
	    BufferedImage checkedBoxImage = new BufferedImage(
	        checkedBoxIcon.getIconWidth(),  checkedBoxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
	    );
	    Graphics checkedGraphics = checkedBoxImage.createGraphics();
	    try{
	        checkedBoxIcon.paintIcon(checkbox, checkedGraphics, 0, 0);
	    }finally{
	        checkedGraphics.dispose();
	    }
	    ImageIcon newCheckedBoxImage = new ImageIcon(checkedBoxImage);
	    Image finalCheckedBoxImage = newCheckedBoxImage.getImage().getScaledInstance(
	        boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
	    );
	    checkbox.setSelectedIcon(new ImageIcon(finalCheckedBoxImage));
	    checkbox.setSelected(false);
	    checkbox.setSelected(previousState);
	}
	
	public static void repaint(Component component) {
    	if (component instanceof Container) {
	    	for (Component c : ((Container)component).getComponents()) {
	    		repaint(c);
	    	}
    	}
    	component.revalidate();
    	component.repaint();
    }
}
