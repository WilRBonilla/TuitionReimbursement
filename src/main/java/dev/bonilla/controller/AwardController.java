package dev.bonilla.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.bonilla.model.Award;
import dev.bonilla.model.Event;
import dev.bonilla.model.UserLogin;
import dev.bonilla.service.AwardService;
import dev.bonilla.service.AwardServiceImpl;
import dev.bonilla.utility.MyLogger;

public class AwardController {
	static AwardService as = new AwardServiceImpl();
	static Gson gson = new Gson();
	
	public static void getMyAwards (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyLogger.logger.info("AWARD CONTROLLER");
		HttpSession sess = request.getSession();
		UserLogin user = gson.fromJson((String)sess.getAttribute("user"), UserLogin.class);
		List<Award> awardList = as.getAllAwards(user.getUid());
		
		response.getWriter().append(gson.toJson(awardList));
		
	}
	
	
	public static void createAward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyLogger.logger.info("AWARD CONTROLLER");
	 // This functionality is caused when the benco happens to approve a request. 
	}
}
