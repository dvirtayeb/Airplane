package airplane;

public class Outgoing extends airplane {
	
	public Outgoing (String company, String city,String country,  String date, String flightTime, String flightNum, int terminal) {
		super(company,city,country,date,flightTime,flightNum,terminal,"outgoing");
	}
	public String toString() { 
		return super.toString() + " and is an outgoing flight";
	}
}
