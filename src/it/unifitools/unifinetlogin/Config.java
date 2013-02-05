package it.unifitools.unifinetlogin;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Config implements Serializable{
	public String matricola;
	public String password;
	
	public Config(String matricola, String password){
		this.matricola = matricola;
		this.password = password;
	}
	
	public void setPwd(String password){
		this.password = password;
	}
	
	public String getMatr(){
		return matricola;
	}
	
	public String getPwd(){
		return password;
	}
	
}
