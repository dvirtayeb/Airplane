package airplane;

public class Incoming extends Airplane {

	public Incoming(String company, String city, String country, String date, String flightTime, String flightNum,
			int terminal,String airport, String day) {
		super(company, city, country, date, flightTime, flightNum, terminal, "incoming",airport, day);
	}

	public String toString() {
		return super.toString() + " and is an incoming flight";
	}
}
