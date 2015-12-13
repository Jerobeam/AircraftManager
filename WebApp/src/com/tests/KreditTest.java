package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airline;
import com.backend.Game;

public class KreditTest {

	@Test
	public void test() {
Game game = null;
		
		game = new Game(new AirlineServerEndpointMock());
		
		if (game==null){fail("Game creation failed!");}
		
		Airline airline = null;
		airline = new Airline("AirBerlin",50);
		
		if (airline==null){fail("Airline creation failed!");}
		
		int startMoney = airline.getMoney();
		
		airline.takeCreditType1(1000);
		
		if(airline.getMoney() < startMoney + 1000){fail("Take Credit failed!");}
	}

}
