package com.tests;

import org.junit.Assert;

import com.backend.*;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		
		Game game = null;
		game = new Game();
		
		if (game==null){fail("Game not created");}
		
		Airline airline = null;
		airline = new Airline("AirBerlin",50);
		
		if (!"AirBerlin".equals(airline.getName())) {fail("Airline name not correct");}
		
		Assert.assertTrue(50 == airline.getMoney());
		
	}

}
