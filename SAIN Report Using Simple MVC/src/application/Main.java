package application;
	

import java.util.ArrayList;
import java.util.List;

import controller.LoginWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Course;
import model.CoursesTaken;
import model.Degree;
import model.Major;
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
		Person personModel = new Person();
		PersonBag personbag = new PersonBag();
		
		CoursesTaken mycourse1 = new CoursesTaken("Advanced Programming in Java", "246", null, 4.0, "CST", "A");
		CoursesTaken mycourse2 = new CoursesTaken("Physics I", "130", null, 4.0, "PHY", "D+");
		
		Degree degree = new Degree("AS", null, 30); 
		
		Major major = new Major("ComputerScience", null, 2.0, 65.5); 
		
		List<CoursesTaken> coursesArray = new ArrayList();
		coursesArray.add(mycourse1);
		coursesArray.add(mycourse2);
		
		Student st = new Student("Nick", "Manglaviti", 12345, "Mangn96",
				"123", "641-283-8030", "10 Juniper Lane", "Ammerman",
				"Competer Science A.S", degree, major, coursesArray,
				3.5, 4.0, 4.0);
		
		personbag.addPerson(st);
		
		LoginWindowController controller = new LoginWindowController(personbag, loginWindow, personModel);
	}


}
