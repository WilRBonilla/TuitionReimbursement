package dev.bonilla.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.bonilla.model.UserLogin;
import dev.bonilla.service.UserLoginService;
import dev.bonilla.service.UserLoginServiceImpl;
import dev.bonilla.utility.MyLogger;

public class UserController {
	
	public static Gson gson = new Gson();
	static UserLogin user;
	public static UserLoginService us = new UserLoginServiceImpl();
	
	public static void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger.logger.info("CONTROLLER");
		
		try {
			// A magical framework that will turn our JSON into a POJO for our class.
			user = gson.fromJson(request.getReader(), UserLogin.class);
			// Immediately return the user credentials, if found. 
			user = us.getUser(user.getEmail(), user.getPassword());
			//UserLoginDAOImpl.conn.close(); // closing the connection once we have the information.
			if(user != null) {
				System.out.println(user.toString() + " and pw: " + user.getPassword());
				response.setStatus(200);
				response.getWriter().append(gson.toJson(user));
//				response.setContentType("text/html;charset=UTF-8");
//				response.sendRedirect("landing.html");
				HttpSession sess = request.getSession();
				sess.setAttribute("user", gson.toJson(user));
				System.out.println(sess.getId());
				
			} else {
				MyLogger.logger.error("LOGIN NOT SUCCESSFUL");
				response.setStatus(404);
			}
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		} 
	}
	
	// This is here purely for the purpose of getting the session credentials for the logged in user.
	public static void getUserSession(HttpServletRequest request, HttpServletResponse response) {
		// simply sends the user info back to the landing page.
		MyLogger.logger.info("CONTROLLER");
		HttpSession sess = request.getSession();
		//Getting session information so that we do not have to store the user as a static variable.
		UserLogin userSess = gson.fromJson((String) sess.getAttribute("user"), UserLogin.class);
		System.out.println(sess.getId());
		
		try {
			response.getWriter().append(gson.toJson(userSess));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void getAllUsers( HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyLogger.logger.info("CONTROLLER");
		List<UserLogin> usersList = us.getAllUserLogins();
		response.getWriter().append(gson.toJson(usersList));
		
	}
	public static void createNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyLogger.logger.info("CONTROLLER");
		UserLogin u = gson.fromJson(request.getReader(), UserLogin.class);
		us.createUserLogin(u);
		
	}
	
	public static void updateUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		MyLogger.logger.info("CONTROLLER");
		request.getParameter("email");
		us.updateUserLogin(user.getUid(), user.getEmail());
		
	}
	
	public static void deleteUser (HttpServletRequest request, HttpServletResponse response) throws IOException{
		MyLogger.logger.info("CONTROLLER");
		UserLogin u = gson.fromJson(request.getReader(), UserLogin.class);
		us.deleteUserLogin(u.getUid(), u.getEmail());
		
	}
	
	public static void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyLogger.logger.info("CONTROLLER: USER LOGOUT");
		HttpSession sess = request.getSession();
		sess.invalidate();
		user = null;
		
	}
	
	
		
}
