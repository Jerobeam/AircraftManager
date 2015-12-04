package com.tests;

import org.junit.Assert;

import com.backend.*;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		
		Game game = null;
		
		game = new Game(new AirlineServerEndpointMock());
		
		if (game==null){fail("Game creation failed!");}
		
		Airline airline = null;
		airline = new Airline("AirBerlin",50);
		
		if (airline==null){fail("Airline creation failed!");}
		
		int startMoney = airline.getMoney();
		
		airline.buyPlane("A320", "AB01");
		
		if (airline.getMoney() == startMoney - 68000000){fail("Plane Price calculation failed!");}
		
		if (airline.getPlanes().isEmpty()){fail("Plane Buy failed!");}
		
		
	}

}
