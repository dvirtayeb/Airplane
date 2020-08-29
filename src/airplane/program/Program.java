package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import airplane.Airplane;
import airplane.Incoming;
import airplane.MyDate;
import airplane.Outgoing;

public class Program {
	
	static String flight;
	static MyDate startDate;
	static MyDate endDate;
	static String company;
	static String city;
	static String country;
	static String airport;
//	static String day;
	static List<Airplane> sortedListIncoming;
	static List<Airplane> sortedListOutcoming;
	static Boolean[] days;
	
	public static void main(String[] args) {

		List<Airplane> bengurion = new ArrayList<>();
		bengurion.add(new Outgoing("elal", "eilat", "israel", "2021-1-05", "20:15", "Il505", 3, "bengurion", "sunday"));
		bengurion.add(
				new Outgoing("elal", "tel-aviv", "israel", "2020-12-05", "20:20", "Il505", 3, "bengurion", "monday"));
		bengurion.add(
				new Incoming("alal", "modiin", "israel", "2020-05-05", "20:15", "Il505", 3, "bengurion", "saturday"));
		bengurion.add(
				new Incoming("elal", "tel-aviv", "israel", "2019-10-05", "20:15", "Il505", 3, "bengurion", "monday"));
		bengurion.add(
				new Incoming("elal", "shoham", "israel", "2020-10-05", "20:15", "Il505", 3, "bengurion", "sunday"));
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
//		boolean isText = args[0].equals("text");

		// search in web server HTML:
		if (args.length > 0) {
			boolean isText = args[0].equals("text");
			flight = args[1].toString();
			// Web UI:
			if (flight.equals("Departure") || flight.equals("Arrivals")) {
				startDate = new MyDate(args[2].toString());
				endDate = new MyDate(args[3].toString());
				company = args[4].toString();
				country = args[5].toString();
				city = args[6].toString();
				airport = args[7].toString();
				days = new Boolean[7];
				for (int i = 0; i < 7; i++)
					days[i] = (Boolean.parseBoolean(args[i + 8].toString()));
			}
			// Insert in the http line:
			else if (flight.equals("departures") || flight.equals("arrivals")) {
				country = args[3].toString();
				city = args[4].toString();
				if (flight.equals("departures"))
					flight = "Departure";
				else
					flight = "Arrivals";
				airport = args[5].toString();
				company = args[2].toString();
				int year = Integer.parseInt(args[8].toString());
				int month = Integer.parseInt(args[7].toString());
				int day = Integer.parseInt(args[6].toString());
				int year2 = Integer.parseInt(args[11].toString());
				int month2 = Integer.parseInt(args[10].toString());
				int day2 = Integer.parseInt(args[9].toString());
				startDate = new MyDate(year, month, day);
				endDate = new MyDate(year2, month2, day2);
				days = new Boolean[7];
				for (int i = 0; i < 7; i++) {
					days[i] = (Boolean.parseBoolean(args[i + 12].toString()));
				}
			}

			switch (flight) {
			
			// Web:
			case "Departure":
				DisplayHelper.showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days, isHtml, isText);
				if (isHtml)
					System.out.println("<br>");
				break;
			case "Arrivals":
				DisplayHelper.showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days, isHtml, isText);
				if (isHtml)
					System.out.println("<br>");
				break;
			// Html line:
				
			case "departures":
				DisplayHelper.showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days, isHtml, isText);
				break;
			case "arrivals":
				DisplayHelper.showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days, isHtml, isText);
				break;
			default:
				System.out.println("No flights to show");
				break;
			}
			// console :
		} else {
			Scanner s = new Scanner(System.in);
			bengurion = DisplayHelper.sort(bengurion);
			DisplayHelper.createFlight(s, bengurion);
			System.out.println(DisplayHelper.show(bengurion));
			DisplayHelper.sortTheList(s, bengurion, isHtml);
		}
	}
}
