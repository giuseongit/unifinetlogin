package it.unifitools.unifinetlogin;

/**
 * @author giuse
 * 
 * La classe main è l'entry point  dell'intera applicazione. Qui si rileva se sono
 * stati passati dei programmi all'applicazione ed in caso affermativo verranno
 * settate le opportune variabili. Ha anche un piccolo help che ne descrive l'uso.
 */
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
				System.out.println("Parametro invalido: "+opt1);
				help();
			}
		}catch(IndexOutOfBoundsException e){}
		try{
			opt2 = args[1];
			if(opt2.equals("log")){
				if(!log){
					log = true;
				}else{
					System.out.println("log gia' usato!");
					help();
				}
			}else if(opt2.equals("tofile")){
				if(!toFile){
					toFile = true;
				}else{
					System.out.println("tofile gia' usato!");
					help();
				}
			}else{
				System.out.println("Parametro invalido: "+opt1);
				help();
			}
		}catch(IndexOutOfBoundsException e){}
		new Gui(log, toFile).setVisible(true);
		
	}
	
	public static void help(){
		System.out.println("Uso: unifinetlogin.jar [log|tofile]\n\tlog\tMostra i log nella console\n\ttofile\tSalva i log in unifilog.txt\n\thelp\tMostra questa schermata\n");
		System.exit(0);
	}

}
