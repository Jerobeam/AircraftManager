package com.backend.departments;

import com.backend.Airline;

public class IT {
	private int monthlyCosts;
	private boolean boughtSocialMediaModule;
	private boolean boughtHRModule;
	private boolean boughtAccountingModule;

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
			airline.getMarketingDept().setEfficiency(efficiency + 0.05);
			this.setBoughtSocialMediaModule(true);
		}
	}
	
	public void sellSocialMediaSoftware(Airline airline) {
		// TODO: realistic values
		if (this.isBoughtSocialMediaModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			double efficiency = airline.getMarketingDept().getEfficiency();
			airline.getMarketingDept().setEfficiency(efficiency - 0.05);
			this.setBoughtSocialMediaModule(false);
		}
	}

	public void buyHRModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtHRModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			airline.getHRDept().setCostReduction(airline.getHRDept().getCostReduction() - 0.1);
			this.setBoughtHRModule(true);
		}
	}
	
	public void sellHRModule(Airline airline) {
		// TODO: realistic values
		if (this.isBoughtHRModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			airline.getHRDept().setCostReduction(airline.getHRDept().getCostReduction() + 0.1);
			this.setBoughtHRModule(false);
		}
	}

	public void buyAccountingModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			airline.getAccountingDept().setCostReduction(airline.getAccountingDept().getCostReduction() - 0.1);
			this.setBoughtAccountingModule(true);
		}
	}
	
	public void sellAccountingModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() - 1000);
			airline.getAccountingDept().setCostReduction(airline.getAccountingDept().getCostReduction() + 0.1);
			this.setBoughtAccountingModule(false);
		}
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

}
