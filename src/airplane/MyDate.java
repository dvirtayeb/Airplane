package airplane;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

public class MyDate extends Date {
	private int day;
	private int month;
	private int year;

/*	public MyDate() {
		day = 1;
		month = 1;
		year = 2020;
	}
*/	
	public MyDate(int year, int month, int day) {
		day = setDay(day);
		setMonth(month);
		setYear(year);
	}

	public MyDate(String s) {
		String[] numbers = s.split("-");
		 setYear(Integer.parseInt(numbers[0]));
		 setMonth(Integer.parseInt(numbers[1]));
		 setDay(Integer.parseInt(numbers[2]));
	}

	public MyDate(Scanner scan) {
		String str = scan.nextLine();
		String[] numbers = str.split("-");
		 setYear(Integer.parseInt(numbers[0]));
		 setMonth(Integer.parseInt(numbers[1]));
		 setDay(Integer.parseInt(numbers[2]));

	}
	
	public int daysCount(MyDate userLimitDate, MyDate m) {
		LocalDate limit = LocalDate.of(userLimitDate.year, userLimitDate.month, userLimitDate.day);
		LocalDate fromList = LocalDate.of(m.year, m.month, m.day);
		int difference = (int) ChronoUnit.DAYS.between(limit, fromList);
		return difference;
	}

	public void saveDate(PrintWriter writer) {
		writer.print(day + ", ");
		writer.print(month + ", ");
		writer.println(year);
	}



	public int getDay() {
		return day;
	}

	public int setDay(int day) {
		if (day > 31 || day < 1) {
			this.day = 1;
		} else if (month == 2) {
			if (day > 28 || day < 1) {
				this.day = 1;
			}

		}
		this.day = day;
		return day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if (month > 12 || month < 1) {
			this.month = 1;
		}
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return year + "-" + month + "-" + day;
	}
}
