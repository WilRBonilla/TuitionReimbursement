package dev.bonilla.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.bonilla.controller.AwardController;
import dev.bonilla.controller.EventController;
import dev.bonilla.controller.RequestController;
import dev.bonilla.controller.UserController;
import dev.bonilla.utility.MyLogger;

public class RequestHelper {
	
	// Helps delegate and point where a task is taken care of if the following layers.
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		MyLogger.logger.info("REQUEST HELPER: SWITCH");
		String uri = request.getRequestURI();
		
		
		switch(uri) {
		case "/Project1-TuitionReimbursement/login.do":
			MyLogger.logger.info("RequestHelper");
			UserController.getUser(request, response);
			break;
		case "/Project1-TuitionReimbursement/usersession.do":
			MyLogger.logger.info("RequestHelper for user session");
			UserController.getUserSession(request, response);
			break;
		case "/Project1-TuitionReimbursement/submitrequest.do":
			MyLogger.logger.info("RequestHelper for reimbursement submission");
			RequestController.createRequest(request, response);
			break;
		case "/Project1-TuitionReimbursement/submitevent.do":
			MyLogger.logger.info("REQUESTHELPER: submitevent");
			EventController.createEvent(request, response);
			break;
		case "/Project1-TuitionReimbursement/getAllRequests.do":
			MyLogger.logger.info("REQUESTHELPER: GET ALL REQUESTS");
			RequestController.getAllEmployeeRequests(request, response);
			break;
		case "/Project1-TuitionReimbursement/getUserRequests.do":
			MyLogger.logger.info("REQUESTHELPER: GET ALL REQUESTS");
			RequestController.getAllRequests(request, response);
			break;
		case "/Project1-TuitionReimbursement/getawards.do":
			MyLogger.logger.info("REQUESTHELPER: GET ALL AWARDS");
			AwardController.getMyAwards(request, response);
			break;
			// First level supervisor approval
		case "/Project1-TuitionReimbursement/superapprove.do":
			MyLogger.logger.info("REQUESTHELPER: Approve Requests");
			RequestController.superApproveRequests(request, response);
			break;
		case "/Project1-TuitionReimbursement/superdeny.do":
			MyLogger.logger.info("REQUESTHELPER: Approve Requests");
			RequestController.denyRequest(request, response);
			break;
		case "/Project1-TuitionReimbursement/logout.do":
			MyLogger.logger.info("REQUESTHELPER: USER LOGOUT");
			break;
			
			
		default:
//			response.setStatus(404);
			
		
		}
	}
	

}
