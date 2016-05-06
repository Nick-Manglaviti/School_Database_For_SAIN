package controller;


import view.LoginButtonEventObject;
import view.LoginButtonListener;
import view.LoginScreen;
import view.MainScreen;
import view.SainReportButtonEventObject;
import view.SainReportButtonListener;
import javafx.stage.Stage;
import model.Faculty;
import model.Person;
import model.PersonBag;
import model.Student;


public class LoginWindowController {

	
	public LoginWindowController(PersonBag personbag, LoginScreen loginWindow, Person personModel) {
		    // Login Window Button Events
			loginWindow.setLoginButtonListener(new LoginButtonListener() {
			@Override
			public void loginButtonClicked(LoginButtonEventObject ev) {	
			Person p = (Person)personbag.searchForPerson(ev.getUser(), ev.getPassword()); 
			
			if(p == null) {
				String result = new String("Try again..");
				loginWindow.setUserField(result);
			} else {
				loginWindow.close();
				MainScreen main = new MainScreen();
				MainWindowController mainController = new MainWindowController(personbag, main, p);
				}
			}
		    });
	}
}