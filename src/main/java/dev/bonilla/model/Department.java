package dev.bonilla.model;

public class Department {

	private int did;
	private String name;
	private int dhead;

	public Department() {
		super();
	}
	public Department(String dname) {
		super();
		this.name = dname;
	}
	public Department(int id, String dname, int dhead) {
		super();
		this.did = id;
		this.name = dname;
		this.dhead= dhead;
	}
	

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getD_head() {
		return dhead;
	}
	public void setD_head(int dhead) {
		this.dhead = dhead;
	}
	@Override
	public String toString() {
		return "Department [did=" + did + ", name=" + name + ", d_head=" + dhead + "]";
	}

}
