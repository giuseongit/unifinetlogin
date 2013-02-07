package it.unifitools.unifinetlogin;

import java.util.ArrayList;

/**
 * 
 * TODO: net logging
 *
 */
public class Log {
	private static ArrayList<String> logs = new ArrayList<String>();
	public static void i(String log){
		System.out.println(log);
		logs.add(log);
	}
	
	
}
