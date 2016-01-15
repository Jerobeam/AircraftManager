package com.backend.departments;

public class PlaneUpgrade {	
	private boolean winglets = false; //Verbessert Treibstoffverbrauch bis 3,5%
	private boolean newEngine = false; //Verbessert Treibstoffverauch bis 15% -> steiger Image(Umweltschutz, Lärmschutz)-> erhöt Reichweite bis 950km
	private boolean buyOutsideCleaning = false; //Verbessert Treibstoffverauch 1%
	private boolean doOutsideCleaning = false;
	
	private int entertainmentPackage = 0; // 0 = Keins; 3 = das Beste
	private int seat = 1;// 1 = Standart, 2 = Ledersitze, 3 = Luxussitze
	
	
	public boolean isWinglets() {
		return winglets;
	}
	public void setWinglets(boolean winglets) {
		this.winglets = winglets;
	}
	public boolean isNewEngine() {
		return newEngine;
	}
	public void setNewEngine(boolean newEngine) {
		this.newEngine = newEngine;
	}
	public boolean isBuyOutsideCleaning() {
		return buyOutsideCleaning;
	}
	public void setBuyOutsideCleaning(boolean outsideCleaning) {
		this.buyOutsideCleaning = outsideCleaning;
	}
	public boolean isDoOutsideCleaning() {
		return doOutsideCleaning;
	}
	public void setDoOutsideCleaning(boolean doOutsideCleaning) {
		this.doOutsideCleaning = doOutsideCleaning;
	}
	public int getEntertainmentPackage() {
		return entertainmentPackage;
	}
	public void setEntertainmentPackage(int entertainmentPackage) {
		this.entertainmentPackage = entertainmentPackage;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	
}
