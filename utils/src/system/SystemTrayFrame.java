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

public class SystemTrayFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	TrayIcon trayIcon;
    SystemTray tray;
    public SystemTrayFrame(String name){
        super(name);
//        System.out.println("creating instance");
//        try{
//            System.out.println("setting look and feel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }catch(Exception e){
//            System.out.println("Unable to set LookAndFeel");
//        }
        if(SystemTray.isSupported()){
//            System.out.println("system tray supported");
            tray=SystemTray.getSystemTray();

            Image image=Toolkit.getDefaultToolkit().getImage("/media/faisal/DukeImg/Duke256.png");
            ActionListener exitListener=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Exiting....");
                    System.exit(0);
                }
            };
            PopupMenu popup=new PopupMenu();
            MenuItem defaultItem=new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem=new MenuItem("Open");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);
            trayIcon=new TrayIcon(image, name, popup);
            trayIcon.setImageAutoSize(true);
        }else{
//            System.out.println("system tray not supported");
        }
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState()==ICONIFIED){
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
//                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
//                        System.out.println("unable to add to tray");
                    }
                }
        if(e.getNewState()==7){
                    try{
            tray.add(trayIcon);
            setVisible(false);
//            System.out.println("added to SystemTray");
            }catch(AWTException ex){
//            System.out.println("unable to add to system tray");
        }
            }
        if(e.getNewState()==MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    setVisible(true);
//                    System.out.println("Tray icon removed");
                }
                if(e.getNewState()==NORMAL){
                    tray.remove(trayIcon);
                    setVisible(true);
//                    System.out.println("Tray icon removed");
                }
            }
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));

//        setVisible(true);
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}