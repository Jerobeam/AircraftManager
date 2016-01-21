package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleWerbungKosten {

	@Test
	public void test() {
		
		AirlineMock a = new AirlineMock("testA",1000000);
		
		a.getMarketingDept().getINTERNETAD().setSize(2);
		
		a.monthlyCalculation();
		
		if(a.getMoney() != (1000000- 400000)) fail("falsche Berechnung der Kosten");
	}

}
