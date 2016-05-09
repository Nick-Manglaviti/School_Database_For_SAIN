package application;
	

import java.util.ArrayList;
import java.util.List;

import controller.LoginWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Administrator;
import model.Course;
import model.CoursesTaken;
import model.Degree;
import model.DegreeBag;
import model.Faculty;
import model.Major;
import model.MajorBag;
import model.Person;
import model.PersonBag;
import model.Student;
import view.LoginScreen;
import view.MainScreen;






public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		LoginScreen loginWindow = new LoginScreen();
		PersonBag personbag = new PersonBag();
		
		CoursesTaken mycourse1 = new CoursesTaken("Data Structures", "246", "Computer Science", 4.0, "CST", "A");
		CoursesTaken mycourse2 = new CoursesTaken("Physics I", "130", "Laboratory Science", 4.0, "PHY", "B");
		CoursesTaken mycourse3 = new CoursesTaken("Calculus 2", "142", "Math Elective", 4.0, "MAT", "B");
		CoursesTaken mycourse4 = new CoursesTaken("Engineering", "101", "Other", 4.0, "ENG", "A");
		CoursesTaken mycourse5 = new CoursesTaken("Modern Biology", "150", "Laboratory Science", 4.0, "BIO", "W");
		CoursesTaken mycourse6 = new CoursesTaken("Advanced Programming in Java", "242", "Computer Science", 4.0, "CST", "IP");
		CoursesTaken mycourse7 = new CoursesTaken("Discrete Math", "205", "Math Elective", 4.0, "MAT", "IP");
		CoursesTaken mycourse8 = new CoursesTaken("Holocaust", "131", "Humanities", 4.0, "HUM", "A");
		List<CoursesTaken> coursesArray = new ArrayList<CoursesTaken>();
		coursesArray.add(mycourse1);
		coursesArray.add(mycourse2);
		coursesArray.add(mycourse3);
		coursesArray.add(mycourse4);
		coursesArray.add(mycourse5);
		coursesArray.add(mycourse6);
		coursesArray.add(mycourse7);
		coursesArray.add(mycourse8);

		
		Course course1 = new Course("Data Structures", "246", "Computer Science", 4.0, "CST");
		Course course2 = new Course("Physics I", "130", "Laboratory Science", 4.0, "PHY");
		Course course3 = new Course("Calculus 2", "142", "Math Elective", 4.0, "MAT");
		Course course4 = new Course("Engineering", "101", "Other", 4.0, "EGN");
		Course course5 = new Course("Modern Biology", "150", "Laboratory Science", 4.0, "BIO");
		Course course6 = new Course("Advanced Programming in Java", "242", "Computer Science", 4.0, "CST");
		Course course7 = new Course("Discrete Math", "205", "Math Elective", 4.0, "MAT");
		Course course8 = new Course("Psychology of the Human Brain", "226", "Psychology", 4.0, "PSY");
		Course course9 = new Course("The ID and Superego", "158", "Psychology", 3.0, "PSY");
		
		
		List<Course> courses = new ArrayList<Course>();
		courses.add(course1);
		courses.add(course3);
		courses.add(course6);
		courses.add(course7);
		List<String> types = new ArrayList<String>();
		types.add("Laboratory Science");
		types.add("Math Elective");
		types.add("Humanities");
		types.add("Computer Science");
		types.add("PED");
		Major major1 = new Major("ComputerScience", courses, types, 2.0, 65.5); // Create Computer Science Major
		
		
		List<Course> courses2 = new ArrayList<Course>();
		courses2.add(course3);
		courses2.add(course4);
		courses2.add(course5);
		courses2.add(course7);
		List<String> types2 = new ArrayList<String>();
		types2.add("Laboratory Science");
		types2.add("Math Elective");
		types2.add("Computer Science");
		types2.add("PED");
		Major major2 = new Major("Engineering", courses2, types2, 2.0, 65.5); // Create Engineering Major
		
		List<Course> courses3 = new ArrayList<Course>();
		courses3.add(course8);
		List<String> types3 = new ArrayList<String>();
		types3.add("Psychology Elective");
		types3.add("Humanities Elective");
		types3.add("English Elective");
		types3.add("PED");
		Major major3 = new Major("Psychology", courses3, types3, 2.0, 61);  // Create Psychology Major
		
		List<Major> asMajors = new ArrayList<Major>(); // Make the AS Degree Majors
		asMajors.add(major1);
		asMajors.add(major2);
		
		List<Major> aaMajors = new ArrayList<Major>(); // Make the AA Degree Majors
		aaMajors.add(major3);
	
		
		Degree degreeAS = new Degree("AS", asMajors, 30);
		Degree degreeAA = new Degree("AA", aaMajors, 30);
		DegreeBag allDegrees = new DegreeBag();
		allDegrees.addDegree(degreeAS);
		
		MajorBag allMajors = new MajorBag();
		allMajors.getMajors().add(major1);
		allMajors.getMajors().add(major2);
		allMajors.getMajors().add(major3);
		
		
		
		// USE THESE TO ENTER THE DATABASE!
		// Test People
		Student st = new Student("Nick", "Manglaviti", 12345, "Mangn96",
				"123", "641-283-8030", "10 Juniper Lane", "Ammerman",
				"Competer Science A.S", degreeAS, major1, coursesArray,
				3.5, 4.0, 4.0);
		
		Faculty fac = new Faculty("Ben" , "Chen", 18753, "Lion Tamer",
				"456", "1-800-Call-Me", "234 Bellows Lane", "Ammerman",
				"Assistant Chair of Computer Science", 207);
		
		Administrator admin = new Administrator("John" , "Shneider", 875309, "Dean",
				"000", "1631-353-4636", "13 Apple Court", "Ammerman",
				"Dean of Changing Grades", 213);
		
		
		personbag.addPerson(st);
		personbag.addPerson(fac);
		personbag.addPerson(admin);
		
		LoginWindowController controller = new LoginWindowController(personbag, allMajors, allDegrees, loginWindow);
	}


}
