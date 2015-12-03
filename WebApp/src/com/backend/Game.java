package com.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Game {
	private int playerCount;
	private final int maxPlayers = 4;
	private Airline[] airlines = new Airline[maxPlayers];
	private final int startMoney = 2000000;
	private Calendar calendar = new GregorianCalendar(2015,Calendar.MARCH,17); 
	
	public Game() {
		this.playerCount = 0;
	}

	public void addPlayer(String name) {
		if (this.playerCount < this.maxPlayers) {
			this.airlines[playerCount] = new Airline(name, this.startMoney);
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
