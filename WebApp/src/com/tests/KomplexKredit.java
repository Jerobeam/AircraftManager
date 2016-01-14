package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class KomplexKredit {

	@Test
	public void test() {
		
		GameMock g = new GameMock(new AirlineServerEndpointDummy());
		
		AirlineMock a = new AirlineMock("test",1000);
		
		g.getAirlines().add(a);
		
		a.takeCreditType1(1001);
		
		if(a.getFK() > 0){fail("Kreditbeschränkung funktioniert nicht");}
		
		a.takeCreditType1(499);
		
		if(a.getFK() != 499){fail("Kreditaufnehmen funktioniert nicht");}
		
		g.tickMonth();
		
		if(a.getFK() > 460){fail("Kreditrückzahlung funktioniert nicht");}
	}

}
