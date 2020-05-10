package airplane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class program {

	public static void main(String[] args) {
		List <airplane> bengurion = new ArrayList<>();
		bengurion.add(new airplane("al-al", "tel-aviv", "2020-10-05", "20:15", "Il505", 3));
		bengurion.add(new airplane("al-al", "tel-aviv", "2020-10-05", "20:20", "Il505", 3));
		bengurion.add(new airplane("al-al", "tel-aviv", "2020-05-05", "20:15", "Il505", 3));
		bengurion.add(new airplane("al-al", "tel-aviv", "2019-10-05", "20:15", "Il505", 3));
		bengurion = sort(bengurion); // sort by hour
		System.out.println(show(bengurion));

	}

	private static String show(List <airplane> bengurion2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bengurion2.size(); i++)
			sb.append(bengurion2.get(i).toString() + "\n");
		return sb.toString();
	}
	
	private static List <airplane> sort(List <airplane> airport) {
		List <String> strings = new ArrayList<>();
		for(int i=0;i<airport.size();i++) {
			strings.add(airport.get(i).getData());
		}
		strings.sort(Comparator.naturalOrder());
		List <airplane> bengurion = new ArrayList<>();
		for(int i=0;i<strings.size();i++) {
			for(int j=0;j<airport.size();j++) {
				if(strings.get(i).equals(airport.get(j).getData())) {
					bengurion.add(airport.get(j));
					break;
				}
			}
		}
		return bengurion;
	}
	
}
