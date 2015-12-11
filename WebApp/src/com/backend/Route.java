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
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	
	public Route(Airport airport1, Airport airport2){
		this.setName(airport1.getName() + " - " + airport2.getName());
		this.costs = airport1.getSlotCosts() + airport2.getSlotCosts();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}
	
	public int getDistance(){
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
	
	public void occupyRoute(Plane plane){
		this.planes.add(plane);
	}

	public JsonArrayBuilder getPlanesJSON(){
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
}
