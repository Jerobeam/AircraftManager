package com.backend;

public class Flight {

	private Plane plane;
	private Route route;
	//Duration in full hours
	private double duration;
	private int numberOfBookings;
	
	public Flight(Plane plane, Route route){
		this.setPlane(plane);
		this.setRoute(route);
		//+ 1 hour for plane maintenance
		this.duration = route.getDistance() / plane.getSpeed() + 1;
	}

	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public double getDuration() {
		return duration;
	}

	public int getNumberOfBookings() {
		return numberOfBookings;
	}

	public void setNumberOfBookings(int numberOfBookings) {
		this.numberOfBookings = numberOfBookings;
	}
	
}
