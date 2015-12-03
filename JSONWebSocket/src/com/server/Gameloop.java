package com.server;

public class Gameloop extends Thread {
	Game game;
	public Gameloop(Game g){
		game = g;
	}
	
	public void run()
	{
		System.out.println(game.getAirline().getMoney());
	}
}
