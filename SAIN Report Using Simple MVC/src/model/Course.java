package model;



public class Course {

	// Data Fields
	private String courseName;
	private String courseNum;
	private String coursetype;
	private double credits;
	private String subject;
	
	// Getters and Setters
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseNum() {
		return courseNum;
	}
	
	public String getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
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
	public Course(String courseName, String courseNum, String coursetype,
			double credits, String subject) {
		this.courseName = courseName;
		this.courseNum = courseNum;
		this.coursetype = coursetype;
		this.credits = credits;
		this.subject = subject;
	}
	public Course() {
		// No Argument Constructor
	}
	
	public String toStringForCurrent() {
		return  "Subj: " + subject + " " + getCourseNum() + ", Counts: ";
	}
	
	public String toStringForNeeded() {
		return "Subj: " + getSubject() + " " + getCourseNum() + "     "
				+ courseName + "  Area Req: " + getCoursetype() + "     Credits: " + credits;
	}
	
	
}
