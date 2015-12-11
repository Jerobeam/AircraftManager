package com.backend;

public class Airport {
	
	private String name;
	private int slotCosts;
	
	public Airport(){
	}
	
	public Airport(String name, int slotCosts){
		this.setName(name);
		this.setSlotCosts(slotCosts);
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
