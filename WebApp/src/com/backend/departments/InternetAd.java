package com.backend.departments;

import com.backend.Airline;

public class InternetAd extends Advertisement {
	
	private static byte currentCostLevel = 0; //Welcher Preis ist der aktuelle?
	private static int costLevels[] = {200000, 220000, 250000, 300000}; //4 Verschiedene Preise, Großes Paket kostet doppelt soviel
	private double helperForSmallerPackage = 5; //initialer Imagezuwachs für wenig Werbung
	private double helperForBiggerPackage = 12; //initialer Imagezuwachs für viel Werbung
	
	public InternetAd(int size, Airline airline) {
		super(airline);
		
		this.setSize(size);
	}
	
	public void setSize(int size) {
		if (size > 0 && size <=2) {
			if (InternetAd.currentCostLevel <=4) {
				InternetAd.currentCostLevel++;
			}else{
				InternetAd.currentCostLevel = 4;
			}
		}
		
		switch (size) {
		case 0:
			this.setMonthlyCosts(0);
			this.setMonthlyImageIncreasement(0);
			break;
		case 1:
			if(this.getSize()==2){
				this.setMonthlyCosts(this.getMonthlyCosts()*2);
			}else{
				this.setMonthlyCosts(InternetAd.costLevels[currentCostLevel]);
			}
			this.setMonthlyImageIncreasement(this.getHelperForSmallerPackage());
			break;
		case 2:
			if(this.getSize()==1){
				this.setMonthlyCosts((int)(this.getMonthlyCosts()*0.5));
			}else{
				this.setMonthlyCosts(InternetAd.costLevels[currentCostLevel]*2);
			}
			this.setMonthlyImageIncreasement(this.getHelperForBiggerPackage());
			break;
		default:
			break;
		}
		
		this.size = size;
	}
	
	//Decrease monthly Image grow over times
		public void iterate() {
			switch (this.getSize()) {
			//regenerate monthlyImage
			case 0:
				//increase bigger helper variable
				if (this.getHelperForBiggerPackage()<12.0) {
					this.setHelperForBiggerPackage(this.getHelperForBiggerPackage() * 1.1);
				}else{
					this.setHelperForBiggerPackage(12);
				}
				
				//increase smaller helper variable
				if (this.getHelperForSmallerPackage()<5.0) {
					this.setHelperForSmallerPackage(this.getHelperForSmallerPackage() * 1.1);
				}else{
					this.setHelperForSmallerPackage(5.0);
				}
				break;
				
				
			//decrease monthly Image less for smaller package
			case 1:
				
				//decrease bigger helper variable
				if (this.getHelperForBiggerPackage()>2.0) {
					this.setHelperForBiggerPackage(this.getHelperForBiggerPackage() * 0.9);
				}else{
					this.setHelperForBiggerPackage(2);
				}
				
				//decrease smaller helper variable
				if (this.getHelperForSmallerPackage()>1.5) {
					this.setHelperForSmallerPackage(this.getHelperForSmallerPackage() * 0.9);
				}else{
					this.setHelperForSmallerPackage(1.5);
				}
				
				//set monthly Image
				this.setMonthlyImageIncreasement(this.getHelperForSmallerPackage());
				break;
				
			//decrease monthly Image more for bigger package
			case 2:
				//decrease bigger helper variable
				if (this.getHelperForBiggerPackage()>2.0) {
					this.setHelperForBiggerPackage(this.getHelperForBiggerPackage() * 0.75);
				}else{
					this.setHelperForBiggerPackage(2);
				}
				
				//decrease smaller helper variable
				if (this.getHelperForSmallerPackage()>1.5) {
					this.setHelperForSmallerPackage(this.getHelperForSmallerPackage() * 0.75);
				}else{
					this.setHelperForSmallerPackage(1.5);
				}
				
				//set monthly Image
				this.setMonthlyImageIncreasement(this.getHelperForBiggerPackage());
				break;

			default:
				break;
			}
		}
		
		public double getHelperForSmallerPackage() {
			return helperForSmallerPackage;
		}

		public void setHelperForSmallerPackage(double helperForSmallerPackage) {
			this.helperForSmallerPackage = helperForSmallerPackage;
		}

		public double getHelperForBiggerPackage() {
			return helperForBiggerPackage;
		}

		public void setHelperForBiggerPackage(double helperForBiggerPackage) {
			this.helperForBiggerPackage = helperForBiggerPackage;
		}
		
		
}
