package it.unifitools.unifinetlogin;

import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;

public class FileHandle {
	public static boolean saveToFile(String toSend, String path, boolean log){
		try{
            PrintWriter out = new PrintWriter(new FileWriter(path));
            out.write(toSend);
            out.flush();
            out.close();
            return true;
        }catch(Exception e){
        	Log.i("Errore durante il salvataggio del file:\n"+e, log);
        	return false;
        }
	}
	
	public static String readFromFile(String path, boolean log){
		try{
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line;
            String file = "";
            while ((line = in.readLine()) != null) {
            	file += line;
            }
            in.close();
            return file;
        }catch(Exception e){
        	Log.i("Errore nell'apertura del file:\n"+e, log);
        	return null;
        }
	}
	
	public static boolean cfgToFile(Config cfg, String path, boolean log){
		try{
			FileOutputStream out = new FileOutputStream(path);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(cfg);
			objOut.flush();
			objOut.close();
			out.close();
			return true;
		}catch(Exception e){
			Log.i("Errore durante il salvataggio del file:\n"+e, log);
			return false;
		}
	}
	
	public static Config cfgFromFile(String path, boolean log){
		try{
			FileInputStream in = new FileInputStream(path);
			ObjectInputStream objIn = new ObjectInputStream(in);
			Config cfg = (Config) objIn.readObject();
			objIn.close();
			in.close();
			return cfg;
		}catch(Exception e){
			Log.i("Errore nell'apertura del file:\n"+e, log);
			return null;
		}
	}
}
