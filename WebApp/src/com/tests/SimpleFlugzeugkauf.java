package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Airport;

public class SimpleFlugzeugkauf {

	@Test
	public void test() {
		
		AirlineMock a = new AirlineMock("test",10000000);
		
		a.buyPlane("A320", "test", new Airport("test",1));
		
		if(a.getPlanes().size() != 1){fail("Flugzeug wurde nicht der Liste hinzugefügt");}
		
		if(a.getMoney() == 10000000) {fail("Geld wurde nicht abgezogen");};
	}

}
