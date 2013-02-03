package it.unifitools.unifinetlogin;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;

public class Worker extends Thread{
    String matr, pass;
    int agg;
    
    public Worker(String matr, String pass, int agg){
        this.matr = matr;
        this.pass = pass;
        this.agg = agg;
    }
       
    public void run(){
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {/*System.out.println("Error SSL Installing");*/}
        
        while(true){
            String[] keys = { "buttonClicked", "redirect_url", "err_flag","info_flag","info_msg","username","password" };
            String[] vals = { "4", "null", "0","0","0", matr, pass };
            String data = "";
            
            try{
                for(int i = 0; i<keys.length; i++){
                    data += URLEncoder.encode(keys[i], "UTF-8")+"="+URLEncoder.encode(vals[i], "UTF-8");
                    if(i != keys.length -1){
                        data += "&";
                    }
                }
                
                URL host = new URL("https://1.1.1.1/login.html");
                URLConnection conn = host.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(data);
                out.flush();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String _page = "";
                String line;
                while ((line = in.readLine()) != null) {
                    _page += line;
                }
                System.out.println(_page);
                out.close();
                in.close();
                
                sleep(agg*1000);
                
            }catch(Exception e){/*System.out.println("Error Connection");*/}
        }
    }
}
