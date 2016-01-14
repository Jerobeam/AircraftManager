package com.tests;

import com.backend.Game;
import com.server.AirlineServerEndpoint;

public class GameMock extends Game {

	public GameMock(AirlineServerEndpoint s) {
		super(s);
		try {
			this.getLoop().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		
	}



}
