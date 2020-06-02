package airplane;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class program {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<airplane> bengurion = new ArrayList<>();
		bengurion.add(new Outgoing("al-al", "tel-aviv", "2020-10-05", "20:15", "Il505", 3));
		bengurion.add(new Outgoing("al-al", "tel-aviv", "2020-10-05", "20:20", "Il505", 3));
		bengurion.add(new Incoming("al-al", "tel-aviv", "2020-05-05", "20:15", "Il505", 3));
		bengurion.add(new Incoming("al-al", "tel-aviv", "2019-10-05", "20:15", "Il505", 3));
		bengurion = sort(bengurion); // sort by hour
	//	System.out.println(show(bengurion));
		SwichCase(s, bengurion);
		System.out.println(show(bengurion));
		sortTheList(s,bengurion);
	}// 30-4-2020 start

	private static void sortTheList(Scanner s, List<airplane> bengurion) {
		List <Integer> select = new ArrayList<>();
		showManu(s , select);
		if(select.get(0)==1) {// incoming fligt
			if(select.get(1)==1) // sort by date
		
		
		
		
		
		
		
		
		}
	}

	private static void showManu(Scanner s , List <Integer> select) {
		int choose;
		System.out.println("please choose from the list which sort would you like to do, you can choose more then just one option");
		System.out.println("first slecet which type pf flight would you like to sort 1 for incoming or 2 for outgoing");
		select.add(s.nextInt());
		System.out.println("for sort by date press 1 , if you dont want to sort by date press 0");
		select.add(s.nextInt());
		System.out.println("for sort by time press 1 , if you dont want to sort by time press 0");
		select.add(s.nextInt());
		System.out.println("for sort by company press 1 , if you dont want to sort by company press 0");
		select.add(s.nextInt());
		System.out.println("for sort by city press 1 , if you dont want to sort by city press 0");
		select.add(s.nextInt());
		
	}

	private static void SwichCase(Scanner s, List<airplane> bengurion ) {
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

	private static airplane createoutgoing(Scanner s) {
		System.out.println("plsese enter a company");
		String com = s.next();
		System.out.println("plsese enter a city which the flight fly to");
		String city = s.next();
		System.out.println("plsese enter a date by this order yyyy-mm-dd");
		String date = s.next();
		System.out.println("plsese enter a time to flight by this order hh:mm");
		String time = s.next();
		System.out.println("please enter the number of the flight");
		String Fn = s.next();
		System.out.println("please enter a terminal");
		int T = s.nextInt();
		return new Incoming(com, city, date, time, Fn, T);
	}

	private static airplane createIncoming(Scanner s) {
		System.out.println("plsese enter a company");
		String com = s.next();
		System.out.println("plsese enter a city where the flight come from");
		String city = s.next();
		System.out.println("plsese enter a date by this order yyyy-mm-dd");
		String date = s.next();
		System.out.println("plsese enter a time land by this order hh:mm");
		String time = s.next();
		System.out.println("please enter the number of the flight");
		String Fn = s.next();
		return new Incoming(com, city, date, time, Fn, 3);
	}

	private static int showOption(Scanner s) {
		System.out.println("hello, please enter number for what you would like to do");
		System.out.println("enter 1 for create incoming flight , 2 for for create outgoing flight, and 0 to exit ");
		return s.nextInt();
	}

	// 5-5-2020
	private static void filterByDate(List<airplane> bengurion, MyDate Start, MyDate end, LocalDate localDate) {
		List<airplane> filered = new ArrayList<>();

		for (int i = Start.getDay(); i <= end.getDay(); i++) {
			MyDate date = new MyDate(Start.getYear(), Start.getMonth(), i);
			if (Start.before(date) && end.after(date)) {
				for (int j = 0; j < bengurion.size(); j++) {
					if (bengurion.get(i).getData().equals(date))
						filered.add(bengurion.get(i));
				}
			}

		}

	}

	private static String show(List<airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++)
			sb.append(bengurion2.get(i).toString() + "\n");
		return sb.toString();
	}

	private static List<airplane> sort(List<airplane> airport) {
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < airport.size(); i++) {
			strings.add(airport.get(i).getData());
		}
		strings.sort(Comparator.naturalOrder());
		List<airplane> bengurion = new ArrayList<>();
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
