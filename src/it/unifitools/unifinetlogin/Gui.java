package it.unifitools.unifinetlogin;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JPasswordField;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import it.uni.unilogin.Config;
import it.uni.unilogin.Log;
import it.uni.unilogin.FileHandle;
import it.uni.unilogin.OSProbe;

/**
 * @author giuse
 * 
 * La classe Gui implementa la struttura ed il comportamento dell'interfaccia
 * grafica dell'applicazione.
 */
@SuppressWarnings("serial")
public class Gui extends JFrame {
	private Worker g;
    private JPasswordField password;
    private JFormattedTextField matricola;
    private JButton start;
    private boolean log;
    private boolean toFile;
    private Config cfg;
    private MenuItem trigger;
    private TrayIcon tray;
    private SystemTray trayBar;
    
	public Gui(boolean log, boolean toFile) {
		this.log = log;
		this.toFile = toFile;
		Log.i("Programma avviato.", log);
		setupTray();
    	addWindowListener(new CustomWAdapter(toFile, log, this, trayBar, tray));
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                startstop();
	            }
			}
		});
		setResizable(false);
		setBounds(234,152,234,152);
		setTitle("unifiNetLogin");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matricola");
		lblNewLabel.setBounds(12, 14, 70, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(12, 45, 70, 15);
		getContentPane().add(lblNewLabel_1);
		
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startstop();
			}
		});
		start.setBounds(89, 74, 90, 25);
		getContentPane().add(start);
		try{
			matricola = new JFormattedTextField(new MaskFormatter("#######"));
			matricola.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
		                startstop();
		            }
				}
			});
		}catch(Exception e){
			Log.i("Errore nella creazione della JFormattedTxtField", log);
			matricola = new JFormattedTextField();
		}
		
		matricola.setBounds(89, 9, 123, 25);
		getContentPane().add(matricola);
		
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                startstop();
	            }
			}
		});
		password.setBounds(89, 43, 123, 25);
		getContentPane().add(password);
		
		setIconImage(new ImageIcon(this.getClass().getResource("/conn.png")).getImage());
				
		int os = OSProbe.getOs();
		String path = OSProbe.getHome();
		path = os == OSProbe.OS_GNU ? path+"/.unificfg.imp" : os == OSProbe.OS_WIN ? path+"\\unificfg.imp" : "unificfg.imp";
		if((cfg = FileHandle.cfgFromFile(path, log)) != null){
			matricola.setText(cfg.getMatr());
			password.setText(cfg.getPwd());
		}
	}
	
	private void startstop(){
		if((start.getText().equals("Start"))||(trigger.getLabel().equals("Start"))){
	        if(!check()){
	            javax.swing.JOptionPane.showMessageDialog(this, "Riempire tutti i campi!", "",javax.swing.JOptionPane.WARNING_MESSAGE);
	        }else{
	        	int os = OSProbe.getOs();
	        	String usrPath = OSProbe.getHome();
	            start.setText("Stop");
	            trigger.setLabel("Stop");
	            String data1 = matricola.getText();
                String data2 = new String(password.getPassword());
                cfg = new Config(data1, data2);
                usrPath = os == OSProbe.OS_GNU ? usrPath+"/.unificfg.imp" : os == OSProbe.OS_WIN ? usrPath+"\\unificfg.imp" : "unificfg.imp";
                FileHandle.cfgToFile(cfg, usrPath, log, os);
	            g = new Worker(matricola.getText(), new String(password.getPassword()), log);
	            g.start();
	        }
	    }else{
	        start.setText("Start");
	        trigger.setLabel("Start");
	        g.stopThread();
	    	Log.i("Thread stopped.", log);
	    }
	}
	
	/**
	 * Metodo che controlla che tutti i campi siano riempiti. 
	 */
	private boolean check(){
        if(!matricola.getText().equals("") && !new String(password.getPassword()).equals("")){
            return true;
        }else{
            return false;
        }
    }
	
	private void setupTray(){
		trigger = new MenuItem("Start");
		if(!SystemTray.isSupported()){
			Log.i("Impossibile usare la trayBar", log);
			return;
		}
		final PopupMenu menu = new PopupMenu();
		Image img = new ImageIcon(this.getClass().getResource("/conn.png")).getImage();
		if(OSProbe.getOs() == OSProbe.OS_GNU){
			img = img.getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING);
		}else if(OSProbe.getOs() == OSProbe.OS_WIN){
			img = img.getScaledInstance(18, 18, Image.SCALE_AREA_AVERAGING);
		}
		tray = new TrayIcon(img, "Unifinetlogin", menu);
		trayBar = SystemTray.getSystemTray();
		
		MenuItem show = new MenuItem("Mostra");
        MenuItem exit = new MenuItem("Esci");
        
        menu.add(show);
        menu.addSeparator();
        menu.add(trigger);
        menu.addSeparator();
        menu.add(exit);
       
        tray.setPopupMenu(menu);
		
		show.addActionListener(new CustomAListener(toFile, log, this, trayBar, tray));
		
		trigger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startstop();
            }
        });
		
		exit.addActionListener(new CustomAListener(toFile, log, this, trayBar, tray));
		tray.addMouseListener(new CustomAListener(toFile, log, this, trayBar, tray));
	}
}
