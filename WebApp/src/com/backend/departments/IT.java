package com.backend.departments;

import com.backend.Airline;

public class IT {
	private int monthlyCosts = 0;
	private boolean boughtSocialMediaModule;
	private boolean boughtHRModule;
	private boolean boughtAccountingModule;
	//Costfactor influenced by the HR Module --> reduces "personalkosten" in Airline class
	private double hrCostsFactor = 1.0;
	private double accountingCostsFactor = 1.0;
	private Airline airline;

	public IT(Airline airline) {
		this.setBoughtAccountingModule(false);
		this.setBoughtHRModule(false);
		this.setBoughtSocialMediaModule(false);
		this.setAirline(airline);
	}

	public int getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(int monthlyCosts) {
		this.monthlyCosts = monthlyCosts;
	}

	public void buySocialMediaSoftware() {
		// TODO: realistic values
		if (!this.isBoughtSocialMediaModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			this.setBoughtSocialMediaModule(true);
		}
		System.out.println("buySocialMediaSoftware called");
	}
	
	public void sellSocialMediaSoftware() {
		// TODO: realistic values
		if (this.isBoughtSocialMediaModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			this.setBoughtSocialMediaModule(false);
		}
		System.out.println("sellSocialMediaSoftware called");
	}

	public void buyHRModule() {
		// TODO: realistic values
		if (!this.isBoughtHRModule()) {
			this.setHrCostsFactor(0.9);
			this.setBoughtHRModule(true);
		}
		System.out.println("buyHRModule called");
	}
	
	public void sellHRModule() {
		// TODO: realistic values
		if (this.isBoughtHRModule()) {
			this.setHrCostsFactor(1);
			this.setBoughtHRModule(false);
		}
		System.out.println("sellHRModule called");
	}

	public void buyAccountingModule() {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			this.setAccountingCostsFactor(this.getAccountingCostsFactor() - 0.15);
			this.setBoughtAccountingModule(true);
		}
		System.out.println("buyAccountingModule called");
	}
	
	public void sellAccountingModule() {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			this.setAccountingCostsFactor(this.getAccountingCostsFactor() + 0.15);
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

	public double getAccountingCostsFactor() {
		return accountingCostsFactor;
	}

	public void setAccountingCostsFactor(double accountingCostsFactor) {
		this.accountingCostsFactor = accountingCostsFactor;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
}
