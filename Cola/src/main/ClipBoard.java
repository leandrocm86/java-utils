package main;

import system.SystemTrayFrame;
import system.SystemUtils;
import utils.Str;

public class ClipBoard {
	
	public static void main(String[] args) {
		Str fileName = new Str(SystemUtils.getSystemPath() + "clipboard.txt");
		Clip clip = new Clip(fileName);
		SystemTrayFrame frame = new SystemTrayFrame("Clipboard", SystemUtils.getSystemPath() + "clipboard.png");
		frame.addListener(clip);
	}
	

}
