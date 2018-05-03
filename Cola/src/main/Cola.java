package main;

import system.SystemTrayFrame;

public class Cola {
	
	public static void main(String[] args) {
		Clip clip = new Clip();
		SystemTrayFrame frame = new SystemTrayFrame("Cola");
		frame.addListener(clip);
	}
	

}
