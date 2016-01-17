package com.backend.departments;

import com.backend.Airline;
import com.backend.Plane;

public class PlaneUpgrade {	
	private int monthlyCosts;
	private Plane plane;
	private Airline airline;
	
	private boolean winglets = false; //Verbessert Treibstoffverbrauch bis 3,5%
	private boolean newEngine = false; //Verbessert Treibstoffverauch bis 15% -> steigert Image(Umweltschutz, Lärmschutz)-> erhöt Reichweite bis 550km
	private boolean buyOutsideCleaning = false; //Verbessert Treibstoffverauch 1%
	private boolean doOutsideCleaning = false;
	
	private int entertainmentPackage = 0; // 0 = Keins; 3 = das Beste
	private int seat = 1;// 1 = Standart, 2 = Ledersitze, 3 = Luxussitze
	
	public PlaneUpgrade(Plane plane, Airline airline){
		this.plane = plane;
		this.airline = airline;
	}
	
	public int getMonthlyCosts() {
		return monthlyCosts;
	}
	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	public boolean isWinglets() {
		return winglets;
	}
	public void setWinglets(boolean value) {
		this.winglets = value;
		this.airline.setMoney(this.airline.getMoney() - 500000);
		plane.setFuelCosts((int)(plane.getFuelCosts()*0.965));
	}
	public boolean isNewEngine() {
		return newEngine;
	}
	public void setNewEngine(boolean value) {
		this.newEngine = value;
		this.airline.setMoney(this.airline.getMoney() - 10000000);
		plane.setFuelCosts((int)(plane.getFuelCosts()*0.85));
		plane.setRange(plane.getRange()+550);
		this.airline.setImage(this.airline.getImage()+10); //Absprache wie viel große die Imagesteigerung sein soll
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
		this.doOutsideCleaning = value;
		if(value){
			plane.setFuelCosts((int)(plane.getFuelCosts()*0.99));
		}
	}
	public int getEntertainmentPackage() {
		return entertainmentPackage;
	}
	public void setEntertainmentPackage(int value) {
		if((value <= 3) && (value >= 0)){
			switch (value) {
				case 1:{
					if(value > this.entertainmentPackage){
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
				}
					
				case 2:{
					if(value > this.entertainmentPackage){
						this.airline.setMoney(this.airline.getMoney() - 500000);
						plane.setComfort(plane.getComfort() + 10);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+10000);
					} else if(this.entertainmentPackage == 3){
						plane.setComfort(plane.getComfort() - 10);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()-10000);
					}
				}
					
				case 3:{
					if(value > this.entertainmentPackage){
						this.airline.setMoney(this.airline.getMoney() - 1000000);
						plane.setComfort(plane.getComfort() + 20);
						this.airline.setMonthlyCosts(this.airline.getMonthlyCosts()+20000);
					}
				}
			}
			this.entertainmentPackage = value;
		}
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int value) {
		if((value <= 3) && (value >= 1)){			
			switch (value) {
				case 2:{
					if(this.seat < value){
						this.airline.setMoney(this.airline.getMoney() - 750000);
						plane.setComfort(plane.getComfort() + 15);
					}
					break;
				}					
				case 3:{
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
