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
	

	//Set Advertisements
	private final InternetAd INTERNETAD = new InternetAd(0, this);
	private final TVAd TVAD = new TVAd(0, this);
	
	public double getMonthlyImageIncreasement() {
		return this.monthlyImageIncreasement;
	}
	
	public double getMonthlyImageIncreasementWithAds(){
		//add advertisment increasement
		double increasementByAdvertisement = this.getINTERNETAD().getMonthlyImageIncreasement() + this.getTVAD().getMonthlyImageIncreasement();
		return (this.monthlyImageIncreasement + increasementByAdvertisement);
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
		//add monthly costs for advertisements
		int advertisementCosts = this.getINTERNETAD().getMonthlyCosts() + this.getTVAD().getMonthlyCosts();
		return (monthlyCosts + advertisementCosts);
	}
	
	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	
	public void increaseImage(double increasment){
		if ((this.getAirline().getImage() + (increasment*this.getEfficiency())) <=100) {
			this.getAirline().setImage(this.getAirline().getImage() + (increasment*this.getEfficiency()));	
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
	}
	
	//Schriftzug Design für Flugzeuge
	public void designPlaneLettering(){
		if (!this.isLettering()) {
			this.getAirline().setMoney(this.getAirline().getMoney() - 300000);
			int amountPlanes= this.getAirline().getPlanes().size();
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + amountPlanes * 0.7);
			this.setLettering(true);
		}		
	}
	
	public void becomeSponsor(){
		if(!this.isSponsoring()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 200000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() + 2);
			this.setSponsoring(true);
		}
	}
	
	public void stopSponsoring(){
		if(this.isSponsoring()){
			this.setMonthlyCosts(this.getMonthlyCosts() - 200000);
			this.setMonthlyImageIncreasement(this.getMonthlyImageIncreasement() - 2);
			
			//reduce Image once because of "disappointment of fans"
			this.decreaseImage(5);
			this.setSponsoring(false);
		}
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public InternetAd getINTERNETAD() {
		return INTERNETAD;
	}

	public TVAd getTVAD() {
		return TVAD;
	}
	
}
