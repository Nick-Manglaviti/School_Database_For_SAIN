package model;

public class CoursesTaken extends Course {

	// Data Fields
	private String grade;
	private Term term;
	
	// Getters and Setters
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	
	// Constructors
	public CoursesTaken(String courseName, String courseCRN, Course[] preReq,
			double credits, String subject, String grade, Term term) {
		super(courseName, courseCRN, preReq, credits, subject);
		this.grade = grade;
		this.term = term;
	}
	public CoursesTaken() {
		// No Argument Constructor
	}
	
	
	
}
