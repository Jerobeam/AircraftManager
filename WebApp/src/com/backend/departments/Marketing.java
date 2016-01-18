package com.backend.departments;

import com.backend.Airline;

public class Marketing {
	//efficiency as an value percentage 1.5 (150%) means 1.5 times efficient
	private double efficiency = 1.0;
	private int monthlyCosts = 0;
	private boolean lettering = false;
	private boolean sponsoring = false;
	private double monthlyImageIncreasement = 0;
	private Airline airline;
	
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

	public Marketing(Airline airline){
		this.setAirline(airline);
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
	
	public void increaseImage(double increasment){
		if ((this.getAirline().getImage() + increasment) <=100) {
			this.getAirline().setImage(this.getAirline().getImage() + (increasment*this.getEfficiency()));	
			System.out.println("Image erhöht um: " + increasment);
		}else{
			this.getAirline().setImage(100.0);
		}
	}
	
	public void decreaseImage(double decreasement){
		if ((this.getAirline().getImage() - decreasement) >=0) {
			this.getAirline().setImage(this.getAirline().getImage() - decreasement);			
		}else{
			this.getAirline().setImage(0.0);
		}
	}
	
	public boolean isSponsoring() {
		return sponsoring;
	}

	public void setSponsoring(boolean sponsoring) {
		this.sponsoring = sponsoring;
	}
	
	//Marketingaktionen:

	public void donate(long amount){
		this.getAirline().setMoney(airline.getMoney() - amount);
		if (amount<2500000){
			this.increaseImage(2);			
		}else if (amount>=2500000){
			this.increaseImage(5.5);			
		}
		System.out.println("Donate called with: " + amount);
	}
	
	//Schriftzug Design für Flugzeuge
	public void designPlaneLettering(){
		if (!this.isLettering()) {
			this.getAirline().setMoney(this.getAirline().getMoney() - 300000);
			int amountPlanes= this.getAirline().getPlanes().size();
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + amountPlanes * 0.7);
			this.setLettering(true);
			System.out.println("designed Plane Lettering");
		}		
	}
	
	public void becomeSponsor(){
		if(!this.isSponsoring()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 50000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + 3);
			this.setSponsoring(true);
			System.out.println("becomeSponsor called");
		}
	}
	
	public void stopSponsoring(){
		if(this.isSponsoring()){
			this.setMonthlyCosts(this.getMonthlyCosts() - 50000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() - 3);
			
			//reduce Image once because of "disappointment of fans"
			this.decreaseImage(2);
			this.setSponsoring(false);
			
			System.out.println("stopSponsoring called");
		}
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	
	
}
