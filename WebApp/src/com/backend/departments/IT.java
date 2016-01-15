package com.backend.departments;

import com.backend.Airline;

public class IT {
	private int monthlyCosts;
	private boolean boughtSocialMediaModule;
	private boolean boughtHRModule;
	private boolean boughtAccountingModule;
	//Costfactor influenced by the HR Module --> reduces "personalkosten" in Airline class
	private double hrCostsFactor = 1.0;

	public IT() {
		// TODO: realistic values
		this.setMonthlyCosts(10000);
		this.setBoughtAccountingModule(false);
		this.setBoughtHRModule(false);
		this.setBoughtSocialMediaModule(false);
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}

	public void buySocialMediaSoftware(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtSocialMediaModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			double efficiency = airline.getMarketingDept().getEfficiency();
			airline.getMarketingDept().setEfficiency(efficiency + 0.1);
			this.setBoughtSocialMediaModule(true);
		}
		System.out.println("buySocialMediaSoftware called");
	}
	
	public void sellSocialMediaSoftware(Airline airline) {
		// TODO: realistic values
		if (this.isBoughtSocialMediaModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			double efficiency = airline.getMarketingDept().getEfficiency();
			airline.getMarketingDept().setEfficiency(efficiency - 0.05);
			this.setBoughtSocialMediaModule(false);
		}
		System.out.println("sellSocialMediaSoftware called");
	}

	public void buyHRModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtHRModule()) {
			this.setHrCostsFactor(0.9);
			this.setBoughtHRModule(true);
		}
		System.out.println("buyHRModule called");
	}
	
	public void sellHRModule(Airline airline) {
		// TODO: realistic values
		if (this.isBoughtHRModule()) {
			this.setHrCostsFactor(1);
			this.setBoughtHRModule(false);
		}
		System.out.println("sellHRModule called");
	}

	public void buyAccountingModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			airline.getAccountingDept().setCostReduction(airline.getAccountingDept().getCostReduction() - 0.1);
			this.setBoughtAccountingModule(true);
		}
		System.out.println("buyAccountingModule called");
	}
	
	public void sellAccountingModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			airline.getAccountingDept().setCostReduction(airline.getAccountingDept().getCostReduction() + 0.1);
			this.setBoughtAccountingModule(false);
		}
		System.out.println("sellAccountingModule called");
	}

	public boolean isBoughtSocialMediaModule() {
		return boughtSocialMediaModule;
	}

	public void setBoughtSocialMediaModule(boolean boughtSocialMediaModule) {
		this.boughtSocialMediaModule = boughtSocialMediaModule;
	}

	public boolean isBoughtHRModule() {
		return boughtHRModule;
	}

	public void setBoughtHRModule(boolean boughtHRModule) {
		this.boughtHRModule = boughtHRModule;
	}

	public boolean isBoughtAccountingModule() {
		return boughtAccountingModule;
	}

	public void setBoughtAccountingModule(boolean boughtAccountingModule) {
		this.boughtAccountingModule = boughtAccountingModule;
	}

	public double getHrCostsFactor() {
		return hrCostsFactor;
	}

	private void setHrCostsFactor(double hrCostsFactor) {
		this.hrCostsFactor = hrCostsFactor;
	}
	
	

}
