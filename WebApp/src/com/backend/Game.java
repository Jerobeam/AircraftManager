package com.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import com.backend.departments.InternetAd;
import com.backend.departments.TVAd;
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
		
		
		
		Airport frankfurt = new Airport("Frankfurt Airport", 1500);
		airports.add(frankfurt);
		Airport mallorca = new Airport("Palma de Mallorca Airport", 2300);
		airports.add(mallorca);
		Airport moskau = new Airport("Moskau Airport", 1200);
		airports.add(moskau);
		Airport london = new Airport("London Airport", 1900);
		airports.add(london);
		Airport teneriffa = new Airport("Teneriffa Airport", 2000);
		airports.add(teneriffa);
		
		
		Route fra_mal = new Route("Frankfurt-Mallorca", mallorca, frankfurt, 120);
		fra_mal.setDistance(1612);
		fra_mal.setDemand(1100);
		routes.add(fra_mal);
		
		Route mos_lon =  new Route("Moskau-London", moskau, london ,200);
		mos_lon.setDistance(2510);
		mos_lon.setDemand(500);
		routes.add(mos_lon);
		
		Route mos_fra =  new Route("Moskau-Frankfurt", moskau, frankfurt ,180);
		mos_fra.setDistance(2021);
		mos_fra.setDemand(400);
		routes.add(mos_fra);
		
		Route mos_mal =  new Route("Moskau-Mallorca", moskau, mallorca ,290);
		mos_mal.setDistance(3091);
		mos_mal.setDemand(750);
		routes.add(mos_mal);
		
		Route lon_ten =  new Route("London-Teneriffa", london, teneriffa ,190);
		lon_ten.setDistance(2900);
		lon_ten.setDemand(900);
		routes.add(lon_ten);
		
		Route lon_mal =  new Route("London-Mallorca", london, mallorca ,105);
		lon_mal.setDistance(1350);
		lon_mal.setDemand(900);
		routes.add(lon_mal);
		
		Route fra_ten =  new Route("Frankfurt-Teneriffa", frankfurt, teneriffa ,200);
		fra_ten.setDistance(3250);
		fra_ten.setDemand(850);
		routes.add(fra_ten);
		
		Route mal_ten =  new Route("Mallorca-Teneriffa", mallorca, teneriffa ,120);
		mal_ten.setDistance(2250);
		mal_ten.setDemand(500);
		routes.add(mal_ten);
		
		
		
		
		
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
				//System.out.println("Number flights =" + numberOfFlights);
				for (int i=0;i<numberOfFlights;i++){
					Flight flight = new Flight(p,r);
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
		ArrayList<Airline> copy = new ArrayList<Airline>(airlines);
		for (Airline a : copy) {
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
					.add("isLettering", a.getMarketingDept().isLettering())
					.add("isSponsoring", a.getMarketingDept().isSponsoring())
					.add("piloten", a.getPiloten())
					.add("stewardessen", a.getStewardessen())
					.add("bodenpersonal", a.getBodenpersonal())
					.add("wartung", a.getWartung())
					.add("cateringPackage", a.getServices().getCateringPackage())
					.add("airportLounge", a.getServices().getAirportLounge())
					.add("newsPaper", a.getServices().isNewsPaper())
					.add("freeSeatReservation", a.getServices().isFreeSeatReservation())
					.add("freePickupService", a.getServices().isFreePickupService())
					.add("wlanAboard", a.getServices().isWlanAboard())
					.add("tvAdSize", a.getMarketingDept().getTVAD().getSize())
					.add("internetAdSize", a.getMarketingDept().getINTERNETAD().getSize())
					.add("currentTvAdCosts", TVAd.getCurrentPrice())
					.add("currentInternetAdCosts", InternetAd.getCurrentPrice())
					.add("image", a.getImage())
					.build();
			jsonArray.add(json);
		}

		return jsonArray;
	}

	public JsonArrayBuilder getRoutesJSON(){
		JsonObject json;
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		ArrayList<Route> copy = new ArrayList<Route>(routes);
		for (Route r : copy) {
			json = Json.createObjectBuilder()
					.add("name", r.getName())
					.add("distance", r.getDistance())
					.add("demand", r.getDemand())
					.add("costs", r.getCosts())
					.add("planes",r.getPlanesJSON())
					.add("basicPrice", r.getBasePrice())
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

	public void occupyRoute(Airline airlineOccupy, Route route, Plane plane){
		int freePilotes = airlineOccupy.getPiloten() - airlineOccupy.getBlockedPilotes();
		int freeStewards = airlineOccupy.getStewardessen() - airlineOccupy.getBlockedStewards();
		//if(plane.getPilot() <= freePilotes && plane.getSteward() <= freeStewards){
		for (Route r : routes) {
			ArrayList<Plane> planes = r.getPlanes();
			for(Plane p: planes){
				if (p.getName() == plane.getName()){
					r.deletePlane(plane);
					break;
				}
			}
		}
		route.occupyRoute(plane);
		plane.setBookingPrice(route.getBasePrice());
		airlineOccupy.setBlockedPilotes(airlineOccupy.getBlockedPilotes()+plane.getPilot());
		airlineOccupy.setBlockedStewards(airlineOccupy.getBlockedStewards()+plane.getSteward());
	//}
	}
	public void occupyRoute(Airline airlineOccupy, Route route, Plane plane, int price){
		int freePilotes = airlineOccupy.getPiloten() - airlineOccupy.getBlockedPilotes();
		int freeStewards = airlineOccupy.getStewardessen() - airlineOccupy.getBlockedStewards();
		//if(plane.getPilot() <= freePilotes && plane.getSteward() <= freeStewards){
		for (Route r : routes) {
			ArrayList<Plane> planes = r.getPlanes();
			for(Plane p: planes){
				if (p.getName() == plane.getName()){
					r.deletePlane(plane);
					break;
				}
			}
		}
		route.occupyRoute(plane);
		plane.setBookingPrice(price);
		airlineOccupy.setBlockedPilotes(airlineOccupy.getBlockedPilotes()+plane.getPilot());
		airlineOccupy.setBlockedStewards(airlineOccupy.getBlockedStewards()+plane.getSteward());
	//}
	}
	
	public void changePrice(Plane plane, int price) {
		plane.setBookingPrice(price);
		
	}

	
}
