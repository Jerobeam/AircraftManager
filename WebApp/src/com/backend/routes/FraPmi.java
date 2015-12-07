package com.backend.routes;

import com.backend.Airport;
import com.backend.Route;

public class FraPmi extends Route{

	public FraPmi() {
		super("Frankfurt Airport - Palma de Mallorca Airport");
		this.setDistance(1254);
		this.setDemand(1569);
		this.setCosts(51554);
		this.setAirport1(new Airport("FRA"));
		this.setAirport2(new Airport("PMI"));
	}
}
