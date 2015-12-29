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
			for(int i = 0; i < g.getAirlines().size(); i++){
				g.getAirlines().get(i).monthlyCalculation();
			}
			
			String json = Json.createObjectBuilder()
		            .add("date", g.getDate().toString())
		            .add("players", g.getGameData())
		            .add("routes", g.getRoutesJSON())
		            .build()
		            .toString();
			
//			System.out.println(json);		 
			g.server.sendJSONToAll(json);
			
			try {
				this.sleep(TICKTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
