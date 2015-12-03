package com.backend;


public class Game {
	private int playerCount;
	private Airline[] airlines = new Airline[4];
	private int startMoney = 2000000;
	
	public Game(){
		this.playerCount = 0;
	}
	
	public void addPlayer(String name){
		
		this.airlines[0] = new Airline(name,this.startMoney);
		this.playerCount++;
		System.out.println("added player");
	}
	
	public int getPlayerCount(){
		return this.playerCount;
	}
	
}
