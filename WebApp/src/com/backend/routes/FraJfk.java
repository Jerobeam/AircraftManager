package com.backend.routes;

import com.backend.Airport;
import com.backend.Route;

public class FraJfk extends Route{

	public FraJfk() {
		super("Frankfurt Airport - JFK Airport");
		this.setDistance(8722);
		this.setDemand(1569);
		this.setCosts(51554);
		this.setAirport1(new Airport("FRA"));
		this.setAirport2(new Airport("FJK"));
	}

}
