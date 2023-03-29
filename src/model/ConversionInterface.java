package model;

import org.json.JSONObject;

public interface ConversionInterface {
	
	
	
	public boolean convertCurrency(double from, double to);
	
	public boolean invertCurrency(double from, double to);
	
	public Object apiCall();
	
	
	

}