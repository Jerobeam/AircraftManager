package com.backend;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.backend.departments.Accounting;
import com.backend.departments.IT;
import com.backend.departments.Marketing;
import com.backend.departments.Service;

public class Airline {

	private long money;
	private int monthlyCosts;
	private long ek;
	private long fk;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Credit> credits = new ArrayList<Credit>();
	private int planeCount = 0;
	//TODO: Balance startImage 
	private double image = 10;
	private Service services;
	
	//Set Departments
	private final Marketing MarketingDept = new Marketing();
	private final IT ITDept = new IT();
	private final Accounting AccountingDept = new Accounting();
	
	private final int PILOTENKOSTEN = 5000;
	private final int STWDKOSTEN = 2000;
	private final int BODENPERSONALKOSTEN = 2500;
	private final int WARTUNGSPERSONALKOSTEN = 3000;
	
	private int Piloten = 0;
	private int Stewardessen = 0;
	private int Bodenpersonal = 0;
	private int WartungsPersonal = 0;
	private int blockedPilotes = 0;
	private int blockedStewards = 0;
	
	public int getBlockedPilotes() {
		return blockedPilotes;
	}

	public void setBlockedPilotes(int blockedPilotes) {
		this.blockedPilotes = blockedPilotes;
	}

	public int getBlockedStewards() {
		return blockedStewards;
	}

	public void setBlockedStewards(int blockedStewards) {
		this.blockedStewards = blockedStewards;
	}

	public int getPiloten() {
		return Piloten;
	}

	public void setPiloten(int piloten) {
		if(piloten >= this.blockedPilotes)Piloten = piloten;
	}

	public int getStewardessen() {
		return Stewardessen;
	}

	public void setStewardessen(int stewardessen) {
		if(stewardessen >= this.blockedStewards)Stewardessen = stewardessen;
	}

	public int getBodenpersonal() {
		return Bodenpersonal;
	}

	public void setBodenpersonal(int bodenpersonal) {
		Bodenpersonal = bodenpersonal;
	}

	public int getWartung() {
		return WartungsPersonal;
	}

	public void setWartung(int wartung) {
		WartungsPersonal = wartung;
	}

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
		
		System.out.println("buyPlane called");
		if(name.length() >= 1){
			Plane p = this.createPlaneFromJson(name, type, location);
			if (this.getMoney()>= p.getPrice()){
				this.planes.add(p);
				this.money = this.money - p.getPrice();
				//if lettering from marketing is bought increase image
				System.out.println("Plane bought" + p.getName());
			}
		}
	}

	public Plane createPlaneFromJson(String name, String type, Airport location){
		JSONParser parser = new JSONParser();
		 
        try {
 
            Object fileObject = parser.parse(new InputStreamReader(getClass().getResourceAsStream("planes.json")));
            JSONObject planesJSON = (JSONObject) fileObject;
 
            JSONObject planeTypeJSON = (JSONObject) planesJSON.get(type);
            
            Plane p = new Plane(name, type, this);
            p.setLocation(location);
            p.setValue((int)(long)planeTypeJSON.get("value"));
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
					.add("bookingPrice", p.getBookingPrice())
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
		this.ek = this.getEK() + sum;
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
			c.iterationStep();
		}
		for (Plane p : planes){
			if(p.getOutsideCleaning()){
				p.setOutsideCleaning(false);
			}
			if(p.getBuyPlaneCleaning()){
				p.setOutsideCleaning(true);
			}
		}
		int personalkosten = 0;
		personalkosten = (int)((this.Piloten*this.PILOTENKOSTEN+this.Stewardessen*this.STWDKOSTEN+this.Bodenpersonal*this.BODENPERSONALKOSTEN+this.WartungsPersonal*this.WARTUNGSPERSONALKOSTEN) * this.getITDept().getHrCostsFactor());
		//billing departments
		long moneyNew = this.getMoney() - this.getITDept().getMonthlyCosts() - this.getAccountingDept().getMonthlyCosts() - this.getMarketingDept().getMonthlyCosts() - personalkosten;
		this.setMoney(moneyNew);
		this.ek = this.getEK() - this.getITDept().getMonthlyCosts() - this.getAccountingDept().getMonthlyCosts() - this.getMarketingDept().getMonthlyCosts() - personalkosten; 
	}
	
	public void takeCreditType1(int amount){
		double ek = this.getEK();
		if(ek /(this.getFK()+amount) > 2.0){
			int back = (int) (amount*0.1);
			credits.add(new Credit(this,amount,2,back));
		}
	}
	public void takeCreditType2(int amount){
		double ek = this.getEK();
		if(ek /(this.getFK()+amount) > 1.5){
			int back = (int) (amount*0.08);
			credits.add(new Credit(this,amount,3,back));
		}
	}
	public void takeCreditType3(int amount){
		double ek = this.getEK();
		if(ek /(this.getFK()+amount) > 1){
			int back = (int) (amount*0.06);
			credits.add(new Credit(this,amount,5,back));
		}
	}
	public long getEK() {
		return this.ek;
	}
	
	public long getFK() {
		long fremdkap = 0;
		for (Credit c : credits) {
			fremdkap = fremdkap + c.getAmount();
		}
		return fremdkap;
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

	public Accounting getAccountingDept() {
		return AccountingDept;
	}

	public double getImage() {
		return image;
	}

	public void setImage(double image) {
		this.image = image;
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}
	
	public void setNewspaper(boolean value){
		this.services.setNewsPaper(value);
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
	
	public void setWlanAboard(boolean value){
		this.services.setWlanAboard(value);
		if(value){
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+2);
			}
			this.money -= 50000;
			this.monthlyCosts = (this.monthlyCosts + (planes.size()*20000));
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-2);
			}
			this.monthlyCosts = (this.monthlyCosts - (planes.size()*20000));
		}
	}
	
	public void setfreePickupService(boolean value){
		this.services.setFreePickupService(value);
		if(value){		
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+5);
			}
			this.money -= 100000;
			this.monthlyCosts += 50000;
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-5);
			}
			this.monthlyCosts -= 50000;
		}
	}
	
	public void setfreeSeatReservation(boolean value){
		this.services.setFreeSeatReservation(value);
		if(value){
			for (Plane p : planes){
				 p.setComfort(p.getComfort()+1);
			}
			this.money -= 10000;
			this.monthlyCosts += 5000;		
		} else {
			for (Plane p : planes){
				 p.setComfort(p.getComfort()-1);
			}
			this.monthlyCosts -= 5000;	
		}
	}
	
	public void setAirportLounge(int value){
		if((value <= 2) && (value >= 0)){
			this.services.setAirportLounge(value);
			switch (value) {
			    case 0:{
			    	if(this.services.getAirportLounge() == 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-5);
						}
						this.monthlyCosts -= 50000;
					} else if (this.services.getAirportLounge() == 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-10);
						}
						this.monthlyCosts -= 150000;
					}
			    }
				case 1:{
					if(this.services.getAirportLounge() < 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+5);
						}
						this.money -= 100000;
						this.monthlyCosts += 50000;
					} else if (this.services.getAirportLounge() > 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-5);
						}
						this.monthlyCosts -= 100000;
					}
				}
					
				case 2:{
					if(this.services.getAirportLounge() < 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+10);
						}
						this.money -= 500000;
						this.monthlyCosts += 150000;
					}
				}			
			}
		}		
	}
	
	public void setCateringPackage(int value){
		if((value <= 3) && (value >= 0)){
			this.services.setCateringPackage(value);
			switch (value) {
			    case 0:{
			    	if(this.services.getCateringPackage() == 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-3);
						}
						this.monthlyCosts = (this.monthlyCosts - (planes.size()*10000));
					} else if (this.services.getCateringPackage() == 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-7);
						}
						this.monthlyCosts -= (this.monthlyCosts - (planes.size()*50000));
					}
			    }
				case 1:{
					if(this.services.getCateringPackage() < 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+3);
						}
						this.monthlyCosts = (this.monthlyCosts + (planes.size()*10000));
					} else if (this.services.getCateringPackage() > 1){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()-4);
						}
						this.monthlyCosts = (this.monthlyCosts - (planes.size()*40000));
					}
				}
					
				case 2:{
					if(this.services.getCateringPackage() < 2){
						for (Plane p : planes){
							 p.setComfort(p.getComfort()+7);
						}
						this.monthlyCosts = (this.monthlyCosts + (planes.size()*50000));
					}
				}
			}
		}
	}
	
	
}
