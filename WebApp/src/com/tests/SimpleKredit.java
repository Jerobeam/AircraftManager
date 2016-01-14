package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.backend.Credit;

public class SimpleKredit {

	@Test
	public void testCredit() {
		AirlineMock a = new AirlineMock("Testairline", 0);
		Credit c = new Credit(a, 1000, 1, 100);
		c.iterationStep();
		if(c.getAmount() != 910){fail("Kredit falsch berechnet");}
	}
}
