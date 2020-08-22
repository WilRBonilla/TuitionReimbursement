package dev.bonilla.DAO;

import dev.bonilla.model.Upload;

public interface UploadDAO {
	
	public Upload getUpload(int uid, int rid, int eid);
	public boolean createNewUpload(String stat, int thresh, byte[] inp);
	public boolean deleteUpload(int uid, int rid, int eid);
	public boolean updateUpload(Upload u);
	/*
	 * Potentially: add methods to get all uploads based on
	 * events or a user.
	 */
}
