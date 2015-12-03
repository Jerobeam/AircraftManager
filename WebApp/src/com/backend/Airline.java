package com.backend;


public class Airline {
	
 private int money;
 private String name;
 private static int airlineCount;
 
public Airline(String name, int money){
	airlineCount++;
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



}
