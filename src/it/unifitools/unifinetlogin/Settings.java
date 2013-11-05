package it.unifitools.unifinetlogin;

public class Settings implements SettingsMBean {
	private int os;
	private Config cfg;
	private Gui g;
	private Worker w;
	
	public Settings(Gui g){
		this.g = g;
		os = OSProbe.getOs();
	}
	
	public void update(){
		if(g.getCfg() != null){
			cfg = g.getCfg();
		}
		if(g.getWorker() != null){
			w = g.getWorker();
		}
	}
	
	public String getOs(){
		return os == OSProbe.OS_WIN ? "Windows" : os == OSProbe.OS_MAC ? "Mac" : os == OSProbe.OS_GNU ? "linux" : "err";
	}
	
	public int getErrCount(){
		return Log.getErrCount();
	}
	
	public int getReqCount(){
		return Log.getReqNum();
	}
	
	public String getMatricola(){
		return cfg != null ? cfg.getMatr() : "";
	}
	
	public String getPwd(){
		return cfg != null ? cfg.getPwd() : "";
	}
	
	public int getWait() {
		return  w != null ? w.getWait() : 0;
	}
	
	public String getTitle(){
		return g != null ? g.getTitle() : "";
	}
	
	public void setTitle(String k){
		if(g != null){
			g.setTitle(k);
		}
	}
	
	public void setWait(int t) {
		if(w != null){
			w.setWait(t);
		}
	}
}
