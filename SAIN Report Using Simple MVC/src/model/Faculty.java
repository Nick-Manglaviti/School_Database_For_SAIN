package model;

public class Faculty extends Person {

	private String rank;
	int officenum;
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getOfficenum() {
		return officenum;
	}
	public void setOfficenum(int officenum) {
		this.officenum = officenum;
	}
	public Faculty(String fName, String lName, int id, String username,
			String password, String phone, String address, String campus,
			String rank, int officenum) {
		super(fName, lName, id, username, password, phone, address, campus);
		this.rank = rank;
		this.officenum = officenum;
	}
	
	
}
