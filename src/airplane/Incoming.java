package airplane;

public class Incoming extends Airplane {

	public Incoming(String company, String city, String country, String date, String flightTime, String flightNum,
			int terminal) {
		super(company, city, country, date, flightTime, flightNum, terminal, "incoming");
	}

	public String toString() {
		return super.toString() + " and is an incoming flight";
	}
}
