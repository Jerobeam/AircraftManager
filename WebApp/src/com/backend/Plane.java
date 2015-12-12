package com.backend;

import javax.json.JsonValue;

public abstract class Plane {
	protected String name;
	protected String type;
	protected Airport location;
	protected int capacity;
	//Range in km
	protected int range;
	protected int fuelCosts;
	protected int comfort;
	protected int upkeepCosts;
	//Speed in km/h
	protected int speed;
	protected int price;
	
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
	
	

}
