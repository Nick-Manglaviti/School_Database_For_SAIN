package model;

import java.util.List;

public class Major {

	private String name;
	private List<Course> coursesList;
	private List<String> typesArray;
	private double minGPA;
	private double totalCredit;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public List<String> getTypesArray() {
		return typesArray;
	}

	public void setTypesArray(List<String> subArray) {
		this.typesArray = subArray;
	}

	public void setName(String name) {
		this.name = name;
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
	public Major(String name, List<Course> coursesList, List<String> typesArray,
			double minGPA, double totalCredit) {
		super();
		this.name = name;
		this.coursesList = coursesList;
		this.typesArray = typesArray;
		this.minGPA = minGPA;
		this.totalCredit = totalCredit;
	}
	public Major() {
		// No Argument Constructor
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
}
