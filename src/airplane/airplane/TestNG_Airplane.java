package airplane;

import org.testng.annotations.Test;

public class TestNG_Airplane {
	@Test(priority = 1, description = "this test case will verify login functionality")
	public void loginApplication() {
		System.out.println("Hey");
	}
	@Test(priority = 2)
	public void selectedItems() {
		System.out.println("Selected Items");
	}
	@Test
	public void logOut() {
		System.out.println("Bye");
	}
}
