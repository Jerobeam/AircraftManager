package com.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.server.AirlineServerEndpoint;

public class Game {
	private int playerCount;
	private final int maxPlayers = 4;
	private ArrayList<Airline> airlines = new ArrayList<Airline>();
	protected AirlineServerEndpoint server;
	public ArrayList<Airline> getAirlines() {
		return airlines;
	}

	private final int startMoney = 2000000;
	protected Calendar calendar = new GregorianCalendar(2015,Calendar.MARCH,17); 
	private Gameloop loop;
	
	public Game(AirlineServerEndpoint s) {
		server = s;
		this.playerCount = 0;
		loop = new Gameloop(this);
		loop.start();
	}

	public Gameloop getLoop() {
		return loop;
	}

	public void addPlayer(String name) {
		if (this.playerCount < this.maxPlayers) {
			this.airlines.add(new Airline(name, this.startMoney));
			this.playerCount++;
		}
	}

	public int getPlayerCount() {
		return this.playerCount;
	}

	public void tick() {
		this.calendar.add(Calendar.HOUR_OF_DAY, 1);
	}
	
	public Date getDate(){
		return this.calendar.getTime();
	}

}
