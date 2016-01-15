package com.tests;

import com.backend.Airline;
import com.backend.Airport;
import com.backend.Plane;

public class AirlineMock extends Airline{

	public AirlineMock(String name, long money) {
		super(name, money);
	}

	@Override
	public Plane createPlaneFromJson(String name, String type, Airport location) {
		Plane p = new Plane(name, type, this);
		if(type.equals("A320")){
			p.setCapacity(180);
			p.setComfort(30);
			p.setSpeed(891);
			p.setRange(6150);
			p.setFuelCosts(2700);
			p.setUpkeepCosts(3500);
			p.setPrice(8000000);
			p.setValue(8000000);
			p.setPilot(2);
			p.setSteward(3);
		}
		if(type.equals("A350")){
			p.setCapacity(314);
			p.setComfort(35);
			p.setSpeed(910);
			p.setRange(14350);
			p.setFuelCosts(3500);
			p.setUpkeepCosts(4000);
			p.setPrice(21500000);
			p.setValue(21500000);
			p.setPilot(2);
			p.setSteward(6);
		}
		return p;
	}

}
