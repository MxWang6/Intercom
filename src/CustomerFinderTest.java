import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CustomerFinderTest {

	@Test
	public void testAllCostumersInformatinCanLoadSuccessfully() {
		double latitude = 53.339428;
	    double longitude = -6.257664;
		CustomerFinder visitors = new CustomerFinder(latitude,longitude);
		visitors.readFile("customers_test.txt");

		ArrayList<Customer> allCustomers = visitors.getAllCustomers();
		assertEquals(3,allCustomers.size());
		
		Customer customer1 = allCustomers.get(0);
	    assertEquals(12,customer1.getUserId());
	    assertEquals("Christina McArdle",customer1.getName());
	    assertEquals(52.986375,customer1.getLatitude());
	    assertEquals(-6.043701,customer1.getLongitude());
	    
	    Customer customer2 = allCustomers.get(1);
	    assertEquals(1,customer2.getUserId());
	    assertEquals("Alice Cahill",customer2.getName());
	    assertEquals(51.92893,customer2.getLatitude());
	    assertEquals(-10.27699,customer2.getLongitude());
	    
	    Customer customer3 = allCustomers.get(2);
	    assertEquals(2,customer3.getUserId());
	    assertEquals("Ian McArdle",customer3.getName());
	    assertEquals(51.8856167,customer3.getLatitude());
	    assertEquals(-10.4240951,customer3.getLongitude());
	}
	
	@Test
	public void testfindNearbyCustomers() {
		double latitude = 53.339428;
		double longitude = -6.257664;
		int distance = 100;
		CustomerFinder customerFinder = new CustomerFinder(latitude,longitude);
		customerFinder.readFile("customers_test.txt");
		
		ArrayList<Customer> nearByCustomers = customerFinder.findNearbyCustomers(distance);
        assertEquals(1,nearByCustomers.size());
		
		Customer customer1 = nearByCustomers.get(0);
	    assertEquals(12,customer1.getUserId());
	    assertEquals("Christina McArdle",customer1.getName());
	    assertEquals(52.986375,customer1.getLatitude());
	    assertEquals(-6.043701,customer1.getLongitude());
	}

}
