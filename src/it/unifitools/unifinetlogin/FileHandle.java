package it.unifitools.unifinetlogin;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileHandle {
	public static boolean saveToFile(String toSend, String path){
		try{
            PrintWriter out = new PrintWriter(new FileWriter(path));
            out.write(toSend);
            out.flush();
            out.close();
            return true;
        }catch(Exception e){
        	Log.i("Error saving file:\n"+e);
        	return false;
        }
	}
	
	public static String readFromFile(String path){
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
        	Log.i("Errore nell'apertura del file:\n"+e);
        	return null;
        }
	}
}
