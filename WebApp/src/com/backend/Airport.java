package com.backend;

public class Airport {
	
	private String name;
	
	public Airport(){
	}
	
	public Airport(String name){
		this.setName(name);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
