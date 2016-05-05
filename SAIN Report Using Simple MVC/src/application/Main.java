package application;
	

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.PersonBag;
import model.Student;
import view.LoginScreen;





public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		LoginScreen window = new LoginScreen(stage);
		Student model = new Student();
		PersonBag personbag = new PersonBag();
		Controller controller = new Controller(personbag, window);
	}


}
