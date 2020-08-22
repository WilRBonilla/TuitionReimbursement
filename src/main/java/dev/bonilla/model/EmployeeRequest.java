package dev.bonilla.model;

public class EmployeeRequest extends Request {
	

	private String firstname;
	private String lastname;
	private int avail;
	private int department;
	
	public EmployeeRequest(){
		super();
		
	}

	public EmployeeRequest(int uid, String firstname, String lastname, int avail, int department) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.avail = avail;
		this.department = department;
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

	public int getAvail() {
		return avail;
	}

	public void setAvail(int avail) {
		this.avail = avail;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [firstname=" + firstname + ", lastname=" + lastname + ", avail="
				+ avail + ", department=" + department + "]";
	}
	
	
	
	
	
}
