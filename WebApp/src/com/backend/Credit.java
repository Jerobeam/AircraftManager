package com.backend;

public class Credit {
	public Credit(Airline o, int a, int p, int r )
	{
		owner = o;
		amount = a;
		payRent = p;
		repayment = r;
		owner.setMoney(owner.getMoney() + amount);
	}
	Airline owner;
	private int amount;
	public int getAmount() {
		return amount;
	}
	public int getPayRent() {
		return payRent;
	}
	public int getRepayment() {
		return repayment;
	}
	private int payRent;
	private int repayment;
	public void iterationStep(){
		if(amount > 0){
			if(amount > repayment){
				owner.setMoney(owner.getMoney() - repayment);
				amount = amount + ((amount * payRent)/100);
				amount = amount - repayment;
			}
			else {
				amount = amount + ((amount * payRent)/100);
				owner.setMoney(owner.getMoney() - amount);
				amount = 0;
			}
		}
	}
}
