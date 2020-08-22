package dev.bonilla.model;

import java.sql.Date;

public class Award {

	private int aid;
	private int uid;
	private int eid;
	private int value;
	private Date date;
	private String notes; // InputStream?
	
	public Award() {
		super();
	}
	
	public Award(int aid, int uid, int eid, int value, Date date, String notes) {
		super();
		this.aid = aid;
		this.uid = uid;
		this.eid = eid;
		this.value = value;
		this.date = date;
		this.notes = notes;
	}

	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "Award [aid=" + aid + ", uid=" + uid + ", eid=" + eid + ", value=" + value + ", date=" + date + "]";
	}
	
	

}
