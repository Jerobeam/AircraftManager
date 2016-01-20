package com.backend.departments;

public class TVAd extends Advertisement {

	private static final double REGENERATIONRATE = 1.15; //Regenerierungsrate des monathelichen Imagezuwachses, wenn keine Werbung aktiv ist (Paket 0)
	private static final double DECREASEMENTRATESMALL = 0.85; //Imageverlustrater bei wenig Werbung
	private static final double DECREASEMENTRATEBIG = 0.7; //Imageverlust bei viel Werbung
	private static final double INITIALINCREASEMENTSMALL = 4.0; //Initialer Imagezuwachs bei wenig Werbung
	private static final double INITIALINCREASEMENTBIG = 9.6; //Initialer Imagezuwachs bei viel Werbung
	private static final double MINIMALINCREASEMENTSMALL = 1.2; //Minimaler Imagezuwachs bei wenig Werbung
	private static final double MINIMALINCREASEMENTBIG = 1.6; //Minimaler Imagezuwachs bei viel Werbung
	
	private static byte currentCostLevel = 0; //Welcher Preis ist der aktuelle?
	private static int costLevels[] = {160000, 176000, 200000, 240000}; //4 Verschiedene Preise, Großes Paket kostet doppelt soviel
	private double currentSmallIncreasement = TVAd.INITIALINCREASEMENTSMALL; //magezuwachs, wie er aktuelle für wenig Werbung wäre
	private double currentBigIncreasement = TVAd.INITIALINCREASEMENTBIG; //Imagezuwachs, wie er aktuelle für viel Werbung wäre
	
	public TVAd(int size, Marketing marketing) {
		super(marketing);
		
		this.setSize(size);
	}
	
	@Override
	public void setSize(int size) {
		//only increase currentCostLevel if someone switches from "no advert." to "any advert."
		if (size > 0 && size <=2 && this.getSize() == 0) {
			if (TVAd.currentCostLevel <=4) {
				TVAd.currentCostLevel++;
			}else{
				TVAd.currentCostLevel = 4;
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
				this.setMonthlyCosts(TVAd.costLevels[currentCostLevel]);
			}
			this.setMonthlyImageIncreasement(this.getCurrentSmallIncreasement());
			break;
		case 2:
			if(this.getSize()==1){
				this.setMonthlyCosts((int)(this.getMonthlyCosts()*0.5));
			}else{
				this.setMonthlyCosts(TVAd.costLevels[currentCostLevel]*2);
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
				if ((this.getCurrentBigIncreasement() * TVAd.REGENERATIONRATE)<TVAd.INITIALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * TVAd.REGENERATIONRATE);
				}else{
					this.setCurrentBigIncreasement(TVAd.INITIALINCREASEMENTBIG);
				}
				
				//increase smaller helper variable
				if ((this.getCurrentSmallIncreasement() * TVAd.REGENERATIONRATE)<TVAd.INITIALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * TVAd.REGENERATIONRATE);
				}else{
					this.setCurrentSmallIncreasement(TVAd.INITIALINCREASEMENTSMALL);
				}
				break;
				
				
			//decrease monthly Image less for smaller package
			case 1:
				
				//decrease bigger helper variable
				if ((this.getCurrentBigIncreasement() * TVAd.DECREASEMENTRATESMALL)>TVAd.MINIMALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * TVAd.DECREASEMENTRATESMALL);
				}else{
					this.setCurrentBigIncreasement(TVAd.MINIMALINCREASEMENTBIG);
				}
				
				//decrease smaller helper variable
				if ((this.getCurrentSmallIncreasement() * TVAd.DECREASEMENTRATESMALL)>TVAd.MINIMALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * TVAd.DECREASEMENTRATESMALL);
				}else{
					this.setCurrentSmallIncreasement(TVAd.MINIMALINCREASEMENTSMALL);
				}
				
				//set monthly Image
				this.setMonthlyImageIncreasement(this.getCurrentSmallIncreasement());
				break;
				
			//decrease monthly Image more for bigger package
			case 2:
				//decrease bigger helper variable
				if ((this.getCurrentBigIncreasement() * TVAd.DECREASEMENTRATEBIG)>TVAd.MINIMALINCREASEMENTBIG) {
					this.setCurrentBigIncreasement(this.getCurrentBigIncreasement() * TVAd.DECREASEMENTRATEBIG);
				}else{
					this.setCurrentBigIncreasement(TVAd.MINIMALINCREASEMENTBIG);
				}
				
				//decrease smaller helper variable
				if ((this.getCurrentSmallIncreasement() * TVAd.DECREASEMENTRATEBIG)>TVAd.MINIMALINCREASEMENTSMALL) {
					this.setCurrentSmallIncreasement(this.getCurrentSmallIncreasement() * TVAd.DECREASEMENTRATEBIG);
				}else{
					this.setCurrentSmallIncreasement(TVAd.MINIMALINCREASEMENTSMALL);
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
			return TVAd.costLevels[TVAd.currentCostLevel];
		}

}
