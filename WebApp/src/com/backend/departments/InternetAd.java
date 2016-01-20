package com.backend.departments;

public class InternetAd extends Advertisement {
	
	private static final double REGENERATIONRATE = 1.1; //Regenerierungsrate des monathelichen Imagezuwachses, wenn keine Werbung aktiv ist (Paket 0)
	private static final double DECREASEMENTRATESMALL = 0.9; //Imageverlustrater bei wenig Werbung
	private static final double DECREASEMENTRATEBIG = 0.75; //Imageverlust bei viel Werbung
	private static final double INITIALINCREASEMENTSMALL = 5.0; //Initialer Imagezuwachs bei wenig Werbung
	private static final double INITIALINCREASEMENTBIG = 12.0; //Initialer Imagezuwachs bei viel Werbung
	private static final double MINIMALINCREASEMENTSMALL = 1.5; //Minimaler Imagezuwachs bei wenig Werbung
	private static final double MINIMALINCREASEMENTBIG = 2; //Minimaler Imagezuwachs bei viel Werbung
	
	private static byte currentCostLevel = 0; //Welcher Preis ist der aktuelle?
	private static int costLevels[] = {200000, 220000, 250000, 300000}; //4 Verschiedene Preise, Großes Paket kostet doppelt soviel
	private double currentSmallIncreasement = InternetAd.INITIALINCREASEMENTSMALL; //magezuwachs, wie er aktuelle für wenig Werbung wäre
	private double currentBigIncreasement = InternetAd.INITIALINCREASEMENTBIG; //Imagezuwachs, wie er aktuelle für viel Werbung wäre
	
	public InternetAd(int size, Marketing marketing) {
		super(marketing);
		
		this.setSize(size);
	}
	
	@Override
	public void setSize(int size) {
		//only increase currentCostLevel if someone switches from "no advert." to "any advert."
		if (size > 0 && size <=2 && this.getSize() == 0) {
			if (InternetAd.currentCostLevel <=4) {
				InternetAd.currentCostLevel++;
			}else{
				InternetAd.currentCostLevel = 4;
			}
		}
		
		switch (size) {
		case 0:
			this.setMonthlyCosts(0);
			this.setMonthlyImageIncreasement(0);
			break;
		case 1:
			if(this.getSize()==2){
				this.setMonthlyCosts(this.getMonthlyCosts()*2);
			}else{
				this.setMonthlyCosts(InternetAd.costLevels[currentCostLevel]);
			}
			this.setMonthlyImageIncreasement(this.getCurrentSmallIncreasement());
			break;
		case 2:
			if(this.getSize()==1){
				this.setMonthlyCosts((int)(this.getMonthlyCosts()*0.5));
			}else{
				this.setMonthlyCosts(InternetAd.costLevels[currentCostLevel]*2);
			}
			this.setMonthlyImageIncreasement(this.getCurrentBigIncreasement());
			break;
		default:
			break;
		}
		this.size = size;
	}



		//Decrease monthly Image grow over times
		public void iterate() {
			switch (this.getSize()) {
			//regenerate monthlyImage
			case 0:
				//increase bigger helper variable
				if ((this.getCurrentBigIncreasement() * InternetAd.REGENERATIONRATE)<InternetAd.INITIALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * InternetAd.REGENERATIONRATE);
				}else{
					this.setCurrentBigIncreasement(InternetAd.INITIALINCREASEMENTBIG);
				}
				
				//increase smaller helper variable
				if ((this.getCurrentSmallIncreasement() * InternetAd.REGENERATIONRATE)<InternetAd.INITIALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * InternetAd.REGENERATIONRATE);
				}else{
					this.setCurrentSmallIncreasement(InternetAd.INITIALINCREASEMENTSMALL);
				}
				break;
				
				
			//decrease monthly Image less for smaller package
			case 1:
				
				//decrease bigger helper variable
				if ((this.getCurrentBigIncreasement() * InternetAd.DECREASEMENTRATESMALL)>InternetAd.MINIMALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * InternetAd.DECREASEMENTRATESMALL);
				}else{
					this.setCurrentBigIncreasement(InternetAd.MINIMALINCREASEMENTBIG);
				}
				
				//decrease smaller helper variable
				if ((this.getCurrentSmallIncreasement() * InternetAd.DECREASEMENTRATESMALL)>InternetAd.MINIMALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * InternetAd.DECREASEMENTRATESMALL);
				}else{
					this.setCurrentSmallIncreasement(InternetAd.MINIMALINCREASEMENTSMALL);
				}
				
				//set monthly Image
				this.setMonthlyImageIncreasement(this.getCurrentSmallIncreasement());
				break;
				
			//decrease monthly Image more for bigger package
			case 2:
				//decrease bigger helper variable
				if ((this.getCurrentBigIncreasement() * InternetAd.DECREASEMENTRATEBIG)>InternetAd.MINIMALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * InternetAd.DECREASEMENTRATEBIG);
				}else{
					this.setCurrentBigIncreasement(InternetAd.MINIMALINCREASEMENTBIG);
				}
				
				//decrease smaller helper variable
				if ((this.getCurrentSmallIncreasement() * InternetAd.DECREASEMENTRATEBIG)>InternetAd.MINIMALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * InternetAd.DECREASEMENTRATEBIG);
				}else{
					this.setCurrentSmallIncreasement(InternetAd.MINIMALINCREASEMENTSMALL);
				}
				
				//set monthly Image
				this.setMonthlyImageIncreasement(this.getCurrentBigIncreasement());
				break;

			default:
				break;
			}
		}
		
		public double getCurrentSmallIncreasement() {
			return currentSmallIncreasement;
		}

		public void setCurrentSmallIncreasement(double currentSmallIncreasement) {
			this.currentSmallIncreasement = currentSmallIncreasement;
		}

		public double getCurrentBigIncreasement() {
			return currentBigIncreasement;
		}

		public void setCurrentBigIncreasement(double currentBigIncreasement) {
			this.currentBigIncreasement = currentBigIncreasement;
		}
		
		public static int getCurrentPrice(){
			return InternetAd.costLevels[InternetAd.currentCostLevel];
		}
		
}
