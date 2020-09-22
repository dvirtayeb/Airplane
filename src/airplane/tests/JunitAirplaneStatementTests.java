package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.JUnit4;

import airplane.Airplane;
import airplane.Outgoing;

public class JunitAirplaneStatementTests {

	@Test
	public void statementTest1() {
		Airplane flight = createOutgoingFlight();
		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("company: elal");
		expectedResult.append(" destination:  eilat");
		expectedResult.append(" country: israel");
		expectedResult.append(" date: 2021-1-05");
		expectedResult.append(" time to flight: 20:15");
		expectedResult.append(" flight number: Il505");
		expectedResult.append(" terminal: 3");
		expectedResult.append(" airport: bengurion");
		expectedResult.append(" day: sunday");
		assertEquals(expectedResult.toString(), flight.toString());
		
	}

	private Airplane createOutgoingFlight() {
		Outgoing flight = new Outgoing("elal", "eilat", "israel", "2021-1-05", "20:15", "Il505", 3, "bengurion", "sunday");
		return flight;
	}
}
