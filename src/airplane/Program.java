package airplane;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
	static String flight;
	static MyDate startDate;
	static MyDate endDate;
	static String company;
	static String city;
	static String country;
	static String airport;
	static boolean[] days;
	static List<Airplane> sortedListIncoming;
	static List<Airplane> sortedListOutcoming;

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
		boolean isText = args[0].equals("text");

		// search in web server HTML:
		if (args.length > 0) {
			flight = args[1].toString();
			// Web UI:
			if (flight.equals("Departure") || flight.equals("Arrivals")) {
				startDate = new MyDate(args[2].toString());
				endDate = new MyDate(args[3].toString());
				company = args[4].toString();
				country = args[5].toString();
				city = args[6].toString();
				airport = args[7].toString();
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
				ArrayList<String> days = new ArrayList<>();
				for (int i = 0; i < 7; i++) {
					days.add(args[i + 12].toString());
				}
			}

			switch (flight) {

			// Web:
			case "Departure":
				showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days,
						isHtml, isText);
				if (isHtml)
					System.out.println("<br>");
				break;
			case "Arrivals":
				showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days,
						isHtml, isText);
				if (isHtml)
					System.out.println("<br>");
				break;
			// Html line:

			case "departures":
				showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days,
						isHtml, isText);
				break;
			case "arrivals":
				showResultSearchFlight(bengurion, flight, startDate, endDate, company, city, country, airport, days,
						isHtml, isText);
				break;
			default:
				System.out.println("No flights to show");
				break;
			}
			// console :
		} else {
			Scanner s = new Scanner(System.in);
			bengurion = sort(bengurion);
			createFlight(s, bengurion);
			System.out.println(show(bengurion));
			sortTheList(s, bengurion, isHtml, isText);
		}
	}

	private static void showResultSearchFlight(List<Airplane> bengurion, String flight, MyDate startDate,
			MyDate endDate, String company, String city, String country, String airport, boolean[] day2, boolean isHtml,
			boolean isText) {
		sortedListIncoming = new ArrayList<>();
		sortedListOutcoming = new ArrayList<>();
		insertToSortedList(bengurion, flight, startDate, endDate, company, city, country, airport, day2, isHtml,
				isText);

	}

	private static void insertToSortedList(List<Airplane> bengurion, String flight, MyDate startDate, MyDate endDate,
			String company, String city, String country, String airport, boolean[] day2, boolean isHtml,
			boolean isText) {
		for (int i = 0; i < bengurion.size(); i++) {
			int directionFlight = bengurion.get(i).getDirection();
			switch (flight) {

			case "Departure":
				if (directionFlight == 2)
					sortedListIncoming.add(bengurion.get(i));
				break;
			case "Arrivals":
				if (directionFlight == 1)
					sortedListOutcoming.add(bengurion.get(i));
				break;
			default:
				i = bengurion.size();
				break;
			}
		}
		if (flight.equals("Departure")) {
			showSortedList(sortedListIncoming, startDate, endDate, company, city, country, airport, day2, isHtml,
					isText);
		} else if (flight.equals("Arrivals")) {
			showSortedList(sortedListOutcoming, startDate, endDate, company, city, country, airport, day2, isHtml,
					isText);
		} else {
			System.out.println("no flights");
		}

	}

	private static void showSortedList(List<Airplane> sortedList, MyDate startDate, MyDate endDate, String company,
			String city, String country, String airport, boolean[] days, boolean isHtml, boolean isText) {
		List<Airplane> finalSortedListByAllVariables = new ArrayList<>();
		Airplane airplaneToEnter;
		System.out.println("Here are the sort by the Date:" + startDate + ",Until: " + endDate + ", By company: "
				+ company + ", By city: " + city + ", By country: " + country + ", By airport: " + airport); // need to
																												// add
																												// by
																												// days
		if (isHtml)
			System.out.println("<br>");
		System.out.println("--------------------");
		if (isHtml) {
			System.out.println("<br>");
		} else if (isText) {
			System.out.println("\n");
		}
		System.out.println("the List: ");
		if (isHtml) {
			System.out.println("<br>");
		}
		MyDate tempMyDate = new MyDate("1-1-1");
		for (int i = 0; i < sortedList.size(); i++) {
			airplaneToEnter = sortedList.get(i);

			if (!(startDate.toString().equals(tempMyDate.toString()))
					|| !(endDate.toString().equals(tempMyDate.toString()))) {
				if (!(airplaneToEnter.date.daysCount(startDate, airplaneToEnter.date) >= 0)
						|| !(airplaneToEnter.date.daysCount(endDate, airplaneToEnter.date) <= 0)) {
					sortedList.set(i, null);
					continue;
				}
			}

			if (company == null)
				company = "null";
			if (!company.equals("null")) {
				if (!airplaneToEnter.getCompany().equals(company)) {
					sortedList.set(i, null);
					continue;
				}
			}

			if (city == null)
				city = "null";
			if (!city.toString().equals("null")) {
				if (!airplaneToEnter.getCity().equals(city)) {
					sortedList.set(i, null);
					continue;
				}
			}

			if (country == null)
				country = "null";
			if (!country.equals("null")) {
				if (!airplaneToEnter.getCountry().equals(country)) {
					sortedList.set(i, null);
					continue;
				}
			}

			if (airport == null)
				airport = "null";
			if (!airport.equals("null")) {
				if (!airplaneToEnter.getAirport().equals(airport)) {
					sortedList.set(i, null);
					continue;
				}
			}
			for (int j = 0; j < 7; j++) {
				if (!days[i])
					sortedList.set(i, null);

			}

			finalSortedListByAllVariables.add(airplaneToEnter);

		}
		if (finalSortedListByAllVariables.size() > 0) {
			System.out.println(finalSortedListByAllVariables.toString());
		} else {
			System.out.println("We didn't found a flight for your search");
		}
	}

	private static void sortTheList(Scanner s, List<Airplane> bengurion, boolean isHtml, boolean isText) {
		List<Integer> select = new ArrayList<>();
		sortedListIncoming = new ArrayList<>();
		sortedListOutcoming = new ArrayList<>();
		sortSelectionByUser(s, select);
		if (select.get(0) == 1) {// incoming flight
			detailsFromUserToSort(s, select);
		}
		if (select.get(0) == 2) { // out going flight
			detailsFromUserToSort(s, select);
		}
		insertToSortedListOnConsole(bengurion, select, startDate, endDate, company, city, country, airport, days,
				isHtml, isText);
	}

	private static void insertToSortedListOnConsole(List<Airplane> bengurion, List<Integer> select, MyDate startDate,
			MyDate endDate, String company, String city, String country, String airport, boolean[] day2, boolean isHtml,
			boolean isText) {
		int selected = select.get(0);
		for (int i = 0; i < bengurion.size(); i++) {
			int directionFlight = bengurion.get(i).getDirection();
			Airplane flight = bengurion.get(i);
			switch (selected) {

			case 1:
				if (directionFlight == 1)
					sortedListIncoming.add(flight);

				break;
			case 2:
				if (directionFlight == 2)
					sortedListOutcoming.add(flight);
				break;

			default:
				i = bengurion.size();
				break;
			}
		}
		if (selected == 0)
			showSortedList(bengurion, startDate, endDate, company, city, country, airport, day2, isHtml, isText);
		else if (selected == 1)
			showSortedList(sortedListIncoming, startDate, endDate, company, city, country, airport, day2, isHtml,
					isText);
		else if (selected == 2)
			showSortedList(sortedListOutcoming, startDate, endDate, company, city, country, airport, day2, isHtml,
					isText);
		else {
			System.out.println("no flight");
		}
	}

	private static void detailsFromUserToSort(Scanner s, List<Integer> select) {
		if (select.get(1) == 1) {// sort by date
			System.out.println("please provide me the start date");
			startDate = getDateFromUser(s);
			System.out.println("please provide me the end date");
			endDate = getDateFromUser(s);
		}
		if (select.get(2) == 1) { // sort by company
			System.out.println("please provide me the company name");
			company = s.next();
		}
		if (select.get(3) == 1) { // sort by city
			System.out.println("please provide me the city name");
			city = s.next();
		}
		if (select.get(4) == 1) { // sort by country
			System.out.println("please provide me the country name");
			country = s.next();
		}
		if (select.get(5) == 1) { // sort by airport
			System.out.println("please provide me the airport name");
			airport = s.next();
		}
		if (select.get(6) == 1) { // sort by airport
			System.out.println("please provide me the days you would like to search by");
			days = new boolean[7];
			days = sortByDays(s, days);
		}
	}

	private static boolean[] sortByDays(Scanner s, boolean[] days) {
		String day = "";
		for (int i = 0; i < 7; i++) {
			if (i == 0)
				day = "Sunday";
			if (i == 1)
				day = "Monday";
			if (i == 2)
				day = "Tuesday";
			if (i == 3)
				day = "Wednesday";
			if (i == 4)
				day = "Thursday";
			if (i == 5)
				day = "Friday";
			if (i == 6)
				day = "Saturday";
			System.out.println("For search by " + day + " press Y, for not press N");
			days[i] = yesOrNo(s);
		}

		return days;
	}

	private static boolean yesOrNo(Scanner s) {
		char answer;
		do {
			answer = s.next().charAt(0);
			if (answer == 'Y' || answer == 'y')
				return true;
			else if (answer == 'N' || answer == 'n')
				return false;
			System.out.println("You have enterd a wrong answer, try again");
		} while (answer != 'N' || answer != 'n' || answer != 'Y' || answer != 'y');
		return false;
	}

	private static MyDate getDateFromUser(Scanner s) {
		System.out.println("year:");
		int y = s.nextInt();
		System.out.println("month:");
		int m = s.nextInt();
		System.out.println("day:");
		int d = s.nextInt();
		MyDate date = new MyDate(y, m, d);
		return date;
	}

	private static void sortSelectionByUser(Scanner s, List<Integer> select) {
		System.out.println(
				"please choose from the list which sort would you like to do, you can choose more then just one option");

		// first place in array 1 for incoming , 2 for outgoing
		System.out.println("first select which type of flight would you like to sort 1 for incoming or 2 for outgoing");
		select.add(s.nextInt());
		// second place by date:
		System.out.println("for sort by date press 1 , if you dont want to sort by date press 0");
		select.add(s.nextInt());
		// third place for company:
		System.out.println("for sort by company press 1 , if you dont want to sort by company press 0");
		select.add(s.nextInt());
		// for city sort:
		System.out.println("for sort by city press 1 , if you dont want to sort by city press 0");
		select.add(s.nextInt());
		// for country sort:
		System.out.println("for sort by country press 1 , if you dont want to sort by country press 0");
		select.add(s.nextInt());
		// for airport sort:
		System.out.println("for sort by airport press 1 , if you dont want to sort by airport press 0");
		select.add(s.nextInt());
		System.out.println("for sort by day press 1 , if you dont want to sort by day press 0");
		select.add(s.nextInt());
	}

	// CREATE Flight:

	private static int showOptions(Scanner s) {
		System.out.println("hello, please enter number for what you would like to do");
		System.out
				.println("enter 1 for create incoming flight , 2 for for create outgoing flight, and 0 for continue: ");
		return s.nextInt();
	}

	private static void createFlight(Scanner s, List<Airplane> bengurion) {
		int select;
		do {
			select = showOptions(s);
			switch (select) {
			case 1:
				bengurion.add(createIncoming(s));
				break;
			case 2:
				bengurion.add(createoutgoing(s));
				break;
			}
		} while (select != 0);

	}

	private static Airplane createoutgoing(Scanner s) {
		System.out.println("plsese enter a company");
		String com = s.next();
		System.out.println("plsese enter a city which the flight fly to");
		String city = s.next();
		System.out.println("please enter a country which the flight fly to");
		String country = s.next();
		System.out.println("plsese enter a date by this order yyyy-mm-dd");
		String date = s.next();
		System.out.println("plsese enter a time to flight by this order hh:mm");
		String time = s.next();
		System.out.println("please enter the number of the flight");
		String Fn = s.next();
		System.out.println("please enter a terminal");
		int T = s.nextInt();
		System.out.println("please enter a  airport");
		String airport = s.next();
		System.out.println("please enter a  day");
		String day = s.next();
		return new Incoming(com, city, country, date, time, Fn, T, airport, day);
	}

	private static Airplane createIncoming(Scanner s) {
		System.out.println("plsese enter a company");
		String com = s.next();
		System.out.println("plsese enter a city where the flight come from");
		String city = s.next();
		System.out.println("please enter a country where the flight come from");
		String country = s.next();
		System.out.println("plsese enter a date by this order yyyy-mm-dd");
		String date = s.next();
		System.out.println("plsese enter a time land by this order hh:mm");
		String time = s.next();
		System.out.println("please enter the number of the flight");
		String Fn = s.next();
		System.out.println("please enter a  airport");
		String airport = s.next();
		System.out.println("please enter a  day");
		String day = s.next();
		return new Incoming(com, city, country, date, time, Fn, 3, airport, day);
	}

	// OTHER:

	private static String show(List<Airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++)
			sb.append(bengurion2.get(i).toString() + "\n");
		return sb.toString();
	}

	private static List<Airplane> sort(List<Airplane> airport) {
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < airport.size(); i++) {
			strings.add(airport.get(i).getData());
		}
		strings.sort(Comparator.naturalOrder());
		List<Airplane> bengurion = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			for (int j = 0; j < airport.size(); j++) {
				if (strings.get(i).equals(airport.get(j).getData())) {
					bengurion.add(airport.get(j));
					break;
				}
			}
		}
		return bengurion;
	}
}
