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
	private ArrayList<Airport> airports = new ArrayList<Airport>();
	protected AirlineServerEndpoint server;

	public ArrayList<Airline> getAirlines() {
		return airlines;
	}

	private final int startMoney = 20000000;
	protected Calendar calendar = new GregorianCalendar(2015, Calendar.MARCH, 17);
	private Gameloop loop;

	public Game(AirlineServerEndpoint s) {
		server = s;
		this.playerCount = 0;
		loop = new Gameloop(this);
		loop.start();
		
		Airport fraport = new Airport("Frankfurt Airport", 1337);
		airports.add(fraport);
		Airport pmi = new Airport("Palma de Mallorca Airport", 8888);
		airports.add(pmi);
		Route fraPmi = new Route("Frankfurt-Mallorca", pmi, fraport,120);
		fraPmi.setDistance(1612);
		fraPmi.setDemand(800);
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
			currentMonth = calendar.get(Calendar.MONTH); 
		}
		if (calendar.get(Calendar.HOUR_OF_DAY)== 0){
			tickDay();
		}
	}
	public void tickMonth(){
		for(Airline airline : airlines){
			airline.monthlyCalculation();
		}
	}
	public void tickDay(){
		createFlights();
		for (Airline a : airlines) {
			a.guvDay();
		}
	}

	private void createFlights() {
		ArrayList<Plane> planes = new ArrayList<Plane>();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		for (Route r : routes) {
			r.cleanFlights();
			planes = r.getPlanes();
			
			for (Plane p : planes){
				p.resetEarnings();
				int numberOfFlights = (int) (24/((double)r.getDistance()/(double)p.getSpeed()+1));
				p.setFlightsPerDay(numberOfFlights);
				for (int i=0;i<numberOfFlights;i++){
					Flight flight = new Flight(p);
					r.addFlight(flight);
				}
			}
			r.createBookings();	
		}	
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
					.add("boughtSocialMediaModule", a.getITDept().isBoughtSocialMediaModule())
					.add("boughtHRModule", a.getITDept().isBoughtHRModule())
					.add("boughtAccountingModule", a.getITDept().isBoughtAccountingModule())
					.build();
			System.out.println("FK:" + a.getFK());
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
					//.add("flights", r.getFlightsJSON())
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
	
	public Airport getAirportByName(String name) {
		Airport airport = null;
		for (Airport a : airports) {
			if (a.getName().equals(name)) {
				airport = a;
				break;
			}
		}
		return airport;
	}

	public void occupyRoute(Route route, Plane plane){
		route.occupyRoute(plane);
		plane.setRouteCosts(route.getCosts());
		plane.setBookingPrice(route.getBasePrice());
	}

}
