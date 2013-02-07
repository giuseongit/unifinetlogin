package it.unifitools.unifinetlogin;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Config implements Serializable{
	public String matricola;
	public String password;
	public String os;
	public String userdir;
	
	public Config(String matricola, String password, String os, String userdir){
		this.matricola = matricola;
		this.password = password;
		this.os = os;
		this.userdir = userdir;
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
	
	public String getUsrOs(){
		return os;
	}
	
	public String getUsrDir(){
		return userdir;
	}
	
}
