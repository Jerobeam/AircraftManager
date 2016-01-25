package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class KomplexServiceTest {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("testA",10000000);
		
		Airport frankfurt = new Airport("frankfurt",1);
		
		a.buyPlane("A350", "testA", frankfurt);
		
		long moneyStart = a.getMoney();
		
		g.getAirlines().add(a);
		
		a.getServices().setAirportLounge(2);
		
		a.getServices().setCateringPackage(2);
		
		if(moneyStart-500000 != a.getMoney()) fail("Geld für Lounge nicht abgezogen");
		
		moneyStart = a.getMoney();
		
		g.tickMonth();
		
		if(moneyStart-155000 != a.getMoney()) fail("Monatliche Kosten nicht richtig berechnet");
	}

}
