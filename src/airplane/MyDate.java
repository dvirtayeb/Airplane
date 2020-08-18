package airplane;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class MyDate extends Date {
	private int Day;
	private int Month;
	private int Year;

	public MyDate() {
		Day = 1;
		Month = 1;
		Year = 2020;
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

	public void saveDate(PrintWriter writer) {
		writer.print(Day + ", ");
		writer.print(Month + ", ");
		writer.println(Year);
	}

	public MyDate(int year, int month, int day) {
		Day = setDay(day);
		setMonth(month);
		setYear(year);
	}

	public int getDay() {
		return Day;
	}

	public int setDay(int day) {
		if (day > 31 || day < 1) {
			this.Day = 1;
		} else if (Month == 2) {
			if (day > 28 || day < 1) {
				this.Day = 1;
			}

		}
		this.Day = day;
		return Day;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		if (month > 12 || month < 1) {
			this.Month = 1;
		}
		this.Month = month;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		if (year > 2020 || year < 2000) {
			Year = 2020;
		}
		this.Year = year;
	}

	public String toString() {
		return Year + "-" + Month + "-" + Day;
	}
}
