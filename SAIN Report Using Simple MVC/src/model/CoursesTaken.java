package model;





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
	public CoursesTaken(String courseName, String courseNum, String coursetype,
			double credits, String subject, String grade) {
		super(courseName, courseNum, coursetype, credits, subject);
		this.grade = grade;
	}
	public CoursesTaken() {
		// No Argument Constructor
	}
	
	public String toStringForRequired() {
		return "Subj: " + getSubject() + " " + getCourseNum() + "     Grade: "
				+ grade + "  Area Req: " + getCoursetype();
	}
	
	public String toStringForOther() {
		return "Subj: " + getSubject() + " " + getCourseNum() + "     Grade: "
				+ grade;
	}
	
	public String toStringForFailed() {
		return "Subj: " + getSubject() + " " + getCourseNum() + "     Grade: "
				+ grade;
	}
	
	
	
	
	
	
	
}
