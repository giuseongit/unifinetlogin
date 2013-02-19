package it.unifitools.unifinetlogin;

import java.io.Serializable;

/**
 * @author giuse
 * 
 * Questa classe contiene matricola e password.
 * Essa implementa l'interfaccia Serializable che indica gli oggetti che possono
 * essere serializzati, cioè salvati su file.
 * 
 * Per garantire un minimo di sicurezza i dati all'interno delle stringhe sono
 * codificati in byte, in modo che pur aprendo il file salvato non è immediata
 * la lettura dei dati.
 **/

@SuppressWarnings("serial")
public class Config implements Serializable{
	public String matricola;
	public String password;
	
	public Config(String matricola, String password){
		try{
			byte[] temp = matricola.getBytes("UTF-8");
			this.matricola = "";
			for(int i = 0; i< temp.length; i++){
				this.matricola += temp[i]+",";
			}
			temp = password.getBytes("UTF-8");
			this.password = "";
			for(int i = 0; i< temp.length; i++){
				this.password += temp[i]+",";
			}
		}catch(Exception e){}
	}
	
	public String getMatr(){
		String vals[] = matricola.split(",");
		byte[] ret = new byte[vals.length];
		for(int i=0; i<vals.length; i++){
			ret[i] = Byte.parseByte(vals[i]);
		}
		return new String(ret);
	}
	
	public String getPwd(){
		String vals[] = password.split(",");
		byte[] ret = new byte[vals.length];
		for(int i=0; i<vals.length; i++){
			ret[i] = Byte.parseByte(vals[i]);
		}
		return new String(ret);
	}
	
}
