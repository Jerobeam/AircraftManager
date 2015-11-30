package backend;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
		Airline airline = new Airline();
		airline.setMoney(1000);
		g.setAirline(airline );
		Gameloop loop = new Gameloop(g);
		loop.start();
	}

}
