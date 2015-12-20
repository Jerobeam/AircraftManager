package com.backend.departments;

public class HR {
	private int monthlyCosts;
	private int amountPilots;
	private int amountStewars;
	// Cost reduction as value between 0 and 1. 0,5 means half the costs
	private double costReduction;
	
	public HR(){
		//TODO: Set realistic values
		this.setMonthlyCosts(10000);
		this.setCostReduction(1.0);
	}

	public double getCostReduction() {
		return costReduction;
	}

	public void setCostReduction(double costsReduction) {
		this.costReduction = costsReduction;
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = (int) (monthlyCosts * this.getCostReduction());
	}

	public int getAmountPilots() {
		return amountPilots;
	}

	public void setAmountPilots(int amountPilots) {
		this.amountPilots = amountPilots;
	}

	public int getAmountStewars() {
		return amountStewars;
	}

	public void setAmountStewars(int amountStewars) {
		this.amountStewars = amountStewars;
	}
	
	
}
