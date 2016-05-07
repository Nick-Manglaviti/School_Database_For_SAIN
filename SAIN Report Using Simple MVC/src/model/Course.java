package model;

import java.util.Arrays;

public class Course {

	// Data Fields
	private String courseName;
	private String courseCRN;
	private Course[] preReq;
	private double credits;
	private String subject;
	
	// Getters and Setters
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCRN() {
		return courseCRN;
	}
	public void setCourseCRN(String courseCRN) {
		this.courseCRN = courseCRN;
	}
	public Course[] getPreReq() {
		return preReq;
	}
	public void setPreReq(Course[] preReq) {
		this.preReq = preReq;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	// Constructors
	public Course(String courseName, String courseCRN, Course[] preReq,
			double credits, String subject) {
		this.courseName = courseName;
		this.courseCRN = courseCRN;
		this.preReq = preReq;
		this.credits = credits;
		this.subject = subject;
	}
	public Course() {
		// No Argument Constructor
	}
	@Override
	public String toString() {
		return  courseName + ", CRN: " + courseCRN
				+ "PreReqs: " + Arrays.toString(preReq) + ", Credits: "
				+ credits + ", Subject" + subject + "]";
	}
	
	
	
}
