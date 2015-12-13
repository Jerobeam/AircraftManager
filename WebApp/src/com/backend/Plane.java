package com.backend;

import javax.json.JsonValue;

public abstract class Plane {
	protected String name;
	protected String type;
	protected Airport location;
	protected int capacity;
	protected int range;
	protected int fuelCosts;
	protected int comfort;
	protected int upkeepCosts;
	protected int speed;
	protected int price;
	protected int value;
	protected int flightsPerDay;
	protected int earnings;
	protected int bookingPrice;
	protected int routeCosts;
	
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

}
