package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class SzenarioRoutenFlugzeugWahl {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("testA",30000000);
		
		g.getAirlines().add(a);
		
		Airport frankfurt = new Airport("frankfurt",1);
		
		a.buyPlane("A350", "testA", frankfurt);
		
		a.setPiloten(2);
		
		a.setStewardessen(6);
		
		g.occupyRoute(a, g.getRouteByName("Moskau-Mallorca"), a.getPlaneByName("testA"));
		
		AirlineMock b = new AirlineMock("testB",30000000);
		
		g.getAirlines().add(b);
		
		b.buyPlane("A350", "testB", frankfurt);
		
		b.setPiloten(2);
		
		b.setStewardessen(6);
		
		g.occupyRoute(b, g.getRouteByName("Moskau-Frankfurt"), b.getPlaneByName("testB"));
		
		for(int i = 0; i < 10; i++){
			g.tickDay();
		}
		
		if(a.getMoney() > b.getMoney()){fail("Airline mit schlechterer Taktik hat mehr Geld");}
	}

}
