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
			if (this.money >= A320.buyPrice){
				Airport air = new Airport();//
				A320 P =  new A320(name, air);
			}
		}
	}

	public ArrayList getPlanes() {
		return planes;
	}
}
