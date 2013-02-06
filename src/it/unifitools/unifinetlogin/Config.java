package it.unifitools.unifinetlogin;

import java.io.Serializable;

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
