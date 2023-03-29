package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.management.RuntimeErrorException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import model.ConversionInterface;

public class Funciones extends ConversionMonedas implements ConversionInterface {
	

	private static JSONParser jsonparser = new JSONParser();
	public String total;
	public String totalInvertido;
	

	public String getTotalInvertido() {
		return totalInvertido;
	}

	public void setTotalInvertido(String totalInvertido) {
		this.totalInvertido = totalInvertido;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public boolean convertCurrency(double from, double to) {
		
		double monedaFrom = from;
		double cambioTo  = to;
		double totales = 0;
		DecimalFormat decimal = new DecimalFormat("#,###.#");
	
		if (monedaFrom > 0 && cambioTo > 0) {			
			
			totales = monedaFrom * cambioTo;
			String fromToFormated =  decimal.format(totales);	
		
			setTotal(fromToFormated);
			return true;
			
		}

		return false;
	}



	@Override
	public Object apiCall() {
		
	
		try {
		
			String apiUrl = "https://v6.exchangerate-api.com/v6/a16483d579f76dd5964ed82b/pair/"+getTo()+"/"+getFrom()+"/"+getAmount()+"";
						
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");	
			conn.connect();
			
			//Comprobar si la peticion es correcta con respuesta 200
			
			if (conn.getResponseCode() != 200) {
				
				throw new RuntimeException("Error al conectarse a la api: " + conn.getResponseCode());
				
			}
			
			InputStream inputStream = url.openStream();
			JSONObject jsonObj = (JSONObject) jsonparser.parse(new InputStreamReader(inputStream)); 
			
			return jsonObj;
			
			
		} catch (Exception e) {

			throw new RuntimeException("Error en la peticion a la API" + e);
		}
		

	}
	
	public URL flags(String flag) {

		
		try {
			String url_str = "https://flagcdn.com/72x54/"+flag.substring(0,2).toLowerCase()+".png";

			URL url = new URL(url_str);
			HttpURLConnection com = (HttpURLConnection) url.openConnection();
			com.setRequestMethod("GET");
			com.connect();
			
			
			if (com.getResponseCode() != 200) {
				
				throw new RuntimeException("Error al conectarse a la api: " + com.getResponseCode());
				
			}
			
			return url;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	


	
	public boolean validacion(double numToconvert) {
		
		if (numToconvert > -1) {
			
			return true;
			
		}
		System.out.println(numToconvert);
		return false;
	}
	
	@Override
	public boolean invertCurrency(double from, double to) {
		
		double monedaFrom = from;
		double cambioTo  = to;
		double totales = 0;
		
		System.out.println(monedaFrom + " "+ cambioTo);
		DecimalFormat decimal = new DecimalFormat("#,###.#");
		DecimalFormat LessThanzero = new DecimalFormat("#.###");
		String TofromFormated;
	
		if (monedaFrom > -1 && cambioTo > -1) {
			
			
			totales = cambioTo * monedaFrom;
		
			boolean formatting = totales < 0.0 ? true:false;
			
			if (formatting) {
				
				TofromFormated = decimal.format(totales);	
			}else {
				TofromFormated = LessThanzero.format(totales);
			}
		
			setTotalInvertido(TofromFormated);
			return true;
			
		}



		return false;
	}

	
	
	public String converted(String cboxFrom, String cboxTo, String amount) {
		
	
		setFrom(cboxFrom);
		setTo(cboxTo);
		setAmount(amount);
				
		double convertion = 0;
		double moneyAmount =  Double.parseDouble(getAmount());	
		boolean sameFlag =  false;
		JSONObject obj = new JSONObject();		
		
		try{
			obj = (JSONObject) apiCall();
			convertion = (Double) obj.get("conversion_rate");
			sameFlag = cboxFrom != cboxTo ? true :false ;
		
			
		} catch (ClassCastException n) {
			
			return "Error de casteo.";
			
		}
		
		if (convertCurrency(moneyAmount,convertion) && validacion(moneyAmount) && sameFlag) {
			
			
			invertCurrency(moneyAmount, convertion);		
			return getTotalInvertido();
			
			
		}else {
			
			return "Misma moneda!!";
		}
		
	}
	
	
	public ArrayList currencyNames() {
		
		try {
			
			URL url = new URL("https://openexchangerates.org/api/currencies.json");
			HttpURLConnection comm =  (HttpURLConnection) url.openConnection();
			comm.connect();
			
			Reader read = new BufferedReader(new InputStreamReader(comm.getInputStream(),"UTF-8"));
			
		
			StringBuilder sb = new StringBuilder();

			for (int i;(i = read.read()) >=0;) {
				 sb.append((char) i);			
				
				 }	
							
			
			 org.json.JSONObject obj = new org.json.JSONObject(sb.toString());

		     ArrayList<String> allCountries = new ArrayList<>();
		     
		
			 for(String key : obj.keySet()) {
				 allCountries.add(obj.get(key) + " - " + key);
				 
			 }
			 

			 Collections.sort(allCountries);
		
			return allCountries;
		} catch (Exception e) {
			
			 System.out.println(e);
			 
		}
	
		 
		return null;

	}
	
	

}
