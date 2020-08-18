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
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<Airplane> bengurion = new ArrayList<>();
		bengurion.add(new Outgoing("al-al", "tel-aviv", "israel", "2021-1-05", "20:15", "Il505", 3));
		bengurion.add(new Outgoing("al-al", "tel-aviv", "israel", "2020-12-05", "20:20", "Il505", 3));
		bengurion.add(new Incoming("al-al", "tel-aviv", "israel", "2020-05-05", "20:15", "Il505", 3));
		bengurion.add(new Incoming("al-al", "tel-aviv", "israel", "2019-10-05", "20:15", "Il505", 3));
		bengurion = sort(bengurion); // sort by hour
		// System.out.println(show(bengurion));
		SwichCase(s, bengurion);
		System.out.println(show(bengurion));
		sortTheList(s, bengurion);

	}// 30-4-2020 start

	private static void sortTheList(Scanner s, List<Airplane> bengurion) {
		List<Integer> select = new ArrayList<>();
		List<Airplane> sortedListIncoming = new ArrayList<>();
		List<Airplane> sortedListOutcoming = new ArrayList<>();
		showManu(s, select);
//		String startDate = "", endDate = "", company = "", city = "", country = "", airport = "";
		if (select.get(0) == 1) {// incoming flight
			detailsFromUserToSort(s, select);
//			detailsFromUserToSort(s, select, , endDate, company, city, country, airport);
		}
		if (select.get(0) == 2) { // out going flight
			detailsFromUserToSort(s, select);
//			detailsFromUserToSort(s, select, startDate, endDate, company, city, country, airport);
		}
		showSortdedList(bengurion, sortedListIncoming,sortedListOutcoming, select, startDate, endDate, company, city, country, airport);
	}

	private static void showSortdedList(List<Airplane> bengurion, List<Airplane> sortedListIncoming, List<Airplane> sortedListOutcoming, List<Integer> select, MyDate startDate, MyDate endDate,
			String company, String city, String country, String airport) {
		for (int i = 0; i < bengurion.size(); i++) {
			switch (select.get(0)) {
			case 1:
				if(bengurion.get(i).date.daysCount(startDate, bengurion.get(i).date)>= 0)
					if(bengurion.get(i).date.daysCount(endDate, bengurion.get(i).date)<= 0)
						sortedListIncoming.add(bengurion.get(i));
				
				break;
			case 2:
				if(bengurion.get(i).date.daysCount(startDate, bengurion.get(i).date)>= 0)
					if(bengurion.get(i).date.daysCount(endDate, bengurion.get(i).date)<= 0)
						sortedListOutcoming.add(bengurion.get(i));
				break;
				
			default:
				
				break;
			}	
		}
		if(select.get(0) == 1)
			System.out.println(show(sortedListIncoming));
		else if(select.get(0) == 2)
			System.out.println(show(sortedListOutcoming));
		else {
			System.out.println("no flight");
		}
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
		if (select.get(4) == 1) { // sort by airport
			System.out.println("please provide me the airport name");
			airport = s.next();
		}
	}

/*	private static void detailsFromUserToSort(Scanner s, List<Integer> select, String startDate, String endDate,
			String company, String city, String country, String airport) {
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
		if (select.get(4) == 1) { // sort by airport
			System.out.println("please provide me the airport name");
			airport = s.next();
		}
	}
*/
	private static MyDate sortByDate(Scanner s) {
		System.out.println("year:");
		int y = s.nextInt();
		System.out.println("month:");
		int m = s.nextInt();
		System.out.println("day:");
		int d = s.nextInt();
		MyDate date = new MyDate(y,m,d);
		return date;
	}

	private static void showManu(Scanner s, List<Integer> select) {
		int choose;
		System.out.println(
				"please choose from the list which sort would you like to do, you can choose more then just one option");

		// first place in array 1 for incoming , 2 for outgoing
		System.out.println("first select which type of flight would you like to sort 1 for incoming or 2 for outgoing");
		select.add(s.nextInt());

		// second place by date
		System.out.println("for sort by date press 1 , if you dont want to sort by date press 0");
		select.add(s.nextInt());

		// System.out.println("for sort by time press 1 , if you dont want to sort by
		// time press 0");
		// select.add(s.nextInt());

		// third place for company
		System.out.println("for sort by company press 1 , if you dont want to sort by company press 0");
		select.add(s.nextInt());

		System.out.println("for sort by city press 1 , if you dont want to sort by city press 0"); // for city sort
		select.add(s.nextInt());
		System.out.println("for sort by country press 1 , if you dont want to sort by country press 0"); // for country
																											// sort
		select.add(s.nextInt());
		System.out.println("for sort by airport press 1 , if you dont want to sort by airport press 0"); // for airport
																											// sort
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
		return new Incoming(com, city, country, date, time, Fn, T);
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
		return new Incoming(com, city, country, date, time, Fn, 3);
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
