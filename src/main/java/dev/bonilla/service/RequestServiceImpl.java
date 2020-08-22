package dev.bonilla.service;

import java.util.List;

import dev.bonilla.DAO.AwardDAO;
import dev.bonilla.DAO.AwardDAOImpl;
import dev.bonilla.DAO.DepartmentDAO;
import dev.bonilla.DAO.DepartmentDAOImpl;
import dev.bonilla.DAO.RequestDAO;
import dev.bonilla.DAO.RequestDAOImpl;
import dev.bonilla.DAO.UserLoginDAO;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.Award;
import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.MyLogger;

public class RequestServiceImpl implements RequestService {
	RequestDAO rd = new RequestDAOImpl();
	DepartmentDAO dd = new DepartmentDAOImpl();
	AwardDAO ad = new AwardDAOImpl();
	UserLoginDAO ud = new UserLoginDAOImpl();
	@Override
	public Request getRequest(int rid) {
		MyLogger.logger.info("SERVICE LAYER: GETTING A REQUEST BY RID: " + rid);
		return rd.getRequest(rid);
	}

	@Override
	public Request getRequest(int uid, int eid) {
		MyLogger.logger.info("SERVICE LAYER: GETTING A REQUEST");
		return rd.getRequest(uid, eid);
	}

	@Override
	public List<Request> getAllRequests(int uid) {
		MyLogger.logger.info("SERVICE LAYER: GET MY REQUESTS");
		return rd.getAllRequests(uid);
	}


	public List<EmployeeRequest> getAllMyEmpRequests(int sid) {
		MyLogger.logger.info("SERVICE LAYER: EMPLOYEE REQUESTS");
		return rd.getAllMyEmpRequests(sid);
	}
	
	@Override
	public boolean createRequest(Request req, Event event, int sid) {
		MyLogger.logger.info("SERVICE LAYER: CREATE REQUEST");
		// If this person is his or her own "superior" in this system, then assign benco.
		if (sid == req.getUid()) {
			sid = 1040;
		}
		
		return rd.createRequest(req, event, sid);
	}

	@Override
	public boolean updateDenyRequest(Request[] reqarr) {
		MyLogger.logger.info("SERVICE LAYER: DENY REQUEST");
		for(Request r : reqarr) {
			rd.updateDenyRequest(r);
		}
		return true;
	}
	@Override
	public boolean updateSuperApprove(Request[] reqarr, UserLogin user) {
		MyLogger.logger.info("SERVICE LAYER: SUPERVISOR APPROVAL");
		boolean success;
		// Get the Department head's ID.
		int dhead = dd.getDepartmentHead(user.getUid());
		int benco = 1040;
		
		
		MyLogger.logger.info("DEPARTMENT HEAD ID: " + dhead);
		// First, we check if the supervisor approving is the benco.
		if (user.getUid() == benco) {
			MyLogger.logger.info("[SERVICE] BENCO APPROVAL");
			for(Request r : reqarr) {
				// Retrieve the employee for the request
				r = rd.getRequest(r.getRid());
				UserLogin employee = ud.getUser(r.getUid());
				// Populate award's fields.
				Award award = new Award();
				award.setAid(r.getRid());
				award.setValue(r.getValue());
				award.setEid(r.getEid());
				award.setNotes(r.getNotes());
				// Finalize the request to award as PENDING.
				// This will move the request to the "Award" pool.
				ad.createAward(award, r, employee);
				
			}
			return true;
			
			// Now, we check if the supervisor is the department head.
		} else if (user.getUid() == dhead){
			MyLogger.logger.info("[SERVICE] HEAD APPROVAL");
			for(Request r : reqarr) {
				UserLogin employee = ud.getUser(r.getUid());
				r.setStatus(dhead);
				// Execute department head approval
				rd.updateHeadApprove(r, employee);
			}
			return true;
			

		} else {
			for(Request r: reqarr) {
				MyLogger.logger.info("[SERVICE] SUPER APPROVAL");
				UserLogin employee = ud.getUser(r.getUid());
				r.setStatus(user.getSid());
				// Execute supervisor approval
				rd.updateSuperApprove(r, employee);
			}
			return true;
		}
		
	
	}

	@Override
	public boolean deleteRequest(Request req) {
		return rd.deleteRequest(req);
	}

	

	



	

	

}
