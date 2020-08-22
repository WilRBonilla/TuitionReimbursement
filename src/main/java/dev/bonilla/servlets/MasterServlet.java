package dev.bonilla.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.MyLogger;

/**
 * Servlet implementation class MasterServlet
 */
@SuppressWarnings("unused")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger.logger.info("GET REQUEST");
	
		RequestHelper.process(request, response);
		
	}

	// NEED doPost to pass information on to server to verify credentials B/C request body for Get requests is empty
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger.logger.info("FUNNELLING POST TO GET REQUEST");
		doGet(request, response);
	}
	
	private void addCorsHeader(HttpServletResponse resp) {
		//log.trace("adding headers");
//		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//		resp.addHeader("Vary", "Origin");
		//if I don't care about getting my cookie, this will work
		resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Produces", "application/json");
	}

}
