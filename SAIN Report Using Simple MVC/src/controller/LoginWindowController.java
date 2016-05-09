package controller;


import view.LoginButtonEventObject;
import view.LoginButtonListener;
import view.LoginScreen;
import view.MainScreen;
import view.SainReportButtonEventObject;
import view.SainReportButtonListener;
import javafx.stage.Stage;
import model.Administrator;
import model.Faculty;
import model.Person;
import model.PersonBag;
import model.Student;


public class LoginWindowController {

	
	public LoginWindowController(PersonBag personbag, LoginScreen loginWindow) {
		    // Login Window Button Events
			loginWindow.setLoginButtonListener(new LoginButtonListener() {
			@Override
			public void loginButtonClicked(LoginButtonEventObject ev) {	
			Person person = personbag.searchForPerson(ev.getUser(), ev.getPassword()); 
			if(person == null) {
				String result = new String("Try again..");
				loginWindow.setUserField(result);
			} else if (person instanceof Student){
				loginWindow.close();
				MainScreen main = new MainScreen();
				MainWindowController mainController = new MainWindowController(personbag, main, (Student)person);
				
				} else if (person instanceof Administrator){ // this way the admin wont be confused as a regular Faculty
					loginWindow.close();
					MainScreen main = new MainScreen();
					MainWindowController mainController = new MainWindowController(personbag, main, (Administrator)person, new Student());
					
					} else if (person instanceof Faculty){
						loginWindow.close();
						MainScreen main = new MainScreen();
						MainWindowController mainController = new MainWindowController(personbag, main, (Faculty)person, new Student());
						}
			}
		    });
	}
}
