package com.backend;

public class Flight {

	private Plane plane;
	private Route route;
	//Duration in full hours
	private double duration;
	private int numberOfBookings;
	
	public Flight(Plane plane, Route route){
		this.setPlane(plane);
		this.route = route;
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
		
		int priceBenefit = ((int)((double)route.getBasePrice()/(double)plane.getBookingPrice()*100))-100;
		//System.out.println("PriceBenefit:" + priceBenefit);
		int benefit = plane.getComfort() + (int)(plane.getAirline().getImage() + 0.5) +priceBenefit;
		return benefit;
		
	}
	public int getBookingPrice(){
		return plane.getBookingPrice();		
	}
	
}
