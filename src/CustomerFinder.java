import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.*;

public class CustomerFinder {

	private static final int earthRadius = 6371;
	
	private ArrayList<Customer> customers;
	private double officeLatitude;
	private double officeLongitude;
	
	public CustomerFinder(double officeLatitude,double officeLongitude) {
		this.officeLatitude = officeLatitude;
		this.officeLongitude = officeLongitude;
		customers = new ArrayList<Customer>();
		
	}
	
	public ArrayList<Customer> getAllCustomers() {
		return customers;
	}

	public void readFile(String fileName) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			Gson gson = new Gson();
			while((currentLine = bufferedReader.readLine())!= null ) {
				customers.add(gson.fromJson(currentLine, Customer.class));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Your specified file is not found.", e);
			
		} catch (IOException e) {
			throw new RuntimeException("An error occured while reading your file.", e);
		} finally {
			try {
				if(fileReader != null) {
					fileReader.close();
				}
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("An error occured while closing reading your file.", e);	
			}
		}
	}
	
	public ArrayList<Customer> findNearbyCustomers(int distance) {
		ArrayList<Customer> nearByCustomers = new ArrayList<Customer>();
		for (int i = 0; i<customers.size(); i++) {
			double d = calculateDistance(customers.get(i).getLatitude(),customers.get(i).getLongitude(),officeLatitude, officeLongitude);
			if (d <= distance) {
				nearByCustomers.add(customers.get(i));
			}
		}
		
		Collections.sort(nearByCustomers);
		return nearByCustomers;
	}

	private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double centralAngle; 
		centralAngle = Math.acos(Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) + 
				Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) 
				* Math.cos(Math.toRadians(Math.abs(longitude1-longitude2))));
		
		return centralAngle * earthRadius;
	}
	
	public static void main(String[] args) throws IOException {
		double latitude = 53.339428;
	    double longitude = -6.257664;
	    int distance = 100;
		CustomerFinder customerFinder = new CustomerFinder(latitude,longitude);
		customerFinder.readFile("customers.txt");
	    ArrayList<Customer> nearByCustomers = new ArrayList<Customer>();
	    nearByCustomers = customerFinder.findNearbyCustomers(distance);
	    for(int i = 0; i<nearByCustomers.size(); i++) {
			System.out.println(nearByCustomers.get(i));
		}
	}
}
