package com.backend;

import java.io.FileReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

	public void buyPlane(String type,String name, Airport location) {
		
//		if (type.equals("A320")){
//			
//				Airport air = new Airport();//Change!
//				A320 p =  new A320(name, air);
//				if (this.getMoney()>= p.getPrice()){
//				this.planes.add(p);
//				this.money = this.money - p.getPrice();}
//			
//		}
		System.out.println("buyPlane called");
		Plane p = this.createPlaneFromJson(name, type, location);
		if (this.getMoney()>= p.getPrice()){
			this.planes.add(p);
			this.money = this.money - p.getPrice();
			System.out.println("Plane bought" + p.getName());
		}
	}
	@SuppressWarnings("unchecked")
	public Plane createPlaneFromJson(String name, String type, Airport location){
		JSONParser parser = new JSONParser();
		 
        try {
 
            Object obj = parser.parse(new FileReader("JSONFiles/Planes.json"));
 
            JSONObject planesJSON = (JSONObject) obj;
 
            JSONObject planeTypeJSON = (JSONObject) planesJSON.get(type);
            
            Plane p = new Plane(name, type);
            p.setLocation(location);
            
            p.setCapacity((int)planeTypeJSON.get("capacity"));
            p.setComfort((int)planeTypeJSON.get("comfort"));
            p.setSpeed((int)planeTypeJSON.get("speed"));
            p.setRange((int)planeTypeJSON.get("range"));
            p.setFuelCosts((int)planeTypeJSON.get("fuelCosts"));
            p.setUpkeepCosts((int)planeTypeJSON.get("upkeepCosts"));
            p.setPrice((int)planeTypeJSON.get("price"));
            p.setPilot((int)planeTypeJSON.get("pilot"));
            p.setSteward((int)planeTypeJSON.get("steward"));

            return p;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
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
					.add("flightsPerDay", p.getFlightsPerDay())
					.add("costs", p.getCosts())
					.add("earnings", p.getEarnings())
					.build();
			
			jsonArray.add(json);
		}
		return jsonArray;
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
	
	public void guvDay(){
		int sum = 0;
		
		for (Plane p : planes) {
			sum = p.getEarnings() - p.getCosts();
		}
		this.ek = this.ek + sum;
		this.money = this.money + sum;
	}
	
	public int getAV() {
		//Anlagevermögen
		int av = 0;
		for (Plane p : planes) {
			av = av + p.getValue();
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
	
	public void takeCreditType1(int amount){
		credits.add(new Credit(this,amount,1,amount / 50));
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
