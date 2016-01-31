package com.backend;

import com.server.*;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

public class Gameloop extends Thread {
	
	private Game g;
	final private int TICKTIME = 1000/24;
	
	public Gameloop(Game g){
		this.g = g;
	}
	
	public void run(){
		while(true){
			g.tick();		
			String json = Json.createObjectBuilder()
		            .add("date", g.getDate().toString())
		            .add("players", g.getGameData())
		            .add("routes", g.getRoutesJSON())
		            .build()
		            .toString();
			
			g.server.sendJSONToAll(json);
			
			try {
				this.sleep(TICKTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
