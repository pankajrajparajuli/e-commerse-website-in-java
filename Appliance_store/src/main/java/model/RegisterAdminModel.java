package model;


public class RegisterAdminModel {
	
	private String admin_name;
	private String password;
	private String email;
	private String number;
	
	
	public RegisterAdminModel(String admin_name, String password,String email,String number) {
		super();
		this.admin_name = admin_name;
		this.password = password;
		this.email = email;
		this.number = number;
	}


	public String getAdmin_name() {
		return admin_name;
	}


	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
