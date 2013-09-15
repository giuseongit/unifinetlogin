package it.unifitools.unifinetlogin;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author giuse
 * 
 * La classe CustonWAdapter gestisce i comportamenti da avere quando si
 * chide la finestra e quando la si minimizza.
 */
public class CustomWAdapter extends WindowAdapter {
	private boolean toFile;
	private boolean log;
	private Gui f;
	private SystemTray trayBar;
	private TrayIcon tray;
	
	public CustomWAdapter(boolean toFile, boolean log, Gui f, SystemTray trayBar, TrayIcon tray){
		this.toFile = toFile;
		this.log = log;
		this.f = f;
		this.trayBar = trayBar;
		this.tray = tray;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(toFile){
			FileHandle.saveToFile(Log.printLogs(), "unifilog.txt", false, OSProbe.OS_ERR);
		}
		Log.i("Programma fermato.", log);
		System.exit(0);
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		f.setVisible(false);
		try {
			trayBar.add(tray);
			Log.i("Minimizzato nella Tray.", log);
		} catch (Exception ex) { Log.i("Errore nel caricamento della trayIcon.", log);}
	}
}
