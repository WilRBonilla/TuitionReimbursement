package dev.bonilla.ServiceTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.bonilla.DAO.AwardDAOImpl;
import dev.bonilla.DAO.RequestDAOImpl;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.Award;
import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.service.RequestServiceImpl;
//@RunWith(MockitoJUnitRunner.class)

@ExtendWith(MockitoExtension.class)
class RequestServiceTests {
	@Mock
	UserLoginDAOImpl udao;
	@Mock
	AwardDAOImpl adao;
	@Mock
	RequestDAOImpl rdao;
	
	@InjectMocks
	RequestServiceImpl rs;
	
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void getRequest() {
		Request expected = new Request();
		expected.setRid(8);
		Mockito.when(rdao.getRequest(8)).thenReturn(expected);
		Request r = rs.getRequest(8);
		
		Assertions.assertEquals(expected.getRid(), r.getRid());
	}
	@Test
	void getRequestbyID_eid() {
		Request expected = new Request();
		expected.setRid(8);
		expected.setEid(10001);
		Mockito.when(rdao.getRequest(8, 10001)).thenReturn(expected);
		Request r = rs.getRequest(8, 10001);
		
		Assertions.assertEquals(expected.getRid(), r.getRid());
	}
	
	@Test
	void getAllMyRquests() {
		int uid = 1000;
		List<Request> mylist = new ArrayList<Request>();
		
		Mockito.when(rdao.getAllRequests(uid)).thenReturn(mylist);
		
		List<Request> actual = rs.getAllRequests(uid);
		Assertions.assertTrue(actual.isEmpty());
	}
	
	@Test
	void getAllMyEmpRequests() {
		int sid = 1000;
		List<EmployeeRequest> mylist = new ArrayList<EmployeeRequest>();
		
		Mockito.when(rdao.getAllMyEmpRequests(sid)).thenReturn(mylist);
		
		List<EmployeeRequest> actual = rs.getAllMyEmpRequests(sid);
		Assertions.assertTrue(mylist.isEmpty());
		
	}
	
	@Test
	void createRequest() {
		Request req = new Request();
		req.setUid(1001);
		
		Event event = new Event();
		
		int sid =1001;
		Mockito.lenient().when(rdao.createRequest(req, event, sid)).thenReturn(true);
		boolean actual = rs.createRequest(req, event, sid);
		Assertions.assertTrue(actual);
		
		
		
	}
	
	@Test
	void updateDenyRequest() {
		Request[] arr = new Request[1];
		Request r = new Request();
		arr[0] = r;
		
		Mockito.when(rdao.updateDenyRequest(r)).thenReturn(true);
		boolean actual = rs.updateDenyRequest(arr);
		
		Assertions.assertTrue(actual);
		
		
	}
	
	@Test
	void superApproveDhead() {
		Request[] arr = new Request[1];
		Request r = new Request();
		r.setRid(8);
		r.setValue(9000);
		r.setEid(10000);
		r.setNotes("noice");
		r.setUid(1000);
		arr[0] = r;
		UserLogin supere = new UserLogin();
		UserLogin employee = new UserLogin();
		supere.setUid(1001);
		
		Mockito.lenient().when(rdao.updateHeadApprove(r, employee)).thenReturn(true);
		boolean actual = rs.updateSuperApprove(arr, supere);
		
		Assertions.assertTrue(actual);
	}
	
	@Test
	void superApproveBenco() {
		Request[] arr = new Request[1];
		Request r = new Request();
		r.setRid(8);
		r.setValue(9000);
		r.setEid(10000);
		r.setNotes("noice");
		r.setUid(1000);
		arr[0] = r;
		UserLogin benco = new UserLogin();
		UserLogin user = new UserLogin();
		boolean expected = true;
		benco.setUid(1040);
		Award a = new Award();
		// normally, this would return false
		Mockito.lenient().when(udao.getUser(1000)).thenReturn(user);
		Mockito.lenient().when(adao.createAward(a, r, user)).thenReturn(expected);
		boolean actual = rs.updateSuperApprove(arr, benco);
		
		Assertions.assertTrue(actual);
	}
	
	@Test
	void superApprove() {
		Request[] arr = new Request[1];
		Request r = new Request();
		r.setRid(8);
		r.setValue(9000);
		r.setEid(10000);
		r.setNotes("noice");
		r.setUid(1000);
		arr[0] = r;
		
		UserLogin supervisor = new UserLogin();
		supervisor.setUid(1000);
		UserLogin employee = new UserLogin();
		employee.setSid(1000);
		
		
		Mockito.lenient().when(udao.getUser(1000)).thenReturn(employee);
		Mockito.lenient().when(rdao.updateSuperApprove(r, employee)).thenReturn(true);
		boolean actual = rs.updateSuperApprove(arr, supervisor);
		Assertions.assertTrue(actual);
		
		
		
		
	}
	
	@Test
	void deleteRequest() {
		
		
		Request r = new Request();
		Mockito.when(rdao.deleteRequest(r)).thenReturn(true);
		boolean actual = rs.deleteRequest(r);
		
		Assertions.assertTrue(actual);
	}
	
	
	
	

}
