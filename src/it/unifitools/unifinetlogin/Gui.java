package it.unifitools.unifinetlogin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	private Worker g;
    private JPasswordField password;
    private JFormattedTextField matricola;
    private JButton start;
    private boolean log;
    
    /**
     * TODO: add icon to JFrame
     */
	public Gui(boolean log, boolean toFile) {
		this.log = log;
		Log.i("Program started.", log);
    	addWindowListener(new CustomWAdapter(toFile, log));
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
			Log.i("Error creating JFormattedTxtField", log);
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
		
		String doc;
        if((doc = FileHandle.readFromFile("unificfg.imp", log)) != null){
        	String data[] = doc.split("#");
        	matricola.setText(data[0]);
            password.setText(data[1]);
        }
        
	}
	
	@SuppressWarnings("deprecation")
	public void startstop(){
		if(start.getText().equals("Start")){
	        if(!check()){
	            javax.swing.JOptionPane.showMessageDialog(this, "Riempire tutti i campi!", "",javax.swing.JOptionPane.WARNING_MESSAGE);
	        }else{
	            start.setText("Stop");
	            String data1 = matricola.getText();
                String data2 = new String(password.getPassword());
                String data = data1+"#"+data2;
                FileHandle.saveToFile(data, "unificfg.imp", log);
	            g = new Worker(matricola.getText(), new String(password.getPassword()), log);
	            g.start();
	        }
	    }else{
	        start.setText("Start");
	        g.stop();
	    	Log.i("Thread stopped.", log);
	    }
	}
	
	public boolean check(){
        if(!matricola.getText().equals("") && !new String(password.getPassword()).equals("")){
            return true;
        }else{
            return false;
        }
    }
}
