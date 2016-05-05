package model;

public class Person {
	
	//Data Fields
	private String fName;
	private String lName;
	private int id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private String campus;
	
	// Getters and Setters
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	// Constructors
	public Person(String fName, String lName, int id, String username,
			String password, String phone, String address, String campus) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.campus = campus;
	}
	
	public Person() {
		// No Argument Constructor
	}
	
	
}
