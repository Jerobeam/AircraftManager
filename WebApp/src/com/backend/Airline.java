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

import com.backend.departments.IT;
import com.backend.departments.Marketing;
import com.backend.departments.Service;
import com.backend.departments.TVAd;

public class Airline {

	private long money;
	private int monthlyCosts;
	private long ek;
	private long fk;
	private String name;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Credit> credits = new ArrayList<Credit>();
	private int planeCount = 0;
	private double image = 5;

	// Set Departments
	private final Marketing MARKETINGDEPT = new Marketing(this);
	private final IT ITDEPT = new IT(this);
	private Service services = new Service(this);

	//Personalkosten
	private final int PILOTENKOSTEN = 15000;
	private final int STWDKOSTEN = 5000;
	private final int BODENPERSONALKOSTEN = 3000;
	private final int WARTUNGSPERSONALKOSTEN = 4000;

	private int Piloten = 0;
	private int Stewardessen = 0;
	private int Bodenpersonal = 0;
	private int WartungsPersonal = 0;
	private int blockedPilotes = 0;
	private int blockedStewards = 0;
	private int blockedBodenpersonal = 0;
	private int blockedWartungspersonal = 0;

	public int getBlockedGroundCrew() {
		return blockedBodenpersonal;
	}

	public void setBlockedGroundCrew(int blockedGroundCrew) {
		this.blockedBodenpersonal = blockedGroundCrew;
	}

	public int getBlockedMaintenance() {
		return blockedWartungspersonal;
	}

	public void setBlockedMaintenance(int blockedMaintenance) {
		this.blockedWartungspersonal = blockedMaintenance;
	}

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
		if (piloten >= this.blockedPilotes)
			Piloten = piloten;
	}

	public int getStewardessen() {
		return Stewardessen;
	}

	public void setStewardessen(int stewardessen) {
		if (stewardessen >= this.blockedStewards)
			Stewardessen = stewardessen;
	}

	public int getBodenpersonal() {
		return Bodenpersonal;
	}

	public void setBodenpersonal(int bodenpersonal) {
		if (Bodenpersonal >= this.blockedBodenpersonal)
			Bodenpersonal = bodenpersonal;
	}

	public int getWartungspersonal() {
		return WartungsPersonal;
	}

	public void setWartungspersonal(int wartung) {
		if (WartungsPersonal >= this.blockedWartungspersonal)
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

	public void buyPlane(String type, String name, Airport location) {

		if (name.length() >= 1) {
			Plane p = this.createPlaneFromJson(name, type, location);
			if (this.getMoney() >= p.getPrice()) {
				this.planes.add(p);
				this.money = this.money - p.getPrice();
				// if lettering from marketing is bought increase image
				if (this.getMarketingDept().isLettering()) {
					this.getMarketingDept()
							.setMonthlyImageIncreasement(this.getMarketingDept().getMonthlyImageIncreasement() + 0.7);
				}
			}
		}
	}

	public Plane createPlaneFromJson(String name, String type, Airport location) {
		JSONParser parser = new JSONParser();

		try {

			Object fileObject = parser.parse(new InputStreamReader(getClass().getResourceAsStream("planes.json")));
			JSONObject planesJSON = (JSONObject) fileObject;

			JSONObject planeTypeJSON = (JSONObject) planesJSON.get(type);

			Plane p = new Plane(name, type, this);
			p.setLocation(location);
			p.setValue((int) (long) planeTypeJSON.get("value"));
			p.setCapacity((int) (long) planeTypeJSON.get("capacity"));
			p.setComfort((int) (long) planeTypeJSON.get("comfort"));
			p.setSpeed((int) (long) planeTypeJSON.get("speed"));
			p.setRange((int) (long) planeTypeJSON.get("range"));
			p.setFuelCosts((int) (long) planeTypeJSON.get("fuelCosts"));
			p.setUpkeepCosts((int) (long) planeTypeJSON.get("upkeepCosts"));
			p.setPrice((int) (long) planeTypeJSON.get("price"));
			p.setPilot((int) (long) planeTypeJSON.get("pilot"));
			p.setSteward((int) (long) planeTypeJSON.get("steward"));

			return p;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList getPlanes() {
		return planes;
	}

	public JsonArrayBuilder getPlanesJSON() {
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		try {
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
						.add("winglets", p.getUpgrades().isWinglets())
						.add("newEngine", p.getUpgrades().isNewEngine())
						.add("buyOutsideCleaning", p.getUpgrades().isBuyOutsideCleaning())
						.add("doOutsideCleaning", p.getUpgrades().isDoOutsideCleaning())
						.add("entertainmentPackage", p.getUpgrades().getEntertainmentPackage())
						.add("seat", p.getUpgrades().getSeat())					
				        .build();

				jsonArray.add(json);
			}
		} catch (Exception e) {
			System.out.println("Desync.");
		}
		
		return jsonArray;
	}

	public Plane getPlaneByName(String name) {
		Plane ret = null;
		for (Plane p : planes) {
			if (p.getName().equals(name)) {
				ret = p;
				break;
			}
		}
		return ret;
	}

	public void guvDay() {
		int sum = 0;

		for (Plane p : planes) {
			sum += p.getEarnings() - p.getCosts();
		}
		this.ek = this.getEK() + sum;
		this.money = this.money + sum;
	}

	public int getAV() {
		// Anlagevermögen
		int av = 0;
		for (Plane p : planes) {
			av = av + p.getValue();
		}
		return av;
	}

	public int getUV() {
		// Umlaufvermögen
		int uv = 0;
		return uv;
	}

	public void monthlyCalculation(){
		//iterate Credits
		for (Credit c : credits) {
			c.iterationStep();
		}
		
		for (Plane p : planes) {
			if (p.getUpgrades().isDoOutsideCleaning()) {
				p.getUpgrades().setDoOutsideCleaning(false);
			}
			if (p.getUpgrades().isBuyOutsideCleaning()) {
				p.getUpgrades().setDoOutsideCleaning(true);
				p.getUpgrades().setBuyOutsideCleaning(false);
			}
		}
		//billing hr
		int personalkosten = 0;
		personalkosten = (int) ((this.Piloten * this.PILOTENKOSTEN + this.Stewardessen * this.STWDKOSTEN
				+ this.Bodenpersonal * this.BODENPERSONALKOSTEN + this.WartungsPersonal * this.WARTUNGSPERSONALKOSTEN)
				* this.getITDept().getHrCostsFactor());
		
		//billing accounting depending on amount of planes
		int accountingCosts = this.getAccountingCosts();
		
		// billing departments
		long moneyNew = this.getMoney() - this.getITDept().getMonthlyCosts()
				- accountingCosts - this.getMarketingDept().getMonthlyCosts()
				- personalkosten - this.services.getMonthlyCosts();
		this.setMoney(moneyNew);
		
		//check if Social Analyse Software isBough
		if (this.getITDept().isBoughtSocialMediaModule()) {
			this.getMarketingDept().setEfficiency(1.1);
		}else{
			this.getMarketingDept().setEfficiency(1.0);
		}
		
		// monthly Image increasement
		if (this.getMarketingDept().getMonthlyImageIncreasementWithAds() > 0) {
			this.getMarketingDept().increaseImage(this.getMarketingDept().getMonthlyImageIncreasementWithAds());
		}

		// image loss over time
		this.getMarketingDept().decreaseImage(1);
		this.getMarketingDept().getINTERNETAD().iterate();
		this.getMarketingDept().getTVAD().iterate();
	}

	public void takeCreditType1(int amount) {
		double ek = this.getEK();
		if (ek / (this.getFK() + amount) > 2.0) {
			int back = (int) (amount * 0.1);
			credits.add(new Credit(this, amount, 2, back));
		}
	}

	public void takeCreditType2(int amount) {
		double ek = this.getEK();
		if (ek / (this.getFK() + amount) > 1.5) {
			int back = (int) (amount * 0.08);
			credits.add(new Credit(this, amount, 3, back));
		}
	}

	public void takeCreditType3(int amount) {
		double ek = this.getEK();
		if (ek / (this.getFK() + amount) > 1) {
			int back = (int) (amount * 0.06);
			credits.add(new Credit(this, amount, 5, back));
		}
	}

	public long getEK() {
		long eigenkap = 0;
		eigenkap = this.getAV() + this.getMoney() - this.getFK();
		return eigenkap;
	}

	public long getFK() {
		long fremdkap = 0;
		for (Credit c : credits) {
			fremdkap = fremdkap + c.getAmount();
		}
		return fremdkap;
	}

	public long getBilanzSum() {
		return this.getFK() + this.getEK();
	}

	public Marketing getMarketingDept() {
		return MARKETINGDEPT;
	}

	public IT getITDept() {
		return ITDEPT;
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

	public Service getServices() {
		return services;
	}

	public void setServices(Service services) {
		this.services = services;
	}
	
	public void setPlaneUpgrades(String planeName,String upgrade, String value){
			Plane p =this.getPlaneByName(planeName);
			switch(upgrade){
				case "winglets": {
					System.out.println(value);
					p.getUpgrades().setWinglets(Boolean.parseBoolean(value));
					break;
				}
				case "newEngine": {
					p.getUpgrades().setNewEngine(Boolean.parseBoolean(value));
					break;
				}
				case "buyOutsideCleaning": {
					p.getUpgrades().setBuyOutsideCleaning(Boolean.parseBoolean(value));
					break;
				}
				case "seat": {
					p.getUpgrades().setSeat(Integer.parseInt(value));
					break;
				}
				case "enterteinmentPackage": {
					p.getUpgrades().setEntertainmentPackage(Integer.parseInt(value));
					break;
				}
			}
		}
	
	public int getPersonalCosts(){
		int personalkosten = 0;
		personalkosten = (int) ((this.Piloten * this.PILOTENKOSTEN + this.Stewardessen * this.STWDKOSTEN
				+ this.Bodenpersonal * this.BODENPERSONALKOSTEN + this.WartungsPersonal * this.WARTUNGSPERSONALKOSTEN)
				* this.getITDept().getHrCostsFactor());
		return personalkosten;
	}
	
	public int getCreditCosts(){
		int repayments = 0;
		for (Credit c : credits) {
			repayments += c.getRepayment();
		}
		return repayments;
	}
	
	public int getAccountingCosts(){
		int planesAmount = this.getPlanes().size();
		int accountingCosts = (int)(5000 + (planesAmount * 3500 * this.getITDept().getAccountingCostsFactor()) + 0.5);
		return accountingCosts;
	}
}
