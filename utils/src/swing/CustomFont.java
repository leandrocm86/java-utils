package swing;

public class CustomFont extends java.awt.Font {

	private static final long serialVersionUID = 1L;

	public CustomFont(String name, int style, int size) {
		super(name, style, size);
	}
	
	public CustomFont(String name, int size) {
		super(name, 0, size);
	}
}
