package it.unifitools.unifinetlogin;
 
import java.util.ArrayList;

/**
 * @author giuse
 * 
 * Questa classe gestisce la stampa ed il salvataggio dei log del programma.
 */
public class Log {
	private static int errCount = 0;
	private static int reqCount = 0;
	private static ArrayList<String> logs = new ArrayList<String>();
	public static void i(String log, boolean toCmd){
		if(toCmd){
			System.out.println(log);
		}
		logs.add(log);
	}
	
	public static void reqInc(){
		reqCount++;
	}
	
	public static int getReqNum(){
		return reqCount;
	}
	
	public static void errInc(){
		errCount++;
	}
	
	public static int getErrCount(){
		return errCount;
	}
	
	public static String printLogs(){
		String lgs = "";
		for(int i=0; i<logs.size() ;i++){
			lgs += logs.get(i)+"\n";
		}
		return lgs;
	}
}
