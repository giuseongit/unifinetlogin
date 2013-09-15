package it.unifitools.unifinetlogin;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author giuse
 *	
 * Questa classe implementa un ActionListener ed un MouseListener, implementando
 * i comportamenti da avere quando si minimizza nella system tray o lo si
 * ripristina da essa.
 */
public class CustomAListener implements ActionListener, MouseListener{
	private boolean toFile;
	private boolean log;
	private Gui f;
	private SystemTray trayBar;
	private TrayIcon tray;
	
	public CustomAListener(boolean toFile, boolean log, Gui f, SystemTray trayBar, TrayIcon tray){
		this.toFile = toFile;
		this.log = log;
		this.f = f;
		this.trayBar = trayBar;
		this.tray = tray;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Mostra")){
			Log.i("Ripristinato dalla Tray", log);
			trayBar.remove(tray);
			f.setVisible(true);
		}else if(arg0.getActionCommand().equals("Esci")){
			if(toFile){
				FileHandle.saveToFile(Log.printLogs(), "unifilog.txt", false, OSProbe.OS_ERR);
			}
			Log.i("Programma fermato.", log);
			System.exit(0);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() >= 2){
			Log.i("Ripristinato dalla Tray", log);
			trayBar.remove(tray);
			f.setVisible(true);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
