package com.backend;

import javax.json.JsonValue;

import com.backend.departments.PlaneUpgrade;

public class Plane {

	private String name;
	private String type;
	private Airport location;
	private int capacity;
	private int range;
	private int fuelCosts;
	private int comfort;
	private int upkeepCosts;
	private int speed;
	private int price;
	private int value;
	private int flightsPerDay;
	private int earnings;
	private int bookingPrice;
	private int routeCosts;
	private int steward;
	private int pilot;
	private int benefit;
	private Airline airline;
	private PlaneUpgrade upgrades;

	public int getPilot() {
		return pilot;
	}

	public void setPilot(int pilot) {
		this.pilot = pilot;
	}

	public Plane(String name, String type, Airline airline){
		this.setName(name);
		this.setType(type);
		this.airline = airline;
		this.upgrades = new PlaneUpgrade(this, airline);
		this.routeCosts = 0;
	}
	
	public Airline getAirline(){
		return this.airline;
	}
	
	public Airport getLocation() {
		return location;
	}

	public void setLocation(Airport location) {
		this.location = location;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getFuelCosts() {
		return fuelCosts;
	}

	public void setFuelCosts(int fuelCosts) {
		this.fuelCosts = fuelCosts;
	}

	public int getUpkeepCosts() {
		return upkeepCosts;
	}

	public void setUpkeepCosts(int upkeepCosts) {
		this.upkeepCosts = upkeepCosts;
	}

	public int getRouteCosts() {
		return routeCosts;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setComfort(int comfort) {
		this.comfort = comfort;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public int getValue(){
		return this.value;
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public int getCosts() {
		return this.fuelCosts + this.upkeepCosts + this.routeCosts;
	}
	
	public int getEarnings() {
		return this.earnings;
	}
	public void setEarnings(int e) {
		this.earnings = e;
	}
	public int getBookingPrice() {
		return this.bookingPrice;
	}
	
	public void setBookingPrice(int p) {
		this.bookingPrice = p;
	}

	public int getComfort() {
		return this.comfort;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getFlightsPerDay() {
		return this.flightsPerDay;
	}
	
	public void setFlightsPerDay(int i) {
		this.flightsPerDay = i;
		
	}

	public void addEarnings(int i) {
		this.earnings = this.earnings +i;
		
	}

	public void resetEarnings() {
		this.earnings = 0;
		
	}

	public void setRouteCosts(int costs) {
		this.routeCosts = costs;		
	}


	public int getSteward() {
		return steward;
	}

	public void setSteward(int steward) {
		this.steward = steward;
	}

	public PlaneUpgrade getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(PlaneUpgrade upgrades) {
		this.upgrades = upgrades;
	}
	
}
