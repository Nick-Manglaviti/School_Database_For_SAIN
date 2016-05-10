package model;

import java.util.List;

public class Student extends Person {

	//Data Fields
	private String program;
	private Degree degree;
	private Major major;
	private List<CoursesTaken> courses;
	private double cumulativeGpa;
	private double majorGPA;
	private double programGPA;
	
	//Getters and Setters
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	
	public List<CoursesTaken> getCourses() {
		return courses;
	}
	public void setCourses(List<CoursesTaken> courses) {
		this.courses = courses;
	}
	public double getCumulativeGpa() {
		return cumulativeGpa;
	}
	public void setCumulativeGpa(double cumulativeGpa) {
		this.cumulativeGpa = cumulativeGpa;
	}
	public double getMajorGPA() {
		return majorGPA;
	}
	public void setMajorGPA(double majorGPA) {
		this.majorGPA = majorGPA;
	}
	public double getProgramGPA() {
		return programGPA;
	}
	public void setProgramGPA(double programGPA) {
		this.programGPA = programGPA;
	}
	
	//Constructors
	public Student(String fName, String lName, int id, String username,
			String password, String phone, String address, String campus,
			String program, Degree degree, Major major, List<CoursesTaken> courses,
			double cumulativeGpa, double majorGPA, double programGPA) {
		super(fName, lName, id, username, password, phone, address, campus);
		this.program = program;
		this.degree = degree;
		this.major = major;
		this.courses = courses;
		this.cumulativeGpa = cumulativeGpa;
		this.majorGPA = majorGPA;
		this.programGPA = programGPA;
	}
	public Student(String fName, String lName, int id, String username,
			String password, String phone, String address, String campus) {
		super(fName, lName, id, username, password, phone, address, campus);
		// Overloaded Constructor
	}
	public Student() {
		// No Argument Constructor
	}
	
	
	public Student clone(Student student) {
		Student newStudent = new Student(
				student.getfName(),
				student.getlName(),
				student.getId(),
				student.getUsername(),
				student.getPassword(),
				student.getPhone(),
				student.getAddress(),
				student.getCampus(),
				student.getProgram(),
				student.getDegree(),
				student.getMajor(),
				student.getCourses(),
				student.getCumulativeGpa(),
				student.getMajorGPA(),
				student.getProgramGPA());
		return newStudent;
		
	}
	@Override
	public String toString() {
		return "Student [program=" + program + ", degree=" + degree
				+ ", major=" + major + ", courses=" + courses
				+ ", cumulativeGpa=" + cumulativeGpa + ", majorGPA=" + majorGPA
				+ ", programGPA=" + programGPA + "]";
	}
	
	
	
}
