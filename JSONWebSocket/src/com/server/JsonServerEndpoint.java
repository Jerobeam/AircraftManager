package com.server;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
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

@ServerEndpoint("/JsonServerEndpoint")
public class JsonServerEndpoint {
	
	//Create Sessions Array
	private final Set<Session> sessions = new HashSet<>();
	private int sessionIds = 0;
	
	//Create Session
	@OnOpen
    public void open(Session session) {
		sessions.add(session);
		System.out.println("Server created");
		JsonObject initialMessage = this.createInitialMessage();
		this.sendToAllConnectedSessions(initialMessage);
		
		//Give Session an unique Id
		String sessionId = (String) session.getUserProperties().get("sessionId");
			if (sessionId == null) {
				session.getUserProperties().put("username", Integer.toString(sessionIds));
				System.out.println("new SessionId: " + sessionIds);
				sessionIds++;
			}
    }
	
	@OnClose
    public void close(Session session) {
		sessions.remove(session);
    }
	
	@OnMessage
    public void handleMessage(String message, Session session) {
		System.out.println("Incoming Message:");
        try (JsonReader reader = Json.createReader(new StringReader(message))) {        	
            JsonObject jsonMessage = reader.readObject();
            System.out.println("First Name: " + jsonMessage.getString("firstName"));
            System.out.println("Last Name: " + jsonMessage.getString("lastName"));
            
            JsonObject initialMessage = this.createChangeMessage();
    		this.sendToAllConnectedSessions(initialMessage);
        }
    }
	
	private JsonObject createInitialMessage() {
        JsonProvider provider = JsonProvider.provider();
        JsonObject message = provider.createObjectBuilder()
                .add("firstName", "Vincent=")
                .add("lastName", "Snitch")
                .add("action", "showAlert")
                .build();
        return message;
    }
	
	private JsonObject createChangeMessage() {
        JsonProvider provider = JsonProvider.provider();
        JsonObject message = provider.createObjectBuilder()
                .add("firstName", "Peter")
                .add("lastName", "Truckenbrod")
                .build();
        return message;
    }
	
	private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
            System.out.println("Message " + message.toString() + " sent to Session: " + session.toString());
        } catch (IOException ex) {}
    }
}
