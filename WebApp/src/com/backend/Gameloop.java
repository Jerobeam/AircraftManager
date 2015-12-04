package com.backend;

import java.util.Calendar;

public class Gameloop extends Thread {
	
	private Game g;
	final private int TICKTIME = 1000;
	
	public Gameloop(Game g){
		this.g = g;
	}
	
	public void run(){
		while(true){
			g.calendar.add(Calendar.HOUR_OF_DAY, 1);
			System.out.println(g.getDate());
			try {
				this.sleep(TICKTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
