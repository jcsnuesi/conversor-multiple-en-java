package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.ConversionMonedas;
import controllers.Degrees;
import controllers.Funciones;
import controllers.Pesos;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Label;
import java.awt.Canvas;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class MainPanel extends JFrame {

	private JPanel contentPane;
	private static JTextField txtFrom;
	private JTextField txtTo;
	Funciones dataToWork = new Funciones();
	JComboBox<String> cboxSimboloTo = new JComboBox<>();
	JComboBox<String> cboxSimbolo = new JComboBox<>();
	JLabel labeltotal = new JLabel("");
	JSONObject jsonObj = new JSONObject();
	Degrees degree = new Degrees();
	ArrayList<String> opciones =  new ArrayList<String>();
	JLabel lblCurrencyConverter = new JLabel("Conversor de monedas");
	DecimalFormat formatings = new DecimalFormat("#,###.##");
	private Pesos peso = new Pesos();
	Image image;
	JLabel flag1;
	JLabel flag2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
				
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws MalformedURLException 
	 */
	public MainPanel() {
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\iconoMenunew.jpg"));
		setTitle("Alura convertidor multiple");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 492);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel);
	
		JPanel panelPrograms = new JPanel();
		panelPrograms.setBackground(new Color(255, 255, 255));
		panelPrograms.setBounds(186, 102, 518, 317);
		contentPane.add(panelPrograms);
		panelPrograms.setLayout(null);
		
		
		cboxSimboloTo.setFont(new Font("Roboto", Font.PLAIN, 14));		
		cboxSimboloTo.setBounds(150, 162, 156, 43);
		cboxSimboloTo.setModel(new DefaultComboBoxModel(dataToWork.currencyNames().toArray()));
		panelPrograms.add(cboxSimboloTo);
		
		
		
		cboxSimbolo.setFont(new Font("Roboto", Font.PLAIN, 14));		
		cboxSimbolo.setBounds(150, 73, 156, 46);
		cboxSimbolo.setModel(new DefaultComboBoxModel(dataToWork.currencyNames().toArray()));
		panelPrograms.add(cboxSimbolo);
		
		//Agregar las banderas al iniciar programa
		//FLAG 1 ICON
		
	
		txtTo = new JTextField();
		txtTo.setFont(new Font("Roboto", Font.BOLD, 24));
		txtTo.setBounds(336, 162, 107, 43);
		txtTo.setText("0");
		txtTo.setColumns(10);
		panelPrograms.add(txtTo);
				
		
		
		labeltotal.setFont(new Font("Roboto", Font.BOLD, 18));
		labeltotal.setBounds(62, 243, 377, 37);
		panelPrograms.add(labeltotal);
		
		txtTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String txtFromAmount = txtTo.getText();
				
				switch (lblCurrencyConverter.getText()) {
				
				case "Conversor de monedas":
					
					String cboxTo = codeISO(cboxSimboloTo.getSelectedItem().toString()); 
					String cboxFrom = codeISO(cboxSimbolo.getSelectedItem().toString());
					
					try {
						
						dataToWork.validacion(Double.parseDouble(txtFromAmount));
						dataToWork.converted(cboxFrom,cboxTo,txtFromAmount);
						labeltotal.setText(formatings.format(Double.parseDouble(txtFromAmount)) + " " + cboxTo + " son equivalen a: " + dataToWork.getTotal() + " " +cboxFrom);						
						txtFrom.setText(dataToWork.getTotalInvertido());
						
						
					} catch (NumberFormatException e2) {
						
						txtFrom.setText(txtTo.getText());
						labeltotal.setText("Solo numeros positivos"); 
						labeltotal.setForeground(Color.BLACK);
						labeltotal.setForeground(new Color(255, 255, 255));
					}
					
				
					
					break;

				case "Conversor de temperatura":
					

					try {
						dataToWork.validacion(Double.parseDouble(txtFromAmount));
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Solo numeros", "Error", JOptionPane.ERROR_MESSAGE);

	 
					}

				changeComboBox(cboxSimboloTo,Double.parseDouble(txtFromAmount));
					 
					break;
					
				case "Conversor de peso":
					DecimalFormat format = new DecimalFormat("##.###");
					
					peso.setCantidad(Double.parseDouble(txtTo.getText()));
					peso.setHacia((String)cboxSimbolo.getSelectedItem());
					String totalFormated = format.format(peso.choise((String)cboxSimboloTo.getSelectedItem()));
					labeltotal.setBounds(32, 243, 430, 30);
					labeltotal.setText(txtTo.getText() + " " + cboxSimboloTo.getSelectedItem() + " equivalen a: " +  totalFormated + " " + cboxSimbolo.getSelectedItem()); 
					txtFrom.setText(totalFormated);
					

					break;
				}
				
			}
			
		});
		
	
	
		
		
		txtFrom = new JTextField();
		txtFrom.setFont(new Font("Roboto", Font.BOLD, 24));
		txtFrom.setBounds(336, 73, 107, 43);
		txtFrom.setColumns(10);
		txtFrom.setText("0");
		panelPrograms.add(txtFrom);
			
				
	flag1 = new JLabel("");
	flag1.setBounds(62, 73, 78, 46);
	panelPrograms.add(flag1);
	
	flag2 = new JLabel("");
	flag2.setBounds(62, 159, 78, 46);
	panelPrograms.add(flag2);
	
	// FLAG 1 ICON
	String CurrSimbolF = codeISO(cboxSimbolo.getSelectedItem().toString());				
	URL flagImgF = dataToWork.flags(CurrSimbolF);
	
	
	//FLAG 2 ICON
	String CurrSimbolT =codeISO(cboxSimboloTo.getSelectedItem().toString());	
	URL flagImgT = dataToWork.flags(CurrSimbolT);
	
	flag1.setIcon(getImageIcon(flagImgF));
	flag2.setIcon(getImageIcon(flagImgT));
	
	
	lblCurrencyConverter.setFont(new Font("Roboto", Font.BOLD, 22));
	lblCurrencyConverter.setBounds(146, 10, 293, 43);
	panelPrograms.add(lblCurrencyConverter);
	
	
	JPanel panelOpciones = new JPanel();
	panelOpciones.setBackground(new Color(0, 0, 139));
	panelOpciones.setBounds(0, 0, 185, 419);
	contentPane.add(panelOpciones);
	panelOpciones.setLayout(null);
			
	JLabel lbllogo = new JLabel();
	lbllogo.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\logo.png"));

   
    lbllogo.setBounds(45, 10, 130, 127);
    panelOpciones.add(lbllogo);	
    
	txtFrom.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String txtFromAmount = txtFrom.getText();
		
			switch (lblCurrencyConverter.getText()) {
			
			case "Conversor de monedas":
				
				String cboxTo = codeISO(cboxSimbolo.getSelectedItem().toString());
				String cboxFrom = codeISO(cboxSimboloTo.getSelectedItem().toString());
				
				try {
					dataToWork.validacion(Double.parseDouble(txtFromAmount));
					dataToWork.converted(cboxFrom,cboxTo,txtFromAmount);
					
					labeltotal.setText(formatings.format(Double.parseDouble(txtFromAmount)) + " " + cboxTo + " son equivalen a: " + dataToWork.getTotal() + " " +cboxFrom);						
					labeltotal.setForeground(Color.BLACK);
					txtTo.setText(dataToWork.getTotalInvertido());
				} catch (NumberFormatException e2) {
					txtTo.setText(txtFrom.getText());						
					labeltotal.setText("Solo numeros positivos"); 
					labeltotal.setForeground(Color.RED);
				}
							
			
				
				break;

			case "Conversor de temperatura":
				
				try {
					dataToWork.validacion(Double.parseDouble(txtFromAmount));
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Solo numeros", "Error", JOptionPane.ERROR_MESSAGE);

 
				}

				changeComboBox(cboxSimbolo,Double.parseDouble(txtFromAmount));

			
				 
				break;
				
			case "Conversor de peso":
				DecimalFormat format = new DecimalFormat("##.###");
				
				peso.setCantidad(Double.parseDouble(txtFrom.getText()));
				peso.setHacia((String)cboxSimboloTo.getSelectedItem());
				String totalFormated = format.format(peso.choise((String)cboxSimbolo.getSelectedItem()));
				txtTo.setText(totalFormated);
				labeltotal.setBounds(32, 243, 430, 30);
				labeltotal.setText(txtFrom.getText() + " " + cboxSimbolo.getSelectedItem() + " equivalen a: " +  totalFormated + " " + cboxSimboloTo.getSelectedItem()); 


				
				break;
			}
		
			

			
		}
	});
		
    
    
  //Capturar el elemento  seleccionado del comboboxTO 
    cboxSimboloTo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			cboxSimboloTo.setName("toCombo");
			changeIconValue(cboxSimboloTo,cboxSimbolo);
			

		}
	});
    
    
	//Capturar el elemento  seleccionado del comboboxFROM 
	cboxSimbolo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			cboxSimbolo.setName("fromCombo");
			changeIconValue(cboxSimbolo,cboxSimboloTo);


		}
	});
	
	JButton btnMonedaCambio = new JButton("Monedas");
	btnMonedaCambio.setForeground(new Color(255, 255, 255));
	btnMonedaCambio.setBackground(new Color(30, 144, 255));
	btnMonedaCambio.setHorizontalAlignment(SwingConstants.LEFT);
	btnMonedaCambio.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			lblCurrencyConverter.setText("Conversor de monedas");	
			cboxSimbolo.setModel(new DefaultComboBoxModel(dataToWork.currencyNames().toArray()));
			cboxSimboloTo.setModel(new DefaultComboBoxModel(dataToWork.currencyNames().toArray()));
			changeIconValue(cboxSimbolo,cboxSimboloTo);
			reset();
		}
	});
	btnMonedaCambio.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\dolar.png"));
	btnMonedaCambio.setFont(new Font("Roboto", Font.BOLD, 14));
	btnMonedaCambio.setBounds(0, 159, 185, 50);
	panelOpciones.add(btnMonedaCambio);
	
	JButton btnTemperatura = new JButton("Temperaturas");
	btnTemperatura.setForeground(new Color(255, 255, 255));
	btnTemperatura.setBackground(new Color(255, 0, 0));
	btnTemperatura.setHorizontalAlignment(SwingConstants.LEFT);
	btnTemperatura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			lblCurrencyConverter.setText("Conversor de temperatura");	
			 SwingWorker<Void, String> worker = new SwingWorker<Void, String>(){

				@Override
				protected Void doInBackground() throws Exception {
					cboxSimbolo.removeAllItems();
					cboxSimboloTo.removeAllItems();
					  
					opciones.add("Grados Fahrenheit");
					opciones.add("Grados Celsius");
				
                    for (int i = 0; i <= opciones.size(); i++) {
                    	publish(opciones.get(i));
                      
                    }
					
					
					return null;
				}
				
				 @Override
                 protected void process(java.util.List<String> chunks) {
					 // Agrega los elementos al JComboBox
						
					cboxSimbolo.setModel(new DefaultComboBoxModel(chunks.toArray()));
					cboxSimboloTo.setModel(new DefaultComboBoxModel(chunks.toArray()));
						
					cboxSimbolo.setSelectedItem(chunks.get(0));
					cboxSimboloTo.setSelectedItem(chunks.get(1));
						
                 }
				 
			 };
		
			
	
			
			 reset();
			 worker.execute();
		
		}
		
		
	});
	btnTemperatura.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\termometro.png"));
	btnTemperatura.setFont(new Font("Roboto", Font.BOLD, 14));
	btnTemperatura.setBounds(0, 211, 185, 50);
	panelOpciones.add(btnTemperatura);
	
	JButton btnPeso = new JButton("Peso");
	btnPeso.setForeground(new Color(255, 255, 255));
	btnPeso.setBackground(new Color(153, 50, 204));
	btnPeso.setHorizontalAlignment(SwingConstants.LEFT);
	btnPeso.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			lblCurrencyConverter.setText("Conversor de peso");	
			 SwingWorker<Void, String> worker = new SwingWorker<Void, String>(){

				@Override
				protected Void doInBackground() throws Exception {
					cboxSimbolo.removeAllItems();
					cboxSimboloTo.removeAllItems();
					  // Simula una carga de elementos en el JComboBox
					ArrayList<String>  medidas = new ArrayList<>();
					
					medidas.add("Kilogramos - KG");
					medidas.add("Libras - LB");
					medidas.add("Gramos - G");
					medidas.add("Onzas - OZ");
					
                   for (int i = 0; i <= medidas.size(); i++) {
                   	publish(medidas.get(i));
                     
                   }
					
					
					return null;
				}
				
				 @Override
                protected void process(java.util.List<String> chunks) {
					 // Agrega los elementos al JComboBox
						
					cboxSimbolo.setModel(new DefaultComboBoxModel(chunks.toArray()));
					cboxSimboloTo.setModel(new DefaultComboBoxModel(chunks.toArray()));
						
				
					cboxSimbolo.setSelectedItem(chunks.get(0));
					cboxSimboloTo.setSelectedItem(chunks.get(1));
					
					
                }
				 
			 };
		
			
	
			
			 reset();
			 worker.execute();
			
		}
	});
	btnPeso.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\peso.png"));
	btnPeso.setFont(new Font("Roboto", Font.BOLD, 14));
	btnPeso.setBounds(0, 263, 185, 50);
	panelOpciones.add(btnPeso);
	
	JPanel panel = new JPanel();
	panel.setBorder(new LineBorder(new Color(192, 192, 192)));
	panel.setBackground(new Color(0, 0, 139));
	panel.setForeground(Color.BLACK);
	panel.setBounds(186, 0, 518, 102);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("");
	lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\iconoMenunew-removebg-preview.png"));
	lblNewLabel_1.setBounds(408, 0, 100, 103);
	panel.add(lblNewLabel_1);
	
	JLabel lblTitle = new JLabel("Conversor multiple");
	lblTitle.setForeground(new Color(255, 255, 255));
	lblTitle.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 32));
	lblTitle.setBounds(99, 26, 299, 51);
	panel.add(lblTitle);
	
	JLabel lblNewLabel_2 = new JLabel("Developed by Hector Santos©");
	lblNewLabel_2.setFont(new Font("Roboto", Font.BOLD, 12));
	lblNewLabel_2.setBounds(10, 429, 175, 23);
	contentPane.add(lblNewLabel_2);
	
	JLabel lblNewLabel_4 = new JLabel("Impulsed by Oracle & Alura Latam");
	lblNewLabel_4.setFont(new Font("Roboto", Font.BOLD, 12));
	lblNewLabel_4.setBounds(509, 429, 185, 16);
	contentPane.add(lblNewLabel_4);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(186, 102, 518, 317);
	contentPane.add(panel_1);
		
		
	}
	
    private void changeIconValue(JComboBox cboxF, JComboBox cboxT) {
    	
    	String changer = lblCurrencyConverter.getText();

		switch (changer) {
		case "Conversor de monedas":
						
				//FLAG 1 ICON
				String CurrSimbolF = codeISO(cboxF.getSelectedItem().toString());				
				URL flagImgF = dataToWork.flags(CurrSimbolF);
				
				
				//FLAG 2 ICON
				String CurrSimbolT =codeISO(cboxT.getSelectedItem().toString());	
				URL flagImgT = dataToWork.flags(CurrSimbolT);
				
				
				if (cboxF.getName() == "fromCombo") {
					flag1.setIcon(getImageIcon(flagImgF));
					flag2.setIcon(getImageIcon(flagImgT));
					
				}
				
				if (cboxF.getName() == "toCombo") {
					flag1.setIcon(getImageIcon(flagImgT));
					flag2.setIcon(getImageIcon(flagImgF));
					
				}
				
				
			break;
			
		case "Conversor de temperatura"
		:	
			ImageIcon imageIcon = new ImageIcon();
			ImageIcon imageIcon1 = new ImageIcon();
			
			if (cboxF.getName() == "fromCombo") {
				 imageIcon = new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\fpng.png");
				 imageIcon1 = new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\c.png");
				
			}else if(cboxF.getName() == "toCombo"){
				
				 imageIcon =  new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\c.png");
				 imageIcon1 = new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\fpng.png");
				
				
			}
			
			 
			if (cboxF.getSelectedIndex() == 0) {
			
				flag1.setIcon(labelsImg(imageIcon));
				flag2.setIcon(labelsImg(imageIcon1));
				cboxT.setSelectedItem(opciones.get(cboxF.getSelectedIndex()+1));
				
			}
			
			if (cboxF.getSelectedIndex() == 1) {
				
				flag1.setIcon(labelsImg(imageIcon1));
				flag2.setIcon(labelsImg(imageIcon));
				cboxT.setSelectedItem(opciones.get(cboxF.getSelectedIndex()-1));
				
			}

		
			
			break;
			
		case "Conversor de peso":
			
			if (cboxF.getName() == "fromCombo") {
				
				imageIcon =  new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\"+cboxF.getSelectedItem()+".JPG");
				imageIcon1 = new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\"+cboxT.getSelectedItem()+".JPG");
				
				flag1.setIcon(labelsImg(imageIcon));
				flag2.setIcon(labelsImg(imageIcon1));
			}

			if (cboxF.getName() == "toCombo"){
		
				imageIcon =  new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\"+cboxF.getSelectedItem()+".JPG");
				imageIcon1 = new ImageIcon("C:\\Users\\Hardware Gaming PC\\eclipse-workspace\\source\\"+cboxT.getSelectedItem()+".JPG");
				
				flag1.setIcon(labelsImg(imageIcon1));
				flag2.setIcon(labelsImg(imageIcon));
				
			}
		
			break;
		
		}
    	
    }
    
    private void changeComboBox(JComboBox code, double number) {
		
		
    		//fromCombo toCombo
    		DecimalFormat decimal = new DecimalFormat("#.##");
    		String lblmsg = "";
	
			String name = code.getName();
			boolean valueDinamico = name == "fromCombo" ? true: false;
	
		try  {
				dataToWork.validacion(number);			
				
			
		} catch (ArrayIndexOutOfBoundsException e2) { 

			System.out.println(e2);
		}catch (NumberFormatException e3) {
		
			System.out.println(e3);
		}
		
		if (code.getSelectedIndex() == 0 && valueDinamico) {			
			
		
			double totales = degree.degrees(number, code.getSelectedIndex());
			String totalString = decimal.format(totales);
			txtTo.setText(totalString);
			labeltotal.setText(txtFrom.getText() + " "+ code.getSelectedItem() + " es igual a: " + totalString + " Celsius");
			
			
		
		}else if(code.getSelectedIndex() == 1 && valueDinamico){
			
			
			double totales = degree.degrees(number, code.getSelectedIndex());
			String totalString = decimal.format(totales);
			txtTo.setText(totalString);
			labeltotal.setText(txtFrom.getText() + " "+ code.getSelectedItem() + " es igual a: " + totalString + " Fahrenheit");
			

		}
		
		if (code.getSelectedIndex() == 0 && !valueDinamico) {			
			
			
			double totales = degree.degrees(number, code.getSelectedIndex());
			String totalString = decimal.format(totales);
			txtFrom.setText(totalString);
			labeltotal.setText(txtTo.getText() + " "+ code.getSelectedItem() + " es igual a: " + totalString + " Celsius");
		
		}else if(code.getSelectedIndex() == 1 && !valueDinamico){
			
			
			double totales = degree.degrees(number, code.getSelectedIndex());
			String totalString = decimal.format(totales);
			txtFrom.setText(totalString);
			labeltotal.setText(txtTo.getText() + " "+ code.getSelectedItem() + " es igual a: " + totalString + " Fahrenheit"  );
			
			

		}
		

		
		
	}
	

	private String codeISO(String dataToConvert) {
		String dataFrom = dataToConvert;
		String[] fromSplited = dataFrom.split("-");			
		String fromResponse =  fromSplited[1].trim();

		return fromResponse;
	}
	
	private ImageIcon labelsImg(ImageIcon imageIcon) {
		   	Image image = imageIcon.getImage(); // Obtenemos la imagen desde el ImageIcon
	        Image newImage = image.getScaledInstance(70, 45, Image.SCALE_SMOOTH); // Escalamos la imagen
	        ImageIcon newImageIcon = new ImageIcon(newImage); // Creamos un nuevo ImageIcon a partir de la imagen escalada
			
	        return newImageIcon;
	}
	 

	private Icon getImageIcon(URL url) {
		
		
		ImageIcon imageIcon = new ImageIcon(url);
		Icon icon = new ImageIcon(
				imageIcon.getImage());

		return icon;
  
	}
	
	private void reset() {
		labeltotal.setText(" ");
	    txtFrom.setText("0");
	    txtTo.setText("0");
	}
	
	


}
