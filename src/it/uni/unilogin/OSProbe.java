package it.uni.unilogin;

/**
 * @author giuse
 * 
 * Questa classe fornisce informazioni sul sistema, in particolare: tipo di
 * sistema e cartella principale (home) dell'utente.
 */
public class OSProbe {
	public static final int OS_ERR = 0;
	public static final int OS_WIN = 1;
	public static final int OS_GNU = 2;
	public static final int OS_MAC = 3;
	
	public static int getOs(){
		String os_temp = System.getProperty("os.name").toLowerCase();
		
		if(os_temp.contains("win"))
			return OS_WIN;
		else if(os_temp.contains("nux")||os_temp.contains("nix"))
			return OS_GNU;
		else if(os_temp.contains("mac"))
			return OS_MAC;
		else
			return OS_ERR;
		
	}
	
	public static String getHome(){
		return System.getProperty("user.home");
	}
	
}
