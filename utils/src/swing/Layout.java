package swing;

import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Layout extends JPanel {
	
	private static final long serialVersionUID = 1L;

	//AAAB
	//CCCB
	//CCCB
	public static final int FORMATO_1 = 1;
	
	//AAAA
	//BBBC
	//BBBC
	public static final int FORMATO_2 = 2;
	
	
	private JPanel A = new JPanel(SwingUtils.createLayout(RelativeLayout.X_AXIS)); // O Layout aqui eh irrelevante, mas tem que ter algum para preencher tudo.
	private JPanel B = new JPanel(SwingUtils.createLayout(RelativeLayout.X_AXIS));
	private JPanel C = new JPanel(SwingUtils.createLayout(RelativeLayout.X_AXIS));
	
	
	public Layout(int formato, int menorAltura, int menorLargura) {
		super(getRelativeLayout(formato));
		
		Float menorX = new Float(menorLargura);
		Float menorY = new Float(menorAltura);
		Float maiorX = new Float(100 - menorLargura);
		Float maiorY = new Float(100 - menorAltura);
		
		switch(formato) {
			case FORMATO_1: {
				JPanel AC = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS));
				AC.add(A, menorY);
				AC.add(C, maiorY);
				super.add(AC, maiorX);
				super.add(B, menorX);
			} break;
			case FORMATO_2: {
				super.add(A, menorY);
				JPanel BC = new JPanel(SwingUtils.createLayout(RelativeLayout.X_AXIS));
				BC.add(B, maiorX);
				BC.add(C, menorX);
				super.add(BC, maiorY);
			} break;
		}
	}
	
	private static LayoutManager getRelativeLayout(int formato) {
		switch(formato) {
			case FORMATO_1: return SwingUtils.createLayout(RelativeLayout.X_AXIS);
			case FORMATO_2: return SwingUtils.createLayout(RelativeLayout.Y_AXIS);
			default: throw new IllegalArgumentException("Formato invalido de layout!");
		}
	}
	
	public JPanel getA() {
		return A;
	}
	
	public JPanel getB() {
		return B;
	}
	
	public JPanel getC() {
		return C;
	}
	
	// Em vez de mudarmos os paineis, apenas colocamos os componentes do usuario dentro dos paineis da moldura (para manter as posicoes).
	
	public void setA(JComponent component) {
		A.add(component, 1f); // O tamanho aqui eh irrelevante, mas eh necessario para o preenchimento total do espaco.
	}
	
	public void setB(JComponent component) {
		B.add(component, 1f); // O tamanho aqui eh irrelevante, mas eh necessario para o preenchimento total do espaco.
	}
	
	public void setC(JComponent component) {
		C.add(component, 1f); // O tamanho aqui eh irrelevante, mas eh necessario para o preenchimento total do espaco.
	}
}