package com.tests;

import com.backend.*;
import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		
		Game game = null;
		game = new Game();
		
		if (game==null){fail("Game not created");}
		
	}

}
