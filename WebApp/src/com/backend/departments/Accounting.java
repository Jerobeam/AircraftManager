package com.backend.departments;

public class Accounting {
	private int monthlyCosts;
	// Cost reduction as value between 0 and 1. 0,5 means half the costs
	private double costReduction;
	
	public Accounting(){
		//TODO: Set realistic values
		this.setMonthlyCosts(10000);
		this.setCostReduction(1.0);
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = (int) (monthlyCosts * this.getCostReduction());
	}
	
	public double getCostReduction() {
		return costReduction;
	}

	public void setCostReduction(double costsReduction) {
		this.costReduction = costsReduction;
	}
	
}
