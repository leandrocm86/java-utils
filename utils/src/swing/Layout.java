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
	
	//AAAB
	//CCCB
	//CCCB
	//DDDB
	//DDDB
	public static final int FORMATO_3 = 3;
	
	//AAAB
	//CCCB
	//CCCB
	//DDDD
	//DDDD
	public static final int FORMATO_4 = 4;
	
	private JPanel A;
	private JPanel B;
	private JPanel C;
	private JPanel D;
	
	
	/**
	 * @param formato - Codigo do formato pre-definido a ser seguido na moldura.
	 * @param menorAltura - Porcentagem da menor altura.
	 * @param menorLargura - Porcentagem da menor largura.
	 */
	public Layout(int formato, int menorAltura, int menorLargura) {
		super(getRelativeLayout(formato));
		
		// Formatos com sublayouts. Nao precisam iniciar alguns paineis que serao intanciados pelos sublayouts.
		if (formato != FORMATO_4) {
			this.A = new JPanel(SwingUtils.createLayout(0)); // O Layout aqui eh irrelevante, mas tem que ter algum para preencher tudo.
			this.B = new JPanel(SwingUtils.createLayout(0));
			this.C = new JPanel(SwingUtils.createLayout(0));
		}
		
		this.D = new JPanel(SwingUtils.createLayout(0));
		
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
			case FORMATO_3: {
				JPanel ACD = new JPanel(SwingUtils.createLayout(RelativeLayout.Y_AXIS));
				ACD.add(A, menorY);
				ACD.add(C, maiorY/2);
				ACD.add(D, maiorY/2);
				super.add(ACD, maiorX);
				super.add(B, menorX);
			} break;
			case FORMATO_4: {
				Layout ACB = new Layout(FORMATO_1, menorAltura, menorLargura);
				this.A = ACB.getA();
				this.B = ACB.getB();
				this.C = ACB.getC();
				super.add(ACB, maiorY*1.1f/2);
				super.add(D, maiorY*0.9f/2);
			} break;
		}
	}
	
	private static LayoutManager getRelativeLayout(int formato) {
		switch(formato) {
		
			case FORMATO_1:
			case FORMATO_3:
				return SwingUtils.createLayout(RelativeLayout.X_AXIS);
			case FORMATO_2:
			case FORMATO_4:
				return SwingUtils.createLayout(RelativeLayout.Y_AXIS);
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
	
	public JPanel getD() {
		return D;
	}
	
	public void setA(JComponent component) {
		this.setComponent(component, A);
	}
	
	public void setB(JComponent component) {
		this.setComponent(component, B);
	}
	
	public void setC(JComponent component) {
		this.setComponent(component, C);
	}
	
	public void setD(JComponent component) {
		this.setComponent(component, D);
	}
	
	// Em vez de mudarmos os paineis, apenas colocamos os componentes do usuario dentro dos paineis da moldura (para manter as posicoes).
	private void setComponent(JComponent component, JPanel panel) {
		panel.removeAll();
		panel.add(component, 1f); // O tamanho aqui eh irrelevante, mas eh necessario para o preenchimento total do espaco.
	}
}
