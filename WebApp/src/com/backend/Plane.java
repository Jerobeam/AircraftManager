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
	protected Flight flight;
	protected int price;
	protected int value;
	
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
		return this.fuelCosts + this.upkeepCosts;
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

}
