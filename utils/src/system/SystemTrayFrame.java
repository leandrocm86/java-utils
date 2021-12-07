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
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import observer.Evento;
import observer.Events;
import swing.SwingUtils;

public class SystemTrayFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final String EVENT_WINDOW_MINIMIZED = "WINDOW_MINIMIZED";
	
	private String imgURL;
	private TrayIcon trayIcon;
    private SystemTray tray;
    
    public SystemTrayFrame(String name) {
    	this(name, "");
    }
    
    public SystemTrayFrame(String name, String imgURL) {
    	this(name, imgURL, false);
    }
    
    public SystemTrayFrame(String name, String imgURL, boolean restore) {
        super(name);
        
//        System.out.println("creating instance");
//        try{
//            System.out.println("setting look and feel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }catch(Exception e){
//            System.out.println("Unable to set LookAndFeel");
//        }
        if (SystemTray.isSupported()) {
        	this.imgURL = imgURL;
            this.tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(this.imgURL);
//            Image image = this.scaleImage(512, 512, this.imgURL);
            this.setTrayImage(image, name, restore);
        } else {
            SwingUtils.showMessage("ERRO! SystemTray nao suportado!");
        }
        
        super.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
            	tray.remove(trayIcon);
            	if (e.getNewState() == MAXIMIZED_BOTH || e.getNewState() == NORMAL) {
                  setVisible(true);
            	}
            	else {
                    try {
                        tray.add(trayIcon);
                    } catch (AWTException ex) {
                        SwingUtils.showMessage("Nao foi possivel adicionar icone na SystemTray");
                    }
                    setVisible(false);
                    if (e.getNewState() == ICONIFIED)
                    	Events.notify(new Evento(EVENT_WINDOW_MINIMIZED, this));
                }
            }
        });
        super.setIconImage(Toolkit.getDefaultToolkit().getImage(this.imgURL)); // Icone da janela
    }
    
    public void setTrayImage(Image image, String title) {
    	this.setTrayImage(image, title, false);
    }
    
    public void setTrayImage(Image image, String title, boolean restore) {
    	this.tray.remove(this.trayIcon);
    	
    	ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exiting....
            }
        };
        PopupMenu popup = new PopupMenu();
        MenuItem defaultItem = new MenuItem("Exit");
        defaultItem.setFont(SwingUtils.getDefaultFont());
        defaultItem.addActionListener(exitListener);
        popup.add(defaultItem);
        
        if (restore) {
        	ActionListener restoreListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	restore();
                }
            };
            MenuItem retoreOption = new MenuItem("Restore");
            retoreOption.setFont(SwingUtils.getDefaultFont());
            retoreOption.addActionListener(restoreListener);
            popup.add(retoreOption);
        }
    	
        this.trayIcon = new TrayIcon(image, title, popup);
        this.trayIcon.setImageAutoSize(true);
        
        // Adicionando icone na Tray.
        try {
        	this.tray.add(this.trayIcon);
		} catch (AWTException e1) {
			SwingUtils.showMessage("Erro ao tentar colocar icone na SystemTray: " + e1.getMessage());
		}
    }
    
    public void updateIcon(Image image, String tooltip) {
    	this.trayIcon.setImage(image);
    	this.trayIcon.setToolTip(tooltip);
    }
    
    public void addListener(MouseListener listener) {
    	this.trayIcon.addMouseListener(listener);
    }
    
    public void restore() {
    	setState(NORMAL);
    	setVisible(true);
    }
    
//    private BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
//        BufferedImage bi = null;
//        try {
//            ImageIcon ii = new ImageIcon(filename);//path to image
//            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = (Graphics2D) bi.createGraphics();
//            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
//            g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return bi;
//    }
}