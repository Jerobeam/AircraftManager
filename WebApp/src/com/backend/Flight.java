package com.backend;

public class Flight {

	private Plane plane;
	//Duration in full hours
	private double duration;
	private int numberOfBookings;
	
	public Flight(Plane plane){
		this.setPlane(plane);
	}

	public Plane getPlane() {
		return plane;
	}
	
	public void setPlane(Plane plane) {
		this.plane = plane;
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

	public int getBenefit(){
		
		int benefit = plane.getComfort() + plane.getAirline().getImage();
		return benefit;
		
	}
	public int getBookingPrice(){
		return plane.getBookingPrice();		
	}
	
}
