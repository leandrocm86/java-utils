package swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class DoubleClickListener implements MouseListener {

	private static final int INTERVALO = 200; // Maximo intervalo (em ms) a ser considerado entre os clicks.
	private long lastClick = 0;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (System.currentTimeMillis() - lastClick < INTERVALO) {
			executar(arg0);
		}
		lastClick = System.currentTimeMillis();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	public abstract void executar(MouseEvent arg0);
}
