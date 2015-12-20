package com.backend;

import java.io.FileReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.backend.departments.Accounting;
import com.backend.departments.HR;
import com.backend.departments.IT;
import com.backend.departments.Marketing;

public class Airline {

	private long money;
	private int monthlyCosts;
	private long ek;
	private long fk;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Credit> credits = new ArrayList<Credit>();
	private int planeCount = 0;
	private int image;
	
	//Set Departments
	private final Marketing MarketingDept = new Marketing();
	private final IT ITDept = new IT();
	private final HR HRDept = new HR();
	private final Accounting AccountingDept = new Accounting();
	
	public Airline(String name, long money) {

		this.setName(name);
		this.setMoney(money);
		this.ek = this.money;
	}

	public long getMoney() {
		return money;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMoney(long money) {
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

	public Plane createPlaneFromJson(String name, String type, Airport location){
		JSONParser parser = new JSONParser();
		 
        try {
 
        	//TODO: Make path generic
            Object fileObject = parser.parse(new FileReader("D:\\Programme\\Eclipse\\workspaces\\Fallstudie Planspiel\\AirlineManager\\WebApp\\WebContent\\content\\JSONFiles\\planes.json"));
            JSONObject planesJSON = (JSONObject) fileObject;
 
            JSONObject planeTypeJSON = (JSONObject) planesJSON.get(type);
            
            Plane p = new Plane(name, type);
            p.setLocation(location);
            
            p.setCapacity((int)(long)planeTypeJSON.get("capacity"));
            p.setComfort((int)(long)planeTypeJSON.get("comfort"));
            p.setSpeed((int)(long)planeTypeJSON.get("speed"));
            p.setRange((int)(long)planeTypeJSON.get("range"));
            p.setFuelCosts((int)(long)planeTypeJSON.get("fuelCosts"));
            p.setUpkeepCosts((int)(long)planeTypeJSON.get("upkeepCosts"));
            p.setPrice((int)(long)planeTypeJSON.get("price"));
            p.setPilot((int)(long)planeTypeJSON.get("pilot"));
            p.setSteward((int)(long)planeTypeJSON.get("steward"));

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

	public long getEK() {
		return this.ek;
	}
	public long getFK() {
		return this.fk;
	}

	public long getBilanzSum() {
		return this.getFK()+this.getEK();
	}

	public Marketing getMarketingDept() {
		return MarketingDept;
	}

	public IT getITDept() {
		return ITDept;
	}

	public HR getHRDept() {
		return HRDept;
	}

	public Accounting getAccountingDept() {
		return AccountingDept;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	
	
	
}
