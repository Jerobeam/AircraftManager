package com.backend.departments;

import com.backend.Airline;

public class Marketing {
	//efficiency as an value percentage 1.5 (150%) means 1.5 times efficient
	private double efficiency;
	private int monthlyCosts;
	
	public Marketing(){
		this.setEfficiency(1);
	}
		
	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficency) {
		this.efficiency = efficency;
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}
	
	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	
	public void increaseImage(Airline airline, int increasment){
		airline.setImage(airline.getImage() + increasment);
	}
	
}
