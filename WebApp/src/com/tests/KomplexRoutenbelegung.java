package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class KomplexRoutenbelegung {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("test",10000000);
		
		g.getAirlines().add(a);
		
		a.buyPlane("A320", "test", new Airport("test",1));
				
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
				
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() == 1){fail("Route darf nicht belegt sein, zu wenig Piloten und Stewards");}
		
		a.setPiloten(2);
		
		a.setStewardessen(3);
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
		
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() != 1){fail("Fehler belegen von Route");}
		
		long moneystart = a.getMoney();
		
		g.tickDay();
		
		if(a.getMoney() < moneystart){fail("Kalkulation falsch");}
	}

}
