package com.backend.departments;

import com.backend.Airline;

public class Marketing {
	//efficiency as an value percentage 1.5 (150%) means 1.5 times efficient
	private double efficiency = 1.0;
	private int monthlyCosts = 0;
	private boolean lettering = false;
	private boolean sponsoring = false;
	private double monthlyImageIncreasement = 0;
	
	public double getMonthlyImageIncreasement() {
		return monthlyImageIncreasement;
	}

	public void setMonthlyImageIncreasement(double monthlyImageIncreasement) {
		this.monthlyImageIncreasement = monthlyImageIncreasement;
	}

	public boolean isLettering() {
		return lettering;
	}

	public void setLettering(boolean lettering) {
		this.lettering = lettering;
	}

	public Marketing(){
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
	
	public void increaseImage(Airline airline, double increasment){
		if ((airline.getImage() + increasment) <=100) {
			airline.setImage(airline.getImage() + (increasment*this.getEfficiency()));			
		}else{
			airline.setImage(100.0);
		}
	}
	
	public void decreaseImage(Airline airline, double decreasement){
		if ((airline.getImage() - decreasement) >=0) {
			airline.setImage(airline.getImage() - decreasement);			
		}else{
			airline.setImage(0.0);
		}
	}
	
	public boolean isSponsoring() {
		return sponsoring;
	}

	public void setSponsoring(boolean sponsoring) {
		this.sponsoring = sponsoring;
	}
	
	//Marketingaktionen:

	public void donate(Airline airline, long amount){
		airline.setMoney(airline.getMoney() - amount);
		if (amount<2500000){
			this.increaseImage(airline, 2);			
		}else if (amount>=2500000){
			this.increaseImage(airline, 5.5);			
		}
	}
	
	//Schriftzug Design für Flugzeuge
	public void designPlaneLettering(Airline airline){
		if (!this.isLettering()) {
			airline.setMoney(airline.getMoney() - 300000);
			int amountPlanes= airline.getPlanes().size();
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + amountPlanes * 0.7);
			this.setLettering(true);
		}		
	}
	
	public void becomeSponsor(Airline airline){
		if(!this.isSponsoring()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 50000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + 3);
			this.setSponsoring(true);
		}
	}
	
	public void stopSponsoring(Airline airline){
		if(this.isSponsoring()){
			this.setMonthlyCosts(this.getMonthlyCosts() - 50000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() - 3);
			
			//reduce Image once because of "disappointment of fans"
			this.decreaseImage(airline, 2);
			this.setSponsoring(false);
		}
	}
	
	
	
}
