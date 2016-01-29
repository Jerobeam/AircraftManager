package com.backend.departments;

import com.backend.Airline;
import com.backend.Plane;

public class PlaneUpgrade {	
	private Plane plane;
	private Airline airline;
	
	private boolean winglets = false; //Verbessert Treibstoffverbrauch bis 3,5%
	private boolean newEngine = false; //Verbessert Treibstoffverauch bis 15% -> steigert Image(Umweltschutz, Lärmschutz)-> erhöt Reichweite bis 550km
	private boolean buyOutsideCleaning = false; //Verbessert Treibstoffverauch 1%
	private boolean doOutsideCleaning = false;
	private int fuelCostReduced; 
	
	private int entertainmentPackage = 0; // 0 = Keins; 3 = das Beste
	private int seat = 0;// 0 = Standart, 1 = Ledersitze, 2 = Luxussitze
	
	public PlaneUpgrade(Plane plane, Airline airline){
		this.plane = plane;
		this.airline = airline;
	}
	
	public boolean isWinglets() {
		return winglets;
	}
	public void setWinglets(boolean value) {
		if(value){
			this.winglets = value;
			this.airline.setMoney(this.airline.getMoney() - 500000);
			plane.setFuelCosts((int)(plane.getFuelCosts()*0.965));
		}
	}
	public boolean isNewEngine() {
		return newEngine;
	}
	public void setNewEngine(boolean value) {
		if(value){
			this.newEngine = value;
			this.airline.setMoney(this.airline.getMoney() - 10000000);
			plane.setFuelCosts((int)(plane.getFuelCosts()*0.85));
			plane.setRange(plane.getRange()+550);
			this.airline.setImage(this.airline.getImage()+10); //Absprache wie viel große die Imagesteigerung sein soll
		}
	}
	public boolean isBuyOutsideCleaning() {
		return buyOutsideCleaning;
	}
	public void setBuyOutsideCleaning(boolean value) {
		this.buyOutsideCleaning = value;
		this.airline.setMoney(this.airline.getMoney() - 50000);	
	}
	public boolean isDoOutsideCleaning() {
		return doOutsideCleaning;
	}
	public void setDoOutsideCleaning(boolean value) {
		if((this.doOutsideCleaning == false) && (value == true)){
			fuelCostReduced  = (int)(plane.getFuelCosts()*0.01);
			plane.setFuelCosts(plane.getFuelCosts() - fuelCostReduced);
		} else if ((this.doOutsideCleaning == true) && (value == false)) {
			plane.setFuelCosts(plane.getFuelCosts() + fuelCostReduced);
		}
		this.doOutsideCleaning = value;
	}
	public int getEntertainmentPackage() {
		return entertainmentPackage;
	}
	public void setEntertainmentPackage(int value) {
		if((value <= 3) && (value > 0)){
			switch (value) {
				case 1:{
					if(this.entertainmentPackage == 0){
						this.airline.setMoney(this.airline.getMoney() - 300000);
						plane.setComfort(plane.getComfort() + 5);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+5000);
					} else if(this.entertainmentPackage == 2){
						plane.setComfort(plane.getComfort() - 5);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()-5000);
					} else if(this.entertainmentPackage == 3){
						plane.setComfort(plane.getComfort() - 15);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()-15000);						
					}
					break;
				}
					
				case 2:{
					if(this.entertainmentPackage == 0){
						this.airline.setMoney(this.airline.getMoney() - 500000);
						plane.setComfort(plane.getComfort() + 10);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+10000);
					} else if(this.entertainmentPackage == 1){
						this.airline.setMoney(this.airline.getMoney() - 400000); //Da Packet 1 schon vorhanden ist und einige Geräte wiederverwendet werden
						plane.setComfort(plane.getComfort() + 5);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+5000);
					} else if(this.entertainmentPackage == 3){
						plane.setComfort(plane.getComfort() - 10);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()-10000);
					}
					break;
				}
					
				case 3:{
					if(this.entertainmentPackage == 0){
						this.airline.setMoney(this.airline.getMoney() - 1000000);
						plane.setComfort(plane.getComfort() + 20);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+20000);
					} else if(this.entertainmentPackage == 1){
						this.airline.setMoney(this.airline.getMoney() - 900000); //Da Packet 1 schon vorhanden ist und einige Geräte wiederverwendet werden
						plane.setComfort(plane.getComfort() + 15);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+15000);
					} else if(this.entertainmentPackage == 2){
						this.airline.setMoney(this.airline.getMoney() - 600000); //Da Packet 2 schon vorhanden ist und ein Großteil der Geräte wiederverwendet werden
						plane.setComfort(plane.getComfort() + 10);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+10000);
					}
					break;
				}
			}
			this.entertainmentPackage = value;
		}
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int value) {
		if((value <= 2) && (value >= 0)){			
			switch (value) {
				case 1:{
					if(this.seat < value){
						this.airline.setMoney(this.airline.getMoney() - 750000);
						plane.setComfort(plane.getComfort() + 15);
					}
					break;
				}					
				case 2:{
					if(this.seat < value){
						this.airline.setMoney(this.airline.getMoney() - 1500000);
						plane.setComfort(plane.getComfort() + 25);
					}
					break;
				}	
			}
		}
		this.seat = value;
	}
	
	
}
