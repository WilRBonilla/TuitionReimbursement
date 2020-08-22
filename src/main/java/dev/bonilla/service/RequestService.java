package dev.bonilla.service;

import java.util.List;

import dev.bonilla.DAO.RequestDAO;
import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;

public interface RequestService {
	public Request getRequest(int rid);
	public Request getRequest(int uid, int eid);
	public List<Request> getAllRequests(int uid);
	public List<EmployeeRequest> getAllMyEmpRequests(int sid);
	public boolean createRequest(Request req, Event event, int sid);
	public boolean updateDenyRequest(Request[] reqarr);
	public boolean updateSuperApprove(Request[] reqarr, UserLogin user);
	public boolean deleteRequest(Request req);
	
}
