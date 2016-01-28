package com.server;

import com.backend.Airline;
import com.backend.Airport;
import com.backend.Game;
import com.backend.Plane;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/AirlineServerEndpoint")
public class AirlineServerEndpoint {

	// Create Sessions Array
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());;

	public static Game game;

	// Create Session
	@OnOpen
	public void open(Session session) {
		sessions.add(session);
	}

	@OnClose
	public void close(Session session) {
		sessions.remove(session);
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		splitMessage(message);

		String airlineName = (String) session.getUserProperties().get("airlineName");
		if (airlineName == null) {
			session.getUserProperties().put("airlineName", message);
		}

	}

	private void sendToAllConnectedSessions(JsonObject message) {
		try{
			for (Session session : sessions) {
				sendToSession(session, message);
			}
		}catch(Exception e){
			System.out.println("Desync");
		}
	}

	private void sendToSession(Session session, JsonObject message) {
		try {
			session.getBasicRemote().sendText(message.toString());
		} catch (IOException ex) {
		}
	}

	private void splitMessage(String message) {
		String[] request = message.split("§");
		
		switch (request[0]) {
		case "create":
			if (game == null) {

				System.out.println("Game does not exist");
				game = new Game(this);
			}

			game.addPlayer(request[1]);
			System.out.println(game.getPlayerCount());
			break;
		case "changePrice":
			Airline airlinePriceChange = game.getAirlineByName(request[1]);
			String planeNamePriceChange = request[2];
			int price = Integer.parseInt(request[3]);
			game.changePrice(airlinePriceChange.getPlaneByName(planeNamePriceChange),price);
			break;
		case "buyPlane":
			//Messagetype of (Airline,Planetype, Planename, Planelocationairport)
			Airline airlineBuy = game.getAirlineByName(request[1]);
			String planeType = request[2];
			String planeNameBuy = request[3];
			Airport location = game.getAirportByName(request[4]);
			airlineBuy.buyPlane(planeType, planeNameBuy, location);
//			game.getAirlineByName(request[1]).buyPlane(request[2],request[3]);
			break;
		case "occupyRoute":
			System.out.println("Occupy Route called");
			Airline airlineOccupy = game.getAirlineByName(request[1]);
			String planeNameOccupy = request[2];
			String routeName = request[3];
			int routePrice = Integer.parseInt(request[4]);
			System.out.println(planeNameOccupy + " " + routeName + " " + routePrice);
			game.occupyRoute(airlineOccupy, game.getRouteByName(routeName), airlineOccupy.getPlaneByName(planeNameOccupy), routePrice);
			break;
		case "takeCredit":
			Airline a = game.getAirlineByName(request[1]);
			if(request[2].equals("1"))a.takeCreditType1(Integer.parseInt(request[3]));
			if(request[2].equals("2"))a.takeCreditType2(Integer.parseInt(request[3]));
			if(request[2].equals("3"))a.takeCreditType3(Integer.parseInt(request[3]));
			break;
		case "setPersonal":
			Airline airline = game.getAirlineByName(request[1]);
			if(request[2].equals("1"))airline.setPiloten(Integer.parseInt(request[3]));
			if(request[2].equals("2"))airline.setStewardessen(Integer.parseInt(request[3]));
			if(request[2].equals("3"))airline.setBodenpersonal(Integer.parseInt(request[3]));		
			if(request[2].equals("4"))airline.setWartungspersonal(Integer.parseInt(request[3]));
			break;
		case "buySocialMediaSoftware":
			game.getAirlineByName(request[1]).getITDept().buySocialMediaSoftware();
			break;
		case "buyHRModule":
			game.getAirlineByName(request[1]).getITDept().buyHRModule();
			break;
		case "buyAccountingModule":
			game.getAirlineByName(request[1]).getITDept().buyAccountingModule();
			break;
		case "sellSocialMediaSoftware":
			game.getAirlineByName(request[1]).getITDept().sellSocialMediaSoftware();
			break;
		case "sellHRModule":
			game.getAirlineByName(request[1]).getITDept().sellHRModule();
			break;
		case "sellAccountingModule":
			game.getAirlineByName(request[1]).getITDept().sellAccountingModule();
			break;
		case "pickupService":
			game.getAirlineByName(request[1]).getServices().setFreePickupService(Boolean.parseBoolean(request[2]));
			break;	
		case "seatReservation":
			game.getAirlineByName(request[1]).getServices().setFreeSeatReservation(Boolean.parseBoolean(request[2]));
			break;	
		case "wlan":
			game.getAirlineByName(request[1]).getServices().setWlanAboard(Boolean.parseBoolean(request[2]));
			break;	
		case "newspaper":
			game.getAirlineByName(request[1]).getServices().setNewsPaper(Boolean.parseBoolean(request[2]));
			break;	
		case "setLoungePackage":
			game.getAirlineByName(request[1]).getServices().setAirportLounge(Integer.parseInt(request[2]));
			break;	
		case "setCateringPackage":
			game.getAirlineByName(request[1]).getServices().setCateringPackage(Integer.parseInt(request[2]));
			break;
		case "setPlaneUpgrades":
			System.out.println(request[1] + " " + request[2] + " " + request[3] + " " + request[4]);
			game.getAirlineByName(request[1]).setPlaneUpgrades(request[2], request[3], request[4]);
			break;	
		
		//Marketing
		case "donate":
			game.getAirlineByName(request[1]).getMarketingDept().donate(Long.parseLong(request[2]));
			break;
		case "becomeSponsor":
			game.getAirlineByName(request[1]).getMarketingDept().becomeSponsor();
			break;
		case "stopSponsoring":
			game.getAirlineByName(request[1]).getMarketingDept().stopSponsoring();
			break;
		case "designPlaneLettering":
			game.getAirlineByName(request[1]).getMarketingDept().designPlaneLettering();
			break;
		//Advertisement
		case "setInternetAdSize":
			game.getAirlineByName(request[1]).getMarketingDept().getINTERNETAD().setSize(Integer.parseInt(request[2]));
			break;
		case "setTVAdSize":
			game.getAirlineByName(request[1]).getMarketingDept().getTVAD().setSize(Integer.parseInt(request[2]));
			break;
		default:
			break;
		}
	}

	public void sendJSONToAll(String s) {
		JsonObject json = Json.createReader(new StringReader(s)).readObject();
		sendToAllConnectedSessions(json);
	}
}
