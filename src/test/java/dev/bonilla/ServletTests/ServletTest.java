package dev.bonilla.ServletTests;
import static org.mockito.Mockito.times;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.bonilla.controller.AwardController;
import dev.bonilla.controller.EventController;
import dev.bonilla.controller.RequestController;
import dev.bonilla.controller.UserController;
import dev.bonilla.servlets.RequestHelper;





@SuppressWarnings("static-access")
@ExtendWith(MockitoExtension.class)
class ServletTest {
	
	@Mock
	RequestController rc;
	@Mock
	EventController ec;
	@Mock
	AwardController ac;
	@Mock
	UserController uc;
	@Mock
	HttpServletRequest rq;
	@Mock
	HttpServletResponse rp;
	
	@InjectMocks
	RequestHelper helper;
	
	

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void caselogin () throws IOException, ServletException {
		
		String uri = "/Project1-TuitionReimbursement/login.do";
		Mockito.when(rq.getRequestURI()).thenReturn(uri);
		Mockito.doNothing().when(uc).getUser(rq, rp);
		RequestHelper.process(rq, rp);
		
		
	}
	@Test
	void caselogin2 () throws IOException, ServletException {
		
		String uri = "/Project1-TuitionReimbursement/usersession.do";
		Mockito.when(rq.getRequestURI()).thenReturn(uri);
		Mockito.doNothing().when(uc).getUserSession(rq, rp);
		RequestHelper.process(rq, rp);
		
//		Mockito.verify(uc, times(1)).getUserSession(rq, rp);
		
	}
	@Test
	void caselogin3 () throws IOException, ServletException {
		
		String uri = "/Project1-TuitionReimbursement/submitrequest.do";
		Mockito.when(rq.getRequestURI()).thenReturn(uri);
		Mockito.doNothing().when(rc).createRequest(rq, rp);
		RequestHelper.process(rq, rp);
		
//		Mockito.verify(rc, times(1)).createRequest(rq, rp);
		
	}
	
	@Test
	void caselogin4 () throws IOException, ServletException {
		
		String uri = "/Project1-TuitionReimbursement/submitevent.do";
		Mockito.when(rq.getRequestURI()).thenReturn(uri);
		Mockito.doNothing().when(rc).getAllEmployeeRequests(rq, rp);
		RequestHelper.process(rq, rp);
		
		Mockito.verify(rc, times(1)).getAllEmployeeRequests(rq, rp);
		
	}
	@Test
	void caselogin5 () throws IOException, ServletException {
		
		String uri = "/Project1-TuitionReimbursement/getAllRequests.do";
		Mockito.when(rq.getRequestURI()).thenReturn(uri);
		
		RequestHelper.process(rq, rp);
		
	}
	

}
