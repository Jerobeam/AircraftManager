package com.backend.departments;

import com.backend.Airline;

public class IT {
	private int monthlyCosts;
	private boolean boughtSocialMediaModule;
	private boolean boughtHRModule;
	private boolean boughtAccountingModule;

	public IT() {
		this.setMonthlyCosts(0);
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
			airline.setMoney(airline.getMoney() - 1000000);
			double efficiency = airline.getMarketingDept().getEfficiency();
			airline.getMarketingDept().setEfficiency(efficiency + 0.05);
			this.setBoughtSocialMediaModule(true);
		}
	}

	public void buyHRModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtHRModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			airline.getHRDept().setCostReduction(airline.getHRDept().getCostReduction() - 0.1);
		}

	}

	public void buyAccountingModule(Airline airline) {
		// TODO: realistic values
		if (!this.isBoughtAccountingModule()) {
			this.setMonthlyCosts(this.getMonthlyCosts() + 1000);
			airline.getAccountingDept().setCostReduction(airline.getAccountingDept().getCostReduction() - 0.1);
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
