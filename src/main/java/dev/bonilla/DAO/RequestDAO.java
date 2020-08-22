package dev.bonilla.DAO;

import java.util.List;

import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;

public interface RequestDAO {
	public Request getRequest(int rid);
	public Request getRequest(int uid, int value);
	public List<Request> getAllRequests(int uid);
	public List<EmployeeRequest> getAllMyEmpRequests(int sid);
	public boolean createRequest(Request req, Event event, int sid);
	public boolean updateSuperApprove(Request reqarr, UserLogin user);
	public boolean updateHeadApprove(Request r, UserLogin employee);
	public boolean updateDenyRequest(Request reqarr);
	public boolean updateRequest(Request req);
	public boolean deleteRequest(Request req);
}
