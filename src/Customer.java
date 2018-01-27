
public class Customer implements Comparable<Customer> {
	
	private int user_id ;
	private String name;
	private double latitude;
	private double longitude;
	
	public int getUserId() {
		return user_id;
	}
	
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String toString() {
		return "User ID: "+ user_id + " Name: " + name ;
	}
	
	@Override
	public int compareTo(Customer o) {
        int userId = o.getUserId();
		return this.user_id - userId;
	}

}
