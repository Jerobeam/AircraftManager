package com.backend;

import java.util.ArrayList;

public class Airline {

	private int money;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private int planeCount = 0;

	public Airline(String name, int money) {

		this.setName(name);
		this.setMoney(money);
	}

	public int getMoney() {
		return money;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void buyPlane(String type,String name) {
		if (type.equals("A320")){
			if (this.money >= 600000){
				Airport air = new Airport();//Change!
				A320 p =  new A320(name, air);
				this.planes.add(p);
				this.money = this.money - 600000;
			}
		}
	}

	public ArrayList getPlanes() {
		return planes;
	}
	
	public int getAV() {
		int av = 0;
		for (Plane p : planes) {
			av = p.getValue();
		}
		return av;
	}
	
	public int getUV() {
		int uv = 0;
		
		return uv;
	}

	public void calculateNewMoney() {
		//Berechnung wie viel Geld pro tick hinzukommt
		
	}
}
