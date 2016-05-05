package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

	private BorderPane root;
	private Label userLabel;
	private TextField userField;
	private Label passwordLabel;
	private TextField passwordField;
	private Button loginButton;
	private VBox center;
	private LoginButtonListener loginButtonListener;
	
	public LoginScreen(Stage stage) {
		root = new BorderPane();
		stage.setTitle("Login");
		
		userLabel = new Label("Username: ");
		userField = new TextField();
		passwordLabel = new Label("Password");
		passwordField = new TextField();
		userField.setMaxWidth(150);
		passwordField.setMaxWidth(150);
		loginButton = new Button("Login");
		
		center = new VBox(10);
		center.setPadding(new Insets(10, 10, 10, 10));
		center.setAlignment(Pos.BASELINE_CENTER);
		center.getChildren().addAll(userLabel, userField, passwordLabel, passwordField, loginButton);
		
		root.setCenter(center);
		
		loginButton.setOnAction(e -> {
			String user = userField.getText();
			String password = passwordField.getText();
			LoginButtonEventObject ev = 
					new LoginButtonEventObject(this, user, password);
			if (loginButtonListener != null) {
				loginButtonListener.loginButtonClicked(ev);
			}
		});
		
		stage.setScene(new Scene(root, 600, 200));
		stage.show();
	}
	public void setLoginButtonListener(LoginButtonListener okButtonListener){
		this.loginButtonListener = okButtonListener;
	}
	
	public void setUserField(String name) {
		userField.clear();
		userField.setText(name);
	}
	
}


