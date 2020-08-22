package dev.bonilla.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.service.RequestService;
import dev.bonilla.service.RequestServiceImpl;
import dev.bonilla.utility.MyLogger;

public class RequestController {
	static RequestService rs = new RequestServiceImpl();
	static Gson gson = new Gson();

	public static void createRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("REQUEST CONTROLLER");
		HttpSession sess = request.getSession();
		try {
			// Retrieving the event object saved in the session previously
			Event event = (Event) sess.getAttribute("event");
			// Retrieving UserLogin from the session as well.
			UserLogin user = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
			// Request is the rest of the form the user submitted.
			Request req = gson.fromJson(request.getReader(), Request.class);
			req.setUid(user.getUid());
			req.setStatus(user.getSid());// Sets the status to the user's supervisor ID to be looked at next.

			if (rs.createRequest(req, event, user.getSid())) {
				System.out.println("Successfully created request");
			}

		} catch (Exception e) {
			MyLogger.logger.error("createRequest THREW AN EXCEPTION");
			e.printStackTrace();
		}
		// get the user info from the current session

	}

	public static void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public static void getAllRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("REQUEST CONTROLLER");
		HttpSession sess = request.getSession();
		UserLogin user = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
		List<Request> requestList = rs.getAllRequests(user.getUid());
		
		response.getWriter().append(gson.toJson(requestList));
		
	}
	public static void getAllEmployeeRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("REQUEST CONTROLLER");
		HttpSession sess = request.getSession();
		UserLogin user = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
		List<EmployeeRequest> empList = rs.getAllMyEmpRequests(user.getUid());
		
		response.getWriter().append(gson.toJson(empList));
		
	}
	
	public static void superApproveRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("REQUEST CONTROLLER");
		HttpSession sess = request.getSession();
		// This is login for the supervisor
		UserLogin user = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
		Request[] reqarr = gson.fromJson(request.getReader(), Request[].class);
//		for (int i = 0; i < reqarr.length; i++) {
//			reqarr[i].setStatus(user.getSid());
//			System.out.println(reqarr[i].getStatus());
//			
//		}
		// Sends some request updates and the approver's information
		rs.updateSuperApprove(reqarr, user);
		
	}
	public static void denyRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("REQUEST CONTROLLER");
		HttpSession sess = request.getSession();
		UserLogin user = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
		Request[] reqarr = gson.fromJson(request.getReader(), Request[].class);
		
		for (int i = 0; i < reqarr.length; i++) {
			reqarr[i].setStatus(0);
			System.out.println(reqarr[i].getStatus());
			
		}
		// Sends some request updates and the approver's information
		rs.updateDenyRequest(reqarr);
		
	}

}
