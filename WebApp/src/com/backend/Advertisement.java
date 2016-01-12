package com.backend;

public abstract class Advertisement {
	private double imageFactor;
	private int costs[];
	private static byte costLevel;
	
	public int getCosts(){
		return this.costs[costLevel];	
	}
	
	public static void raiseCostLevel(){
		costLevel++;
	}
}
