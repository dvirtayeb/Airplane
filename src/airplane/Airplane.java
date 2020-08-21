package airplane;

import java.io.PrintWriter;
import java.util.Scanner;

public class Airplane {
	protected String city;
	protected MyDate date;
	protected String company;
	protected int hourFlightTime;
	protected int mintuesFlightTime;
	protected double secFlightTime;
	protected String flightNum;
	protected int terminal;
	protected String country;
	protected String directionFlight;
	protected String airport;

	public enum direction {
		incoming, outgoing
	};

	public Airplane(String company, String city, String country, String date, String flightTime, String flightNum,
			int terminal, String directionFlight,String airport) {
		this.company = company;
		this.city = city;
		this.country = country;
		this.date = new MyDate(date);
		setFlightTime(flightTime);
		this.flightNum = flightNum;
		this.terminal = terminal;
		this.directionFlight = directionFlight;
		this.airport=airport;
	}
	
	public String getDirectionIncoming() {
		return direction.valueOf("incoming").toString();
	}
	
	public int getDirection() {
		if(directionFlight.equals("incoming"))
			return 1;
		else {
			return 2;
		}		
	}

	public String getCity() {
		return city;
	}

	public String getCompany() {
		return company;
	}

	public int getMintuesFlightTime() {
		return mintuesFlightTime;
	}

	public double getSecFlightTime() {
		return secFlightTime;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public int getTerminal() {
		return terminal;
	}

	public String getCountry() {
		return country;
	}

	public Airplane(Scanner scan) {
		String str = scan.nextLine();
		String[] details = null;
		details = str.split(", ");
		this.company = details[0];
		this.city = details[1];
		this.date = new MyDate(details[2]);
		setFlightTime(details[3]);
		this.flightNum = details[4];
		this.terminal = Integer.parseInt(details[5]);
	}

	public void saveAirplane(PrintWriter writer) {
		writer.print(company + ", ");
		writer.print(city + ", ");
		writer.print(date.toString() + ", ");
		writer.print(hourFlightTime + ":" + mintuesFlightTime + ", ");
		writer.print(flightNum + ", ");
		writer.print(terminal + ", ");
	}

	private boolean setFlightTime(String flightTime) {
		String[] numbers = flightTime.split(":");
		this.hourFlightTime = Integer.parseInt(numbers[0]);
		this.mintuesFlightTime = Integer.parseInt(numbers[1]);
		this.secFlightTime = 55.555;
		return true;
	}

	public int getHourFlightTime() {
		return hourFlightTime;
	}

	public String getDate() {
		return date.toString();
	}

	public String getTime() {
		return hourFlightTime + ":" + mintuesFlightTime + ":" + secFlightTime;
	}

	public String getData() {
		return getDate() + "T" + getTime();
	}

	public String toString() {
		return "company: " + company + " destination: " + city + " date: " + date.toString() + " time to flight: "
				+ getTime() + " flight number: " + flightNum + " terminal: " + terminal;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

}
