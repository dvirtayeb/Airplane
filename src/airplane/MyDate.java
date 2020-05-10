package airplane;

import java.io.PrintWriter;
import java.util.Scanner;

public class MyDate {
private int Day;
private int Month;
private int Year;
	public MyDate() {
		Day = 1;
		Month = 1;
		Year = 2020;
	}
	public MyDate(String s) {
		String [] numbers=s.split("-");
		this.Year=setYear(Integer.parseInt(numbers[0]));
		this.Month=setMonth(Integer.parseInt(numbers[1]));
		this.Day=setDay(Integer.parseInt(numbers[2]));
	}
	public MyDate(Scanner scan) {
		String str=scan.nextLine();
		String [] numbers=str.split("-");
		this.Year=setYear(Integer.parseInt(numbers[0]));
		this.Month=setMonth(Integer.parseInt(numbers[1]));
		this.Day=setDay(Integer.parseInt(numbers[2]));
		
	}
	public void saveDate(PrintWriter writer) {
		writer.print(Day+", ");
		writer.print(Month+", ");
		writer.println(Year);
	}
	public MyDate(int day, int month, int year) {
		Day = setDay(day);
		Month = setMonth(month);
		Year = setYear(year);
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

	public int setMonth(int month) {
		if (month > 12 || month < 1) {
			this.Month = 1;
			return Month;
		}
		this.Month = month;
		return Month;
	}

	public int getYear() {
		return Year;
	}

	public int setYear(int year) {
		if (year > 2020 || year < 2000) {
			Year = 2020;
			return Year;
		}
		return year;
	}
	public String toString() {
		return  Year + "-" + Month + "-" + Day;
	}
}
