package system;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import observer.Event;
import observer.Events;
import swing.SwingUtils;

public class SystemTrayFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final String EVENT_WINDOW_MINIMIZED = "WINDOW_MINIMIZED";
	
	private TrayIcon trayIcon;
    private SystemTray tray;
    
    public SystemTrayFrame(String name) {
        super(name);
//        System.out.println("creating instance");
//        try{
//            System.out.println("setting look and feel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }catch(Exception e){
//            System.out.println("Unable to set LookAndFeel");
//        }
        if (SystemTray.isSupported()) {
            Image image = Toolkit.getDefaultToolkit().getImage("");
            this.setTrayImage(image, "Sem título");
        } else {
            SwingUtils.showMessage("ERRO! SystemTray nao suportado!");
        }
        
        super.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                        Events.notify(new Event(EVENT_WINDOW_MINIMIZED, this));
//                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
//                        System.out.println("unable to add to tray");
                    }
                }
		        if (e.getNewState() == 7) {
		        	try {
			            tray.add(trayIcon);
			            setVisible(false);
		//            System.out.println("added to SystemTray");
		            } catch (AWTException ex) {
		//            System.out.println("unable to add to system tray");
		            }
		        }
		        if (e.getNewState() == MAXIMIZED_BOTH) {
//                    tray.remove(trayIcon);
                    setVisible(true);
//                    System.out.println("Tray icon removed");
                }
                if (e.getNewState() == NORMAL) {
//                    tray.remove(trayIcon);
                    setVisible(true);
//                    System.out.println("Tray icon removed");
                }
            }
        });
        
        super.setIconImage(Toolkit.getDefaultToolkit().getImage("")); // Icone da janela
    }
    
    public void setTrayImage(Image image, String title) {
    	SystemTray.getSystemTray().remove(this.trayIcon);
    	
    	ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exiting....
            }
        };
        PopupMenu popup = new PopupMenu();
        MenuItem defaultItem = new MenuItem("Exit");
        defaultItem.addActionListener(exitListener);
        popup.add(defaultItem);
    	
        this.trayIcon = new TrayIcon(image, title, popup);
        this.trayIcon.setImageAutoSize(true);
        
        // Adicionando icone na Tray
        try {
        	SystemTray.getSystemTray().add(trayIcon);
		} catch (AWTException e1) {
			SwingUtils.showMessage("Erro ao tentar colocar icone na SystemTray: " + e1.getMessage());
		}
    }
}