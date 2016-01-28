package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class KomplexWerbungKosten {

	@Test
	public void test() {
		
		AirlineMock a = new AirlineMock("testA",1000000);
		double image = 5;
		int money = 1000000;
		
		a.getMarketingDept().getTVAD().setSize(2);
		
		a.monthlyCalculation();
		
		//-5000€ accounting costs, -320.000 Werbungskosten
		if(a.getMoney() != (money - 325000)) fail("falsche Berechnung der Kosten in Monat 1");
		money -= 325000;
		//-1 Image monatlicher Verlust, +9,6 Image durch Werbung
		if(a.getImage() != image - 1 + 9.6) fail ("falsches Image in Monat 1");
		image = image - 1 + 9.6;
		
		a.monthlyCalculation();
		
		//-5000€ accounting costs, -320.000 Werbungskosten
		if(a.getMoney() != (money - 325000)) fail("falsche Berechnung der Kosten in Monat 2");
		money = money - 325000;
		//-1 Image monatlicher Verlust, +6,72 Image durch Werbung
		if(a.getImage() != image - 1 + 6.72) fail ("falsches Image in Monat 2");
		image = image - 1 + 6.72;
		
		a.getITDept().buySocialMediaSoftware();
		
		a.monthlyCalculation();
		
		//-5000€ accounting costs, -320.000 Werbungskosten, -10.000 Social Media Software
		if(a.getMoney() != (money - 325000 - 10000)) fail("falsche Berechnung der Kosten in Monat 3");
		money = money - 325000 - 10000;
		//-1 Image monatlicher Verlust, +5,1744 Image durch Werbung (+10% Image durch Social Media Software)
		if(a.getImage() != image - 1 + 5.1744) fail ("falsches Image in Monat 3");
		image = image- 1 + 5.1744;
		
		a.getMarketingDept().getTVAD().setSize(0);
		a.monthlyCalculation();
		
		//-5000€ accounting costs, -10.000 Social Media Software
		if(a.getMoney() != (money - 15000)) fail("falsche Berechnung der Kosten in Monat 4");
		money = money - 15000;
		//-1 Image monatlicher Verlust
		if(a.getImage() != image - 1) fail ("falsches Image in Monat 4");
		image = image - 1;
		
		a.getMarketingDept().getTVAD().setSize(2);
		a.monthlyCalculation();
		
		//-5000€ accounting costs, -10.000 Social Media Software, -352000 TVWerbung (Werbung wurde teurer)
		if(a.getMoney() != (money - 352000 - 15000)) fail("falsche Berechnung der Kosten in Monat 5");
		money = money - 352000 - 15000;
		//-1 Image monatlicher Verlust, +4.165392 Image durch Werbung 
		//(Werbeeffekt hat sich 1Mal um 15% erholt, wird durch Socail Media Software um 10% erhöht und verringert sich um 30% durch die Werbeeffektverringerung)
		if(a.getImage() != image - 1 + 4.165392000000001) fail ("falsches Image in Monat 5");
		image = - 1 + 4.165392000000001;
	}

}
