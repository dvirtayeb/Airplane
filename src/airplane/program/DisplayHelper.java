package program;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import airplane.Airplane;
import airplane.Incoming;
import airplane.MyDate;

public class DisplayHelper {
	
	static ArrayList<Airplane> sortedListIncoming;
	static ArrayList<Airplane> sortedListOutcoming;

	static void showResultSearchFlight(List<Airplane> bengurion, String flight, MyDate startDate,
			MyDate endDate, String company, String city, String country, String airport,  Boolean[] days, boolean isHtml, boolean isText) {
		sortedListIncoming = new ArrayList<>();
		sortedListOutcoming = new ArrayList<>();
		insertToSortedList(bengurion, flight, startDate, endDate, company, city, country, airport, days, isHtml);

	}
	
	static void insertToSortedList(List<Airplane> bengurion, String flight, MyDate startDate, MyDate endDate,
			String company, String city, String country, String airport, Boolean[] days, boolean isHtml) {
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
			showSortedList(sortedListIncoming, startDate, endDate, company, city, country, airport, days, isHtml);
		} else if (flight.equals("Arrivals")) {
			showSortedList(sortedListOutcoming, startDate, endDate, company, city, country, airport, days, isHtml);
		} else {
			System.out.println("no flights");
		}

	}

	static void showSortedList(List<Airplane> sortedList, MyDate startDate, MyDate endDate, String company,
			String city, String country, String airport, Boolean[] days , boolean isHtml) {
		String daysIn = "";
		List<Airplane> finalSortedListByAllVariables = new ArrayList<>();
		Airplane airplaneToEnter;
		System.out.println("Here are the sort by the Date:" + startDate + ",Until: " + endDate + ", By company: "
				+ company + ", By city: " + city + ", By country: " + country + ", By airport: " + airport + ", By day: " + daysIn);
		String[] daydStr = new String[7];

		for (int i = 0; i < 7; i++) {
			String day = "";
			boolean printDay = days[i];
			if (i == 0 && printDay) {
				day = "Sunday";
				daydStr[0] = day;
			}
			if (i == 1 && printDay) {
				day = "Monday";
				daydStr[1] = day;
			}
			if (i == 2 && printDay) {
				day = "Tuesday";
				daydStr[2] = day;
			}
			if (i == 3 && printDay) {
				day = "Wednesday";
				daydStr[3] = day;
			}
			if (i == 4 && printDay) {
				day = "Thursday";
				daydStr[4] = day;
			}
			if (i == 5 && printDay) {
				day = "Friday";
				daydStr[5] = day;
			}
			if (i == 6 && printDay) {
				day = "Saturday";
				daydStr[6] = day;
			}
			daysIn = day +" ";
			System.out.print(daysIn);
		}
		if (isHtml)
			System.out.println("<br>");
		System.out.println("--------------------");
		if (isHtml) {
			System.out.println("<br>");
		}
//			else if(isText) {
//			System.out.println("\n");
//		}
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
			
			if(!daysIn.equals("")) {
				int couter = 0;
				for (int j = 0; j < 7; j++) {
					if (daydStr[j] != null && airplaneToEnter.getDay().equals(daydStr[j])) 
						couter++;	
				}
				if(couter!=1) {
					sortedList.set(i, null);
					continue;
				}
			}
			finalSortedListByAllVariables.add(airplaneToEnter);
		}

		if (finalSortedListByAllVariables.size() > 0) {
			System.out.println(finalSortedListByAllVariables.toString());
		} else {
			System.out.println("We didn't found a flight for your search");
		}
	}
	
	static void sortTheList(Scanner s, List<Airplane> bengurion, boolean isHtml) {
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
		insertToSortedListOnConsole(bengurion, select, Program.startDate, Program.endDate, Program.company, Program.city, Program.country, Program.airport, Program.days,
				isHtml);
	}

	static void insertToSortedListOnConsole(List<Airplane> bengurion, List<Integer> select, MyDate startDate,
			MyDate endDate, String company, String city, String country, String airport, Boolean[] day2,
			boolean isHtml) {
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
			showSortedList(bengurion, startDate, endDate, company, city, country, airport, day2, isHtml);
		else if (selected == 1)
			showSortedList(sortedListIncoming, startDate, endDate, company, city, country, airport, day2, isHtml);
		else if (selected == 2)
			showSortedList(sortedListOutcoming, startDate, endDate, company, city, country, airport, day2, isHtml);
		else {
			System.out.println("no flight");
		}
	}

	static void detailsFromUserToSort(Scanner s, List<Integer> select) {
		if (select.get(1) == 1) {// sort by date
			System.out.println("please provide me the start date");
			Program.startDate = getDateFromUser(s);
			System.out.println("please provide me the end date");
			Program.endDate = getDateFromUser(s);
		}

		else if (select.get(1) == 0) {
			Program.startDate = new MyDate(1, 1, 1);
			Program.endDate = new MyDate(1, 1, 1);
		}

		if (select.get(2) == 1) { // sort by company
			System.out.println("please provide me the company name");
			Program.company = s.next();
		}
		if (select.get(3) == 1) { // sort by city
			System.out.println("please provide me the city name");
			Program.city = s.next();
		}
		if (select.get(4) == 1) { // sort by country
			System.out.println("please provide me the country name");
			Program.country = s.next();
		}
		if (select.get(5) == 1) { // sort by airport
			System.out.println("please provide me the airport name");
			Program.airport = s.next();
		}
		if (select.get(6) == 1) { // sort by airport
			System.out.println("please provide me the days you would like to search by");
			Program.days = new Boolean[7];
			Program.days = sortByDays(s, Program.days);
		}
	}

	static Boolean[] sortByDays(Scanner s, Boolean[] days) {
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

	static boolean yesOrNo(Scanner s) {
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

	static MyDate getDateFromUser(Scanner s) {
		System.out.println("year:");
		int y = s.nextInt();
		System.out.println("month:");
		int m = s.nextInt();
		System.out.println("day:");
		int d = s.nextInt();
		MyDate date = new MyDate(y, m, d);
		return date;
	}

	static void sortSelectionByUser(Scanner s, List<Integer> select) {
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

	static int showOptions(Scanner s) {
		System.out.println("hello, please enter number for what you would like to do");
		System.out
				.println("enter 1 for create incoming flight , 2 for for create outgoing flight, and 0 for continue: ");
		return s.nextInt();
	}

	static void createFlight(Scanner s, List<Airplane> bengurion) {
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

	static Airplane createoutgoing(Scanner s) {
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

	static Airplane createIncoming(Scanner s) {
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

	static String show(List<Airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++)
			sb.append(bengurion2.get(i).toString() + "\n");
		return sb.toString();
	}

	static List<Airplane> sort(List<Airplane> airport) {
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
