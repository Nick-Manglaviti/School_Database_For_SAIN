package view;

import java.util.EventObject;

public class LoginButtonEventObject extends EventObject {

	private String user;
	private String password;
	
	public LoginButtonEventObject(Object source) {
		super(source);
	}

	public LoginButtonEventObject(Object source, String user, String password) {
		super(source);
		this.user = user;
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
}
