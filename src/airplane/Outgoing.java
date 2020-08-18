package airplane;

import java.util.ArrayList;

public class Outgoing extends Airplane {
	ArrayList<Airplane> outGoing;
	public Outgoing(String company, String city, String country, String date, String flightTime, String flightNum,
			int terminal) {
		super(company, city, country, date, flightTime, flightNum, terminal, "outgoing");
		outGoing = new ArrayList<>();
		
	}
	
	public void addOutGoing(Airplane a ) {
		outGoing.add(a);
	}

	public String toString() {
		return super.toString() + " and is an outgoing flight";
	}
}
