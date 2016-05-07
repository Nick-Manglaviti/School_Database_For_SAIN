package model;

import java.util.Arrays;

public class CoursesTaken extends Course {

	// Data Fields
	private String grade;
	
	// Getters and Setters
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
	// Constructors
	public CoursesTaken(String courseName, String courseCRN, Course[] preReq,
			double credits, String subject, String grade) {
		super(courseName, courseCRN, preReq, credits, subject);
		this.grade = grade;
	}
	public CoursesTaken() {
		// No Argument Constructor
	}
	@Override
	public String toString() {
		return super.toString() + " " + grade;
	}
	
	
	
	
	
	
	
}
