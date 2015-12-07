package com.backend;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Airline {

	private int money;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Route> routes = new ArrayList<Route>();
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
					.add("capacity", p.getCapacity())
					.build();
			
			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	public void buyRoute(String name) {
		//TODO: Buy Route
		//TODO: Create Airports
		//TODO: Buy/ rent Airport
//		if (type.equals("A320")){
//			
//				Airport air = new Airport();//Change!
//				A320 p =  new A320(name, air);
//				if (this.getMoney()>= p.getPrice()){
//				this.planes.add(p);
//				this.money = this.money - p.getPrice();}
//			
//		}
		
		switch (name) {
		case "FraPmi":
			break;

		default:
			break;
		}
	}

	public ArrayList getRoutes() {
		return routes;
	}
	
	public JsonArrayBuilder getRoutesJSON(){
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Route r : routes) {
			json = Json.createObjectBuilder()
					.add("name", r.getName())
					.add("distance", r.getDistance())
					.add("demand", r.getDemand())
					.add("costs", r.getCosts())
					.add("airport1", r.getAirport1().getName())
					.add("airport2", r.getAirport2().getName())
					.build();
			
			jsonArray.add(json);
		}
		return jsonArray;
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

	public void calculateNewMoney() {
		//Berechnung wie viel Geld pro tick hinzukommt
		
	}
}
