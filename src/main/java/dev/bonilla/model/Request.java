package dev.bonilla.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Request {

	private int rid;
	private int uid;
	private int eid;
	private int value;
	private Timestamp time;
	private int status;
	private int flag;
	private String notes;
	
	public Request() {
		super();
	}
	
	public Request(int rid, int uid, int eid, int value, Timestamp time, int status, int flag, String notes) {
		super();
		this.rid = rid;
		this.uid = uid;
		this.eid = eid;
		this.value = value;
		this.time = time;
		this.status = status;
		this.flag = flag;
		if( notes == null) {
			this.notes = "";
		} else
			this.notes = notes;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
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
	
	
	
	/**
	 * This is a OSQL timestamp not necessary.
	 * @return
	 */
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Request [rid=" + rid + ", uid=" + uid + ", eid=" + eid + ", value=" + value + "]";
	}

	
}
