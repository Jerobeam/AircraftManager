package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class SzenarioService {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("testA",100000000);
		
		g.getAirlines().add(a);
		
		Airport frankfurt = new Airport("frankfurt",1);
		
		a.buyPlane("A350", "testA1", frankfurt);
		
		a.buyPlane("A350", "testA2", frankfurt);
		
		a.setPiloten(4);
		
		a.setStewardessen(12);
		
		g.occupyRoute(a, g.getRouteByName("Moskau-Frankfurt"), a.getPlaneByName("testA1"));
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Teneriffa"), a.getPlaneByName("testA2"));
		
		AirlineMock b = new AirlineMock("testB",100000000);
		
		g.getAirlines().add(b);
		
		b.buyPlane("A350", "testB1", frankfurt);
		
		b.buyPlane("A350", "testB2", frankfurt);
		
		b.setPiloten(4);
		
		b.setStewardessen(12);
		
		g.occupyRoute(b, g.getRouteByName("Moskau-Frankfurt"), b.getPlaneByName("testB1"));
		
		g.occupyRoute(b, g.getRouteByName("Frankfurt-Teneriffa"), b.getPlaneByName("testB2"));
		
		//SERVICELEISTUNGEN KAUFEN FÜR AIRLINE B
		
		b.getServices().setAirportLounge(2);
		
		b.getServices().setCateringPackage(2);
		
		b.getMarketingDept().getINTERNETAD().setSize(2);
		
		b.getPlaneByName("testB1").setBookingPrice(250);
		
		for(int i = 0; i < 100; i++){
			g.tickDay();
		}
		
		if(a.getMoney() > b.getMoney()){fail("Airline mit schlechterer Taktik hat mehr Geld");}
	}

}
