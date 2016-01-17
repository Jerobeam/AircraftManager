package com.backend.departments;

import java.util.ArrayList;

import com.backend.Airline;
import com.backend.Plane;

public class Service {
	private Airline airline;
	private int monthlyCosts;
	
	private int cateringPackage = 0; //Steigert Komfort aller Planes
	private int airportLounge = 0; // 1= einfache Lounge steigert leicht den Komfort; 2 = groﬂe Loune mit kostenlosen Speisen/Getr‰nken
	
	private boolean newsPaper = false; //Steigert Komfort aller Planes
	private boolean freeSeatReservation = false; //Steigert Komfort
	private boolean freePickupService = false; //Steigert Komfort aller Planes
	private boolean wlanAboard = false; //Steigert Komfort aller Planes
	
	public Service(Airline airline){
		this.airline = airline;
	}
	public int getMonthlyCosts() {
		return monthlyCosts;
	}
	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	public int getCateringPackage() {
		return cateringPackage;
	}
	public void setCateringPackage(int value) {
		if((value <= 3) && (value >= 0)){
			ArrayList<Plane> planes = airline.getPlanes();
			switch (value) {
			    case 0:{
			    	if(this.cateringPackage == 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-3);
						}
						this.monthlyCosts = (this.monthlyCosts - (planes.size()*10000));
					} else if (this.cateringPackage == 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-7);
						}
						this.monthlyCosts = (this.monthlyCosts - (planes.size()*50000));
					}
			    	break;
			    }
				case 1:{
					if(this.cateringPackage < 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+3);
						}
						this.monthlyCosts = (this.monthlyCosts + (planes.size()*10000));
					} else if (this.cateringPackage > 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-4);
						}
						this.monthlyCosts = (this.monthlyCosts - (planes.size()*40000));
					}
					break;
				}
					
				case 2:{
					if(this.cateringPackage < 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+7);
						}
						this.monthlyCosts = (this.monthlyCosts + (planes.size()*50000));
					}
					break;
				}
			}
			this.cateringPackage = value;
		}
	}
	public int getAirportLounge() {
		return airportLounge;
	}
	public void setAirportLounge(int value) {
		if((value <= 2) && (value >= 0)){
			ArrayList<Plane> planes = airline.getPlanes();
			switch (value) {
			    case 0:{
			    	if(this.airportLounge == 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-5);
						}
						this.monthlyCosts -= 50000;
					} else if (this.airportLounge == 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-10);
						}
						this.monthlyCosts -= 150000;
					}
			    	break;
			    }
				case 1:{
					if(this.airportLounge < 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+5);
						}
						airline.setMoney(airline.getMoney()-100000);
						this.monthlyCosts += 50000;
					} else if (this.airportLounge > 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-5);
						}
						this.monthlyCosts -= 100000;
					}
					break;
				}
					
				case 2:{
					if(this.airportLounge < 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+10);
						}
						airline.setMoney(airline.getMoney()-500000);
						this.monthlyCosts += 150000;
					}
					break;
				}			
			}
			this.airportLounge = value;
		}	
	}
	public boolean isWlanAboard() {
		return wlanAboard;
	}
	public void setWlanAboard(boolean value) {
		this.wlanAboard = value;
		ArrayList<Plane> planes = airline.getPlanes();
		if(value){
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+2);
			}
			airline.setMoney(airline.getMoney()-50000);
			this.monthlyCosts = (this.monthlyCosts + (planes.size()*20000));
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-2);
			}
			this.monthlyCosts = (this.monthlyCosts - (planes.size()*20000));
		}
	}
	public boolean isNewsPaper() {
		return newsPaper;
	}
	public void setNewsPaper(boolean value) {
		this.newsPaper = value;
		ArrayList<Plane> planes = this.airline.getPlanes();
		if(value){
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+1);
			}
			this.monthlyCosts = (this.monthlyCosts + (planes.size()*10000));
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-1);
			}
			this.monthlyCosts = (this.monthlyCosts - (planes.size()*10000));
		}
	}
	public boolean isFreeSeatReservation() {
		return freeSeatReservation;
	}
	public void setFreeSeatReservation(boolean value) {
		this.freeSeatReservation = value;
		ArrayList<Plane> planes = this.airline.getPlanes();
		if(value){
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+1);
			}
			airline.setMoney(airline.getMoney()-10000);
			this.monthlyCosts += 5000;		
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-1);
			}
			this.monthlyCosts -= 5000;	
		}
	}
	public boolean isFreePickupService() {
		return freePickupService;
	}
	public void setFreePickupService(boolean value) {
		this.freePickupService = value;
		ArrayList<Plane> planes = this.airline.getPlanes();
		if(value){		
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+5);
			}
			airline.setMoney(airline.getMoney()-100000);
			this.monthlyCosts += 50000;
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-5);
			}
			this.monthlyCosts -= 50000;
		}
	}	
}
