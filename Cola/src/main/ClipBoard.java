package main;

import system.SystemTrayFrame;
import system.SystemUtils;

public class ClipBoard {
	
	public static void main(String[] args) {
		Clip clip = new Clip();
		SystemTrayFrame frame = new SystemTrayFrame("Clipboard", SystemUtils.getSystemPath() + "clipboard.png");
		frame.addListener(clip);
	}
	

}
