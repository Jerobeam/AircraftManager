package com.backend;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Route {

	private String name;
	private int distance;
	private int demand;
	private Airport airport1;
	private Airport airport2;
	private int costs;
	private int basePrice;
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private ArrayList<Flight> flights = new ArrayList<Flight>();

	public Route(String name, Airport airport1, Airport airport2,int basePrice) {
		this.setName(name);
		this.costs = airport1.getSlotCosts() + airport2.getSlotCosts();
		this.basePrice = basePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return this.distance;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getCosts() {
		return costs;
	}
	

	public int getBasePrice() {
		return this.basePrice;
	}
	public Airport getAirport1() {
		return airport1;
	}

	public void setAirport1(Airport airport1) {
		this.airport1 = airport1;
	}

	public Airport getAirport2() {
		return airport2;
	}

	public void setAirport2(Airport airport2) {
		this.airport2 = airport2;
	}

	public void occupyRoute(Plane plane) {
		this.planes.add(plane);
	}
	public JsonArrayBuilder getFlightsJSON() {
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Flight f : flights) {
			json = Json.createObjectBuilder()
					.add("plane", f.getPlane().getName())
					.add("bookings", f.getNumberOfBookings())
					.build();

			jsonArray.add(json);
		}
		return jsonArray;
	}
	
	public JsonArrayBuilder getPlanesJSON() {
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();

		for (Plane p : planes) {
			json = Json.createObjectBuilder()
					.add("type", p.getType())
					.add("name", p.getName())
					.build();

			jsonArray.add(json);
		}
		return jsonArray;
	}

	public void cleanFlights() {
		flights.clear();

	}

	public ArrayList<Plane> getPlanes() {
		return planes;
	}
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void addFlight(Flight flight) {
		flights.add(flight);

	}

	public void createBookings() {
		
		int toBook = this.getDemand();
		boolean planesFull= true;
		
		for(Flight f : flights){
			f.setBenefit();
		}
		
		while (toBook>0 && !planesFull){
			for(Flight f : flights){
				f.setNumberOfBookings(toBook/flights.size());
				f.getPlane().addEarnings(f.getNumberOfBookings()*f.getPlane().getBookingPrice());
			}
		}
		
	}
}
