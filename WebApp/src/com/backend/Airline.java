package com.backend;


public class Airline {
	
 private int money;
 private String name;
 private Plane[] planes; 
 private int planeCount = 0;
 
public Airline(String name, int money){
	
	this.setName(name);
	this.setMoney(money);
}

public int getMoney() {
	return money;
}

public String getName() {
	return this.name;
}

public void setName(String name) {
	this.name = name;
}
public void setMoney(int money) {
	this.money = money;
}

public void buyPlane(String name){
	
	
}
public void getPlanes(){
	
}
}
