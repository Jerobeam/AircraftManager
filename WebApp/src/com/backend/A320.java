package com.backend;

public class A320 extends Plane {

	
	public A320(String name, Airport location) {
		this.type = "A320";
		this.location = location;
		this.name = name;
		this.capacity = 150;
		this.speed = 891;
		this.range = 6150;
		this.comfort = 30;
		this.fuelCosts = 2700;
		this.upkeepCosts = 3000;//
		this.price = 600000;//
		this.value = 600000;//

	}

	

}
