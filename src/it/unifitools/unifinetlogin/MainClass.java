package it.unifitools.unifinetlogin;

public class MainClass {
	public static void main(String[] args) {
		String opt1 = "";
		String opt2 = "";
		boolean log = false;
		boolean toFile = false;
		try{
			opt1 = args[0];
			if(opt1.equals("log")){
				log = true;
			}else if(opt1.equals("tofile")){
				toFile = true;
			}else if(opt1.equals("help")){
				help();
			}else{
				System.out.println("Invalid argument: "+opt1);
				help();
			}
		}catch(IndexOutOfBoundsException e){}
		try{
			opt2 = args[1];
			if(opt2.equals("log")){
				if(!log){
					log = true;
				}else{
					System.out.println("log already used!");
					help();
				}
			}else if(opt2.equals("tofile")){
				if(!toFile){
					toFile = true;
				}else{
					System.out.println("tofile already used!");
					help();
				}
			}else{
				System.out.println("Invalid argument: "+opt1);
				help();
			}
		}catch(IndexOutOfBoundsException e){}
		new Gui(log, toFile).setVisible(true);
		
	}
	
	public static void help(){
		System.out.println("Usage: unifinetlogin.jar [log|tofile]\n\tlog\tShow logs in shell\n\ttofile\tSave logs to unifilog.txt\n\thelp\tShow this help\n");
		System.exit(0);
	}

}
