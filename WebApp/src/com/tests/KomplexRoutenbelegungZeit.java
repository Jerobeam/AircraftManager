package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;
import com.backend.Game;

public class KomplexRoutenbelegungZeit {

	@Test
	public void test() {
		
		Game g = new Game(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("test",10000000);
		
		g.getAirlines().add(a);
		
		a.buyPlane("A320", "test", new Airport("test",1));
		
		a.setPiloten(2);
		
		a.setStewardessen(3);
		
		g.occupyRoute(a, g.getRouteByName("Frankfurt-Mallorca"), a.getPlaneByName("test"));
		
		if(g.getRouteByName("Frankfurt-Mallorca").getPlanes().size() != 1){fail("Fehler belegen von Route");}
		
		long moneystart = a.getMoney();
		
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(a.getMoney() < moneystart){fail("Kalkulation falsch");}
	}

}
