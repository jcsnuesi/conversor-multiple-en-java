package controllers;

import model.DegreesInterface;

public class Degrees implements DegreesInterface {

	@Override
	public double degrees(double from, int code) {
		
		double totalTemp = 0;
		if (code == 0) {
		 
			totalTemp = (from - 32) * 5/9;
		
		}

		if (code == 1) {
			
			//(0°C × 9/5) + 32 = 32°F
			totalTemp = (from * 9/5) + 32;
			
		}
		
		return totalTemp;
		
	}

}
