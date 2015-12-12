package com.backend;

public class Flight {

	private Plane plane;
	private Route route;
	
	public Flight(Plane plane, Route route){
		this.setPlane(plane);
		this.setRoute(route);
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
	
	
}
