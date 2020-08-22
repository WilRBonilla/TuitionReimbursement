package dev.bonilla.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.bonilla.model.Event;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.MyLogger;

public class EventController {
	
	static Gson gson = new Gson();
	public static void getEvent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	public static void createEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("EVENT CONTROLLER");
		HttpSession sess = request.getSession();
		System.out.println(sess.getId());
		Event event = gson.fromJson(request.getReader(), Event.class);
		sess.setAttribute("event", event);
	}
}
