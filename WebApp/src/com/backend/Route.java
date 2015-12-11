package com.backend;

public class Route {

	private String name;
	private int distance;
	private int demand;
	private Airport airport1;
	private Airport airport2;
	private int costs;
	
	public Route(String name, Airport airport1, Airport airport2){
		this.setName(name);
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
}
