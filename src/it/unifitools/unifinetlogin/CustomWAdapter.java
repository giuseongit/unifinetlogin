package it.unifitools.unifinetlogin;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomWAdapter extends WindowAdapter {
	private boolean toFile;
	private boolean log;
	
	public CustomWAdapter(boolean toFile, boolean log){
		this.toFile = toFile;
		this.log = log;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(toFile){
			FileHandle.saveToFile(Log.printLogs(), "unifilog.txt", false);
		}
		Log.i("Program stopped.", log);
		System.exit(0);
	}
}
