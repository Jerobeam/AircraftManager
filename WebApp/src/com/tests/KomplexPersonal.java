package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class KomplexPersonal {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("test",1000000000);
		
		g.getAirlines().add(a);
		
		a.buyPlane("A320", "test", new Airport("test",1));
				
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
		
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() > 0){fail("Fehler Anzahl Piloten");}
		
		a.setPiloten(2);
		
		a.setStewardessen(3);
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
		
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() != 1){fail("Fehler belegen von Route");}
		
		if(a.getBlockedPilotes() != 2 && a.getBlockedStewards() != 3){fail("Personal nicht beschäftigt");}
		
		a.buyPlane("A320", "test2", new Airport("test2",1));
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test2"));
		
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() > 1){fail("Fehler Anzahl Piloten");}
		
		a.setPiloten(4);
		
		a.setStewardessen(6);
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test2"));
		
		if(a.getBlockedPilotes() != 4 && a.getBlockedStewards() != 6){fail("Personal nicht beschäftigt");}
		
	}

}
