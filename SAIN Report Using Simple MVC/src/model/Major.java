package model;

public class Major {

	private String name;
	private Course[] courses;
	private double minGPA;
	private double totalCredit;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Course[] getCourses() {
		return courses;
	}
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	public double getMinGPA() {
		return minGPA;
	}
	public void setMinGPA(double minGPA) {
		this.minGPA = minGPA;
	}
	public double getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}
	
	// Constructors
	public Major(String name, Course[] courses, double minGPA, double totalCredit) {
		this.name = name;
		this.courses = courses;
		this.minGPA = minGPA;
		this.totalCredit = totalCredit;
	}
	public Major() {
		// No Argument Constructor
	}
	
	
}
