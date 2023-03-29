package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Frame;
import javax.swing.JProgressBar;
import java.awt.Font;

public class Splash extends JFrame {

	private JPanel contentPane;	
	private static JProgressBar progressBar = new JProgressBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);	
					
					
				
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	
		
	
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
	    setUndecorated(true);
		setResizable(false);
		
		setBounds(100, 100, 573, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 139));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setFont(new Font("Roboto", Font.PLAIN, 18));
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 559, 573, 41);
		contentPane.add(progressBar);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 0, 128));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\69b07978551ea776660c33937c11a448_icon.png"));
		lblNewLabel.setBounds(31, 40, 512, 512);
		contentPane.add(lblNewLabel);
		tarea.start();
	

	}
	
	public void cerrar() {
		
		this.dispose();
	}
	
	public void ShowMain() {
		MainPanel mainpanel =  new MainPanel();
		mainpanel.setVisible(true);
	}
	
    Thread tarea = new Thread(new Runnable() {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                try {
                    Thread.sleep(30); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cerrar(); //Cerrar el splash
            ShowMain(); //Mostrar la app 
    	
        }
    });
    
   


}
