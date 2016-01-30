package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class KomplexPlaneUpgrade {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("test",10000000);
		
		g.getAirlines().add(a);
		
		a.buyPlane("A320", "test", new Airport("test",1));
		
		a.setPiloten(2);
		
		a.setStewardessen(3);
		
		a.setBodenpersonal(8);
		
		a.setWartungspersonal(8);
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
		
		long moneystart = a.getMoney();
		
		g.tickDay();
		
		long moneyFromFirstFlight = a.getMoney() - moneystart;
		
		a.getPlaneByName("test").getUpgrades().setNewEngine(true);
		
		a.getPlaneByName("test").getUpgrades().setWinglets(true);
		
		a.getPlaneByName("test").getUpgrades().setSeat(0);
		
		moneystart = a.getMoney();
		
		g.tickDay();
		
		long moneyFromSecondFlight = a.getMoney() - moneystart;
		
		if(moneyFromFirstFlight > moneyFromSecondFlight){fail("Kalkulation falsch");}
	}

}
