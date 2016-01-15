package com.backend.departments;

import com.backend.Airline;

public class Service {
	private int monthlyCosts;
	
	private int CateringPackage = 0; //Steigert Komfort aller Planes
	private int airportLounge = 0; // 1= einfache Lounge steigert leicht den Komfort; 2 = groﬂe Loune mit kostenlosen Speisen/Getr‰nken
	
	private boolean newsPaper = false; //Steigert Komfort aller Planes
	private boolean freeSeatReservation = false; //Steigert Komfort
	private boolean freePickupService = false; //Steigert Komfort aller Planes
	
	private boolean wlanAboard = false; //Steigert Komfort aller Planes
	public int getMonthlyCosts() {
		return monthlyCosts;
	}
	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	public int getCateringPackage() {
		return CateringPackage;
	}
	public void setCateringPackage(int cateringPackage) {
		CateringPackage = cateringPackage;
	}
	public int getAirportLounge() {
		return airportLounge;
	}
	public void setAirportLounge(int airportLounge) {
		this.airportLounge = airportLounge;
	}
	public boolean isWlanAboard() {
		return wlanAboard;
	}
	public void setWlanAboard(boolean wlanAboard) {
		this.wlanAboard = wlanAboard;
	}
	public boolean isNewsPaper() {
		return newsPaper;
	}
	public void setNewsPaper(boolean newsPaper) {
		this.newsPaper = newsPaper;
	}
	public boolean isFreeSeatReservation() {
		return freeSeatReservation;
	}
	public void setFreeSeatReservation(boolean freeSeatReservation) {
		this.freeSeatReservation = freeSeatReservation;
	}
	public boolean isFreePickupService() {
		return freePickupService;
	}
	public void setFreePickupService(boolean freePickupService) {
		this.freePickupService = freePickupService;
	}	
}
