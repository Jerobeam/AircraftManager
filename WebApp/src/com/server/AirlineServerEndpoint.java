package com.server;

import com.backend;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/AirlineServerEndpoint")
public class AirlineServerEndpoint {
	
	//Create Sessions Array
	private final Set<Session> sessions = new HashSet<Session>();
	
	//Create Session
	@OnOpen
    public void open(Session session) {
		sessions.add(session);
    }
	
	@OnClose
    public void close(Session session) {
		sessions.remove(session);
    }
	
	@OnMessage
    public void handleMessage(String message, Session session){
		String request[];
		request = message.split("$");

		if (requestCode[0].equals("Create")){
			Airline airline = new Airline(request[1]);
		}

		airline.setMoney(2000000);
		
		String airlineName = (String) session.getUserProperties().get("airlineName");
		if (airlineName == null) {
			session.getUserProperties().put("airlineName", message);
		}
		
		System.out.println("Airline added: " + message);
    }

	private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {}
    }
}
