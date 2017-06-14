package swing;

import java.awt.Component;
import java.awt.Container;

public class Fonte extends java.awt.Font {
	
	public static final Fonte ARIAL_20 = new Fonte("Arial", 0, 20);
	public static final Fonte ARIAL_30 = new Fonte("Arial", 0, 30);
	public static final Fonte ARIAL_40 = new Fonte("Arial", 0, 40);
	public static final Fonte ARIAL_60 = new Fonte("Arial", 0, 60);
	public static final Fonte ARIAL_80 = new Fonte("Arial", 0, 80);
	
	public static final Fonte ARIAL_20_BOLD = new Fonte("Arial", 1, 20);
	public static final Fonte ARIAL_30_BOLD = new Fonte("Arial", 1, 30);
	public static final Fonte ARIAL_40_BOLD = new Fonte("Arial", 1, 40);
	public static final Fonte ARIAL_60_BOLD = new Fonte("Arial", 1, 60);
	public static final Fonte ARIAL_80_BOLD = new Fonte("Arial", 1, 80);
	
	public static final Fonte SANS_20 = new Fonte("SansSerif", 0, 20);
	public static final Fonte SANS_30 = new Fonte("SansSerif", 0, 30);
	public static final Fonte SANS_40 = new Fonte("SansSerif", 0, 40);
	public static final Fonte SANS_60 = new Fonte("SansSerif", 0, 60);
	public static final Fonte SANS_80 = new Fonte("SansSerif", 0, 80);
	
	public static final Fonte SANS_20_BOLD = new Fonte("SansSerif", 1, 20);
	public static final Fonte SANS_30_BOLD = new Fonte("SansSerif", 1, 30);
	public static final Fonte SANS_40_BOLD = new Fonte("SansSerif", 1, 40);
	public static final Fonte SANS_60_BOLD = new Fonte("SansSerif", 1, 60);
	public static final Fonte SANS_80_BOLD = new Fonte("SansSerif", 1, 80);

	private static final long serialVersionUID = 1L;

	public Fonte(String nome, int estilo, int tamanho) {
		super(nome, estilo, tamanho);
	}
	
	public Fonte(String nome, int tamanho) {
		super(nome, 0, tamanho);
	}
	
	/**
	 * Seta uma dada fonte para componentes e todos os seus filhos.
	 * Os filhos que ja tiverem uma fonte customizada nao sao alterados.
	 */
	public static void set(Fonte fonte, Component... components) {
		for (Component component : components) {
			component.setFont(fonte);
		    if (component instanceof Container)
		    {
		        for (Component child : ((Container) component).getComponents())
		        {
		        	if (child.getFont() == null || !(child.getFont() instanceof Fonte))
		        		set(fonte, child);
		        }
		    }
		}
	}
	
	/**
	 * Seta a fonte para componentes e todos os seus filhos.
	 * Os filhos que ja tiverem uma fonte customizada nao sao alterados.
	 */
	public void set(Component... components) {
		set(this, components);
	}
}
