package com.backend.departments;

import com.backend.Airline;

public abstract class Advertisement {
	protected double monthlyImageIncreasement; // m�gliche Preise f�r die Werbung
	protected int monthlyCosts; // Kosten f�r Werbungs-Objekt
	protected Marketing marketing; // Besitzermarketingabteilung der Werbung
	protected int size; // Werbungspakete: 0=kein Paket; 1=kleines Paket; 2=gro�es Paket
	

	public Advertisement(Marketing marketing) {
		this.marketing = marketing;
	}

	public double getMonthlyImageIncreasement() {
		return monthlyImageIncreasement;
	}

	public void setMonthlyImageIncreasement(double monthlyImageIncreasement){
		this.monthlyImageIncreasement = monthlyImageIncreasement;
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int costs) {
		this.monthlyCosts = costs;
	}
	
	public void setSize(int size){}

	public int getSize() {
		return size;
	}

}
