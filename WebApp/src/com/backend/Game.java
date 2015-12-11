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
	private ArrayList<Route> routes = new ArrayList<Route>();
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
		
		Airport fraport = new Airport("Frankfurt", 1337);
		Airport pmi = new Airport("Mallorca", 8888);
		Route fraPmi = new Route(pmi, fraport);
		routes.add(fraPmi);
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
	
	public JsonArrayBuilder getRoutesJSON(){
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Route r : routes) {
			json = Json.createObjectBuilder()
					.add("name", r.getName())
					.add("distance", r.getDistance())
					.add("demand", r.getDemand())
					.add("costs", r.getCosts())
					.add("planes",r.getPlanesJSON())
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
	
	public Route getRouteByName(String name) {
		Route route = null;
		for (Route r : routes) {
			if (r.getName().equals(name)) {
				route = r;
				break;
			}
		}
		return route;
	}

	public void occupyRoute(Route route, Plane plane){
		route.occupyRoute(plane);
	}

}
