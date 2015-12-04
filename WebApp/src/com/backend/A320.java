package com.backend;

public class A320 extends Plane {

	
	public A320(String name, Airport location) {

		this.location = location;
		this.name = name;
		this.capacity = 180;
		this.speed = 891;
		this.range = 6150;
		this.comfort = 30;
		this.fuelCosts = 2700;
		this.upkeepCosts = 3000;//

	}

	public int getCosts() {
		return this.fuelCosts + this.upkeepCosts;
	}

}
