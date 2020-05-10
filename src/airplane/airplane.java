package airplane;

import java.io.PrintWriter;
import java.util.Scanner;

public class airplane {
	private String city; 
	private MyDate date; 
	private String company; 
	private int hourFlightTime;
	private int mintuesFlightTime; 
	private double secFlightTime;
	private String flightNum; 
	private int terminal;

	
	public airplane(String company, String city,  String date, String flightTime, String flightNum, int terminal) {
		this.company = company;
		this.city = city;
		this.date = new MyDate(date);
		setFlightTime(flightTime);
		this.flightNum = flightNum;
		this.terminal = terminal;
	}
	public airplane(Scanner scan) {
		String str=scan.nextLine();
		String [] details = null;
		details = str.split(", ");
		this.company=details[0];
		this.city=details[1];
		this.date=new MyDate(details[2]);
		setFlightTime(details[3]);
		this.flightNum= details[4];
		this.terminal =Integer.parseInt(details[5]);
	}
	public void saveAirplane (PrintWriter writer) {
		writer.print(company +", ");
		writer.print(city + ", ");
		writer.print(date.toString() + ", ");
		writer.print( hourFlightTime +":"+ mintuesFlightTime + ", ");
		writer.print(flightNum + ", ");
		writer.print(terminal + ", ");
	}
	
	
	private boolean setFlightTime(String flightTime) {
		String [] numbers=flightTime.split(":");
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
		return "company: " +company + " destination: " + city + " date: " + date.toString() + "time to flight: " + getTime() +
				" flight number: " + flightNum + " terminal: " + terminal;
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
