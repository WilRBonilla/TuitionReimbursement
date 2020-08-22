package dev.bonilla.model;

public class Upload {
	private int rid;
	private int uid;
	private int eid;
	private String status;
	private int threshold; // This might need to be another datatype
	private byte[] input; // This might need to be another data type. 
	
	public Upload() {
		super();
		
	}
	public Upload( byte[] inp) {
		super();
		this.input = inp;
	}
	public Upload( int thresh, byte[] inp) {
		super();
		this.threshold = thresh;
		this.input = inp;
	}
	public Upload(String stat, int thresh, byte[] inp) {
		super();
		this.status = stat;
		this.threshold = thresh;
		this.input = inp;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public byte[] getInput() {
		return input;
	}

	public void setInput(byte[] input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "Upload [rid=" + rid + ", uid=" + uid + ", eid=" + eid + ", status=" + status + "]";
	}
	
	

}
