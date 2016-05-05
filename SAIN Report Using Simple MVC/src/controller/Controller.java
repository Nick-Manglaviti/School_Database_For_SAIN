package controller;


import view.LoginButtonEventObject;
import view.LoginButtonListener;
import view.LoginScreen;
import javafx.stage.Stage;
import model.Person;
import model.PersonBag;
import model.Student;


public class Controller {


	
	public Controller(PersonBag personbag, LoginScreen window) {
		window.setLoginButtonListener(new LoginButtonListener() {

		
			
			@Override
			public void loginButtonClicked(LoginButtonEventObject ev) {
			Person p = (Person)personbag.searchForPerson(ev.getUser(), ev.getPassword()); 
			if(p == null) {
				String result = new String("Try again..");
				window.setUserField(result);
			} else {
				String result = new String("Success!");
				window.setUserField(result);
			}
				
			}

			
		});
	}
}
