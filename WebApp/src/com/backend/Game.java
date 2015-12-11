package com.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.server.AirlineServerEndpoint;

public class Game {
	private int playerCount;
	private int currentMonth = 2;
	private final int maxPlayers = 4;
	private ArrayList<Airline> airlines = new ArrayList<Airline>();
	protected AirlineServerEndpoint server;

	public ArrayList<Airline> getAirlines() {
		return airlines;
	}

	private final int startMoney = 2000000;
	protected Calendar calendar = new GregorianCalendar(2015, Calendar.MARCH, 17);
	private Gameloop loop;

	public Game(AirlineServerEndpoint s) {
		server = s;
		this.playerCount = 0;
		loop = new Gameloop(this);
		loop.start();
		
		Airport fraport = new Airport("Frankfurt Airport", 1337);
		Airport pmi = new Airport("Palma de Mallorca Airport", 8888);
		Route fraPmi = new Route(pmi, fraport);
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
		if (calendar.get(Calendar.MONTH)!= currentMonth){
			tickMonth();
		}
	}
	public void tickMonth(){
		
	}

	public Date getDate() {
		return this.calendar.getTime();
	}

	public JsonArrayBuilder getGameData() {

		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Airline a : airlines) {
			json = Json.createObjectBuilder().add("name", a.getName())
					.add("bank", a.getMoney())
					.add("av", a.getAV())
					.add("uv", a.getUV())
					.add("ek", a.getEK())
					.add("fk", a.getFK())
					.add("sum", a.getBilanzSum())
					.add("planes", a.getPlanesJSON())
					.build();

			jsonArray.add(json);
		}

		return jsonArray;
	}

	public Airline getAirlineByName(String s) {
		Airline ret = null;
		for (Airline a : airlines) {
			if (a.getName().equals(s)) {
				ret = a;
				break;
			}
		}

		return ret;

	}

	public void buyPlane(Airline airline, String type, String name) {
		airline.buyPlane(type, name);
	}

}
