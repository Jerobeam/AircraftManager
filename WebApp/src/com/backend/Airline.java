package com.backend;


public class Airline {
	
 public int money;
 public String name;
 private static int airlineCount;
 
public Airline(){
	airlineCount++;
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



}
