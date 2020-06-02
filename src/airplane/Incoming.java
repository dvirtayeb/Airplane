package airplane;

public class Incoming extends airplane {
	
	public Incoming (String company, String city,  String date, String flightTime, String flightNum, int terminal) {
		super(company,city,date,flightTime,flightNum,terminal,"incoming");
	}
	
	public String toString() { 
		return super.toString() + " and is an incoming flight";
	}
}
