package dev.bonilla.model;

public class UserLogin {
	private int uid;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String address;
	private Long phone;
	private int did;
	private int available;
	private int sid;
	

	public UserLogin() {
		super();
		sid = 1;
	}
	
	public UserLogin(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	
	
	// Providing everything, except ID.
	public UserLogin(String mail, String pass, String firstn, String lastn, String addres, Long number, int did, int avail, int rank) {
		super();
		this.email = mail;
		this.password = pass;
		this.firstname = firstn;
		this.lastname = lastn;
		this.address = addres;
		this.phone = number;
		this.did = did;
		this.available = avail;
		this.sid = rank;
	}
	
	// Providing Everything
	public UserLogin(int id, String mail, String pass, String firstn, String lastn, String addres, Long number, int did, int avail, int rank) {
		super();
		this.uid = id;
		this.email = mail;
		this.password = pass;
		this.firstname = firstn;
		this.lastname = lastn;
		this.address = addres;
		this.phone = number;
		this.did = did;
		this.available = avail;
		this.sid = rank;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Long getPhone() {
		return phone;
	}


	public void setPhone(Long phone) {
		this.phone = phone;
	}


	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	public int getAvailable() {
		return available;
	}


	public void setAvailable(int available) {
		this.available = available;
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int rank) {
		this.sid = rank;
	}


	@Override
	public String toString() {
		return "userlogin [uid=" + uid + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", did=" + did + ", supervisorID=" + sid + "]";
	}
	
	

}
