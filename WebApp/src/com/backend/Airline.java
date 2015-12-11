package com.backend;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Airline {

	private int money;
	private int ek;
	private int fk;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Credit> credits = new ArrayList<Credit>();
	private int planeCount = 0;

	public Airline(String name, int money) {

		this.setName(name);
		this.setMoney(money);
		this.ek = this.money;
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
			
				Airport air = new Airport();//Change!
				A320 p =  new A320(name, air);
				if (this.getMoney()>= p.getPrice()){
				this.planes.add(p);
				this.money = this.money - p.getPrice();}
			
		}
	}

	public ArrayList getPlanes() {
		return planes;
	}
	
	public JsonArrayBuilder getPlanesJSON(){
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Plane p : planes) {
			json = Json.createObjectBuilder()
					.add("type", p.getType())
					.add("name", p.getName())
					.add("value", p.getValue())
					.add("comfort", p.getComfort())
					.add("capacity", p.getCapacity())
					.add("costs", p.getCosts())
					.build();
			
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	public void occupyRoute(String name, Plane plane) {
		//TODO: Buy Route
		//TODO: Create Airports
	}
	
	public Plane getPlaneByName(String name){
		Plane ret = null;
		for (Plane p : planes) {
			if (p.getName().equals(name)) {
				ret = p;
				break;
			}
		}
		return ret;
	}
	

	
	public int getAV() {
		//Anlagevermögen
		int av = 0;
		for (Plane p : planes) {
			av = p.getValue();
		}
		return av;
	}
	
	public int getUV() {
		//Umlaufvermögen
		int uv = 0;	
		return uv;
	}

	public void monthlyCalculation() {
		for (Credit c : credits) {
			c.tick();
		}
	}
	
	public void takeCreditType1(){
		credits.add(new Credit(this,10000,1,200));
	}

	public int getEK() {
		// TODO Auto-generated method stub
		return this.ek;
	}
	public int getFK() {
		// TODO Auto-generated method stub
		return this.fk;
	}

	public int getBilanzSum() {
		// TODO Auto-generated method stub
		return this.getFK()+this.getEK();
	}
}
