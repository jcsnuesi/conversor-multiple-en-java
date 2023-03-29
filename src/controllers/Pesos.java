package controllers;

import java.util.HashMap;

public class Pesos {
	
	private double cantidad;
	private String hacia;



	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public String getHacia() {
		return hacia;
	}


	public void setHacia(String hacia) {
		this.hacia = hacia;
	}


	public Double choise(String key) {
		
//		Desde: Kilogramos - KG
		System.out.println("Recibida: "+ key);
//		Hacia: Libras - LB
		System.out.println("Hacia: "+ getHacia());		
		
		switch (key) {
		case "Kilogramos - KG":
			
		 return	kg(getCantidad(),getHacia());
			
		case "Libras - LB":
			
			 return	lb(getCantidad(),getHacia());
				
		case "Onzas - OZ":
			
			 return	onzas(getCantidad(),getHacia());
				
		case "Gramos - G":
			
			 return	gramos(getCantidad(),getHacia());
			
		default:
			System.out.println("Opcion no valida!");
			break;
		}
		
		return null;
		
	}
	
		
	// de unidad a kilogramos
	public double kg(double val, String key) {
		
		switch (key) {
		
		case "Gramos - G":
			
			double total = val * 1000;
						
			return total;
		
		case "Onzas - OZ":
			
			double total1 = val * 35.274;
						
			return total1;
			
		case "Libras - LB":
			
			double total2 = val * 2.205;
						
			return total2;

		default:
			
			return val;
		}
		
	}
	
	// de unidad a Libras
	public double lb(double val, String key) {
		
		switch (key) {
		
		case "Kilogramos - KG":
			
			double total = val / 2.205;
						
			return total;
		
		case "Onzas - OZ":
			
			double total1 = val * 16;
						
			return total1;
			
		case "Gramos - G":
			
			double total2 = val * 453.592;
						
			return total2;

			default:
				
				return val;
		
		}
	
	}
	
	// de unidad a Gramos
	public double gramos(double val, String key) {
		
		switch (key) {
		
		case "Kilogramos - KG":
			
			double total = val / 1000;
						
			return total;
		
		case "Onzas - OZ":
			
			double total1 = val / 28.35;
						
			return total1;
			
		case "Libras - LB":
			
			double total2 = val / 453.6;
						
			return total2;
		default:
			
			return val;
		
		}
	
	}
	
	// de unidad a Onza
		public double onzas(double val, String key) {
			
			switch (key) {
			
			case "Kilogramos - KG":
				
				double total = val / 35.274;
							
				return total;
			
			case "Gramos - G":
				
				double total1 = val / 28.35;
							
				return total1;
				
			case "Libras - LB":
				
				double total2 = val / 16;
							
				return total2;
			
			default:
				
				return val;
			
			}
			
		}
	
	
	
	

}
