package model;

public class RegisterUserModel {
	
	private String user_name;
	private String user_password;
	private String user_email;
	private String user_number;
	
	
	public RegisterUserModel(String user_name, String user_password,String user_email,String user_number) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_number = user_number;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_password() {
		return user_password;
	}


	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_number() {
		return user_number;
	}


	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
	
	

}
