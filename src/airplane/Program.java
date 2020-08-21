package airplane;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
	static MyDate startDate;
	static MyDate endDate;
	static String company;
	static String city;
	static String country;
	static String airport;
	static List<Airplane> sortedListIncoming;
	static List<Airplane> sortedListOutcoming;

	public static void main(String[] args) {

		/*
		 * List<Airplane> bengurion = new ArrayList<>(); bengurion.add(new
		 * Outgoing("al-al", "tel-aviv", "israel", "2021-1-05", "20:15", "Il505", 3));
		 * bengurion.add(new Outgoing("al-al", "tel-aviv", "israel", "2020-12-05",
		 * "20:20", "Il505", 3)); bengurion.add(new Incoming("al-al", "tel-aviv",
		 * "israel", "2020-05-05", "20:15", "Il505", 3)); bengurion.add(new
		 * Incoming("al-al", "tel-aviv", "israel", "2019-10-05", "20:15", "Il505", 3));
		 * boolean isHtmlFromWeb = args.length > 0 && args[0].equalsIgnoreCase("html");
		 * boolean isHtml =args[0].equalsIgnoreCase("html"); // boolean
		 * isDeparturesFromWeb = args.length > 1 &&
		 * args[1].equalsIgnoreCase("departures"); boolean isdepartures =
		 * args[1].equalsIgnoreCase("departure"); boolean isArrivals =
		 * args[1].equalsIgnoreCase("arrivals"); // boolean boolCompany =
		 * args[1].equalsIgnoreCase(company); // boolean boolStartDate =
		 * args[5].equalsIgnoreCase(startDate.toString()); // boolean boolEndDate =
		 * args[6].equalsIgnoreCase(endDate.toString()); //boolean boolcity =
		 * args[].equalsIgnoreCase(city); if (isdepartures) { for (int i = 0; i <
		 * bengurion.size(); i++) { System.out.println(bengurion.get(i)); if(isHtml)
		 * System.out.println("<br>"); } } else if(isArrivals) {
		 * System.out.println("Arrival 1"); } else { System.out.println("..."); }
		 * 
		 */

		Scanner s = new Scanner(System.in);
		List<Airplane> bengurion = new ArrayList<>();
		bengurion.add(new Outgoing("el-al", "tel-aviv", "israel", "2021-1-05", "20:15", "Il505", 3, "bengurion"));
		bengurion.add(new Outgoing("el-al", "tel-aviv", "israel", "2020-12-05", "20:20", "Il505", 3, "bengurion"));
		bengurion.add(new Incoming("al-al", "tel-aviv", "israel", "2020-05-05", "20:15", "Il505", 3, "bengurion"));
		bengurion.add(new Incoming("el-al", "tel-aviv", "israel", "2019-10-05", "20:15", "Il505", 3, "bengurion"));
		bengurion = sort(bengurion); // sort by hour
		// System.out.println(show(bengurion));
		SwichCase(s, bengurion);
		System.out.println(show(bengurion));
		sortTheList(s, bengurion);

	}// 30-4-2020 start

	private static void sortTheList(Scanner s, List<Airplane> bengurion) {
		List<Integer> select = new ArrayList<>();
		sortedListIncoming = new ArrayList<>();
		sortedListOutcoming = new ArrayList<>();
		showManu(s, select);
		if (select.get(0) == 1) {// incoming flight
			detailsFromUserToSort(s, select);
		}
		if (select.get(0) == 2) { // out going flight
			detailsFromUserToSort(s, select);
		}
		createSortedList(bengurion, sortedListIncoming, sortedListOutcoming, select, startDate, endDate, company, city,
				country, airport);
	}

	private static void createSortedList(List<Airplane> bengurion, List<Airplane> sortedListIncoming,
			List<Airplane> sortedListOutcoming, List<Integer> select, MyDate startDate, MyDate endDate, String company,
			String city, String country, String airport) {
		int selected = select.get(0);
		for (int i = 0; i < bengurion.size(); i++) {
			switch (selected) {

			case 1:
				if (bengurion.get(i).getDirection() == 1)
					sortedListIncoming.add(bengurion.get(i));

				break;
			case 2:
				if (bengurion.get(i).getDirection() == 2)
					sortedListOutcoming.add(bengurion.get(i));
				break;

			default:
				i = bengurion.size();
				break;
			}
		}
		if (selected == 0)
			showSortedList(bengurion, select, startDate, endDate, company, city, country, airport);
		else if (selected == 1)
			showSortedList(sortedListIncoming, select, startDate, endDate, company, city, country, airport);
		else if (selected == 2)
			showSortedList(sortedListOutcoming, select, startDate, endDate, company, city, country, airport);
		else {
			System.out.println("no flight");
		}
	}

	private static void showSortedList(List<Airplane> sortedList, List<Integer> select, MyDate startDate,
			MyDate endDate, String company, String city, String country, String airport) {
		int dateSelected = select.get(1);
		int companySelected = select.get(2);
		int citySelected = select.get(3);
		int countrySelected = select.get(4);
		int airportSelected = select.get(5);
		List<Airplane> finalSortedListByAllVariables = new ArrayList<>();
		Airplane airplaneToEnter;
		System.out.println("Here are the sort by the Date:"+ startDate+ ", "+endDate+", By company: "+
		company+ ", By city: "+ city +", By country: "+country+ ", By airport: "+airport);
		for (int i = 0; i < sortedList.size(); i++) {
			airplaneToEnter = sortedList.get(i);
			if (dateSelected == 1) {
				if (!(airplaneToEnter.date.daysCount(startDate, airplaneToEnter.date) >= 0)
						|| !(airplaneToEnter.date.daysCount(endDate, airplaneToEnter.date) <= 0)) {
					sortedList.set(i, null);
					continue;
				}

			}

			else if (companySelected == 1) {
				if (!airplaneToEnter.getCompany().equals(company)) {
					sortedList.set(i, null);
					continue;
				}
				
			}

			else if (citySelected == 1) {
				if (!airplaneToEnter.getCity().equals(city)) {
					sortedList.set(i, null);
					continue;
				}
			}
			else if (countrySelected == 1) {
				if (!airplaneToEnter.getCountry().equals(country)) {
					sortedList.set(i, null);
					continue;
				}
			}

			else if (airportSelected == 1) {
				if (!airplaneToEnter.getAirport().equals(airport)) {
					sortedList.set(i, null);
					continue;
				}
			}
			finalSortedListByAllVariables.add(sortedList.get(i));
		}
		System.out.println(finalSortedListByAllVariables.toString());
	}

	private static void detailsFromUserToSort(Scanner s, List<Integer> select) {
		if (select.get(1) == 1) {// sort by date
			System.out.println("please provide me the start date");
			startDate = sortByDate(s);
			System.out.println("please provide me the end date");
			endDate = sortByDate(s);
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
	}

	private static MyDate sortByDate(Scanner s) {
		System.out.println("year:");
		int y = s.nextInt();
		System.out.println("month:");
		int m = s.nextInt();
		System.out.println("day:");
		int d = s.nextInt();
		MyDate date = new MyDate(y, m, d);
		return date;
	}

	private static void showManu(Scanner s, List<Integer> select) {
		System.out.println(
				"please choose from the list which sort would you like to do, you can choose more then just one option");

		// first place in array 1 for incoming , 2 for outgoing
		System.out.println("first select which type of flight would you like to sort 1 for incoming or 2 for outgoing");
		select.add(s.nextInt());

		// second place by date
		System.out.println("for sort by date press 1 , if you dont want to sort by date press 0");
		select.add(s.nextInt());

		// third place for company
		System.out.println("for sort by company press 1 , if you dont want to sort by company press 0");
		select.add(s.nextInt());

		System.out.println("for sort by city press 1 , if you dont want to sort by city press 0"); // for city sort
		select.add(s.nextInt());
		System.out.println("for sort by country press 1 , if you dont want to sort by country press 0"); // for country
																											// sort
		select.add(s.nextInt());
		System.out.println("for sort by airport press 1 , if you dont want to sort by airport press 0"); // for airport
		select.add(s.nextInt());																								// sort
	}

	private static void SwichCase(Scanner s, List<Airplane> bengurion) {
		int select;
		do {
			select = showOption(s);
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
		return new Incoming(com, city, country, date, time, Fn, T, airport);
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
		return new Incoming(com, city, country, date, time, Fn, 3, airport);
	}

	private static int showOption(Scanner s) {
		System.out.println("hello, please enter number for what you would like to do");
		System.out.println("enter 1 for create incoming flight , 2 for for create outgoing flight, and 0 to exit ");
		return s.nextInt();
	}

	// 5-5-2020
	private static void filterByDate(List<Airplane> bengurion, MyDate Start, MyDate end, LocalDate localDate) {
		List<Airplane> filtered = new ArrayList<>();

		for (int i = Start.getDay(); i <= end.getDay(); i++) {
			MyDate date = new MyDate(Start.getYear(), Start.getMonth(), i);
			if (Start.before(date) && end.after(date)) {
				for (int j = 0; j < bengurion.size(); j++) {
					if (bengurion.get(i).getData().equals(date.toString()))
						filtered.add(bengurion.get(i));
				}
			}

		}

	}

	private static String show(List<Airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++)
			sb.append(bengurion2.get(i).toString() + "\n");
		return sb.toString();
	}

	private static String showHtml(List<Airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++) {
			sb.append(bengurion2.get(i).toString());
		}
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
