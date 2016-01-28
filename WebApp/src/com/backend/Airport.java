package com.backend;

public class Airport {
	
	private String name;
	private int slotCosts;
	private int groundCrew;
	
	public Airport(){
	}
	
	
	public int getGroundCrew() {
		return groundCrew;
	}


	public void setGroundCrew(int groundCrew) {
		this.groundCrew = groundCrew;
	}


	public Airport(String name, int slotCosts){
		this.setName(name);
		this.setSlotCosts(slotCosts);
		this.setGroundCrew(4);
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSlotCosts() {
		return slotCosts;
	}

	public void setSlotCosts(int slotCosts) {
		this.slotCosts = slotCosts;
	}
	
}
