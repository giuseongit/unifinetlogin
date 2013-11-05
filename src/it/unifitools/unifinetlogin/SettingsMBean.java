package it.unifitools.unifinetlogin;

public interface SettingsMBean {
	public void update();
	public String getOs();
	public String getMatricola();
	public String getPwd();
	public int getErrCount();
	public int getReqCount();
	public int getWait();
	public void setWait(int t);
	public void setTitle(String k);
	public String getTitle();
}
