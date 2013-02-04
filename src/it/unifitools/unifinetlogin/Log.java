package it.unifitools.unifinetlogin;
 
import java.util.ArrayList;

/**
 * 
 * TODO: net logging
 *
 */
public class Log {
	private static ArrayList<String> logs = new ArrayList<String>();
	public static void i(String log, boolean toCmd){
		if(toCmd){
			System.out.println(log);
		}
		logs.add(log);
	}
	
	public static String printLogs(){
		String lgs = "";
		for(int i=0; i<logs.size() ;i++){
			lgs += logs.get(i)+"\n";
		}
		return lgs;
	}
}
