package dev.bonilla.DAOTests;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import dev.bonilla.DAO.EventDAO;
import dev.bonilla.DAO.EventDAOImpl;
import dev.bonilla.DAO.RequestDAO;
import dev.bonilla.DAO.RequestDAOImpl;
import dev.bonilla.DAO.UserLoginDAO;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.MyLogger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class RequestDAOTests {
	RequestDAO rd = new RequestDAOImpl();
	EventDAO ed = new EventDAOImpl();
	UserLoginDAO ud = new UserLoginDAOImpl();
	
	
	@BeforeAll
	
	void tearDown() throws Exception {
		
	}

	@Test
	void getRequestFail() {
		Request actual = rd.getRequest(99_9999);
		Assertions.assertNull(actual);
	}
	@Test
	void getRequestSuccess() {
		Request actual = rd.getRequest(8);
		Assertions.assertNotNull(actual);
	}
	@Test
	void getReqByAllIDFail() {
		List<Request> actual = rd.getAllRequests(99_999);
		Assertions.assertTrue(actual.isEmpty());
	}
	@Test
	void getReqByAllIDSuccess() {
		List<Request> actual = rd.getAllRequests(1_000);
		Assertions.assertNotNull(actual);
	}
	@Test
	void getAllEmployeeRequests() {
		List<EmployeeRequest> actual = rd.getAllMyEmpRequests(1000);
		Assertions.assertNotNull(actual);
	}
	
	@Test
	void createNewReqF() {
		Request req = new Request();
		Event event = new Event();
		boolean actual = rd.createRequest(req, event, 1001);
		Assertions.assertFalse(actual);
	}
	@Order(1)
	@Test
	void createNewReq() {
		Request req = new Request();
		Event event = new Event();
		// Date fields
		req.setUid(1000);
		req.setValue(9_001);
		req.setStatus(1);
		req.setFlag(0);
		req.setNotes(" ");
		// Event fields
		Date date = Date.valueOf(LocalDate.now());
		String time = Time.valueOf(LocalTime.now()).toString();
		event.setName("Parks for Dums");
		event.setDescription("Learn about parks, even if you're a dummy!");
		event.setLocation("Pawnee, in the pit.");
		event.setCategory("certification");
		event.setEmployeetime(72);
		event.setEventtime(time);
		event.setEventDate(date.toString());
		
		boolean actual = rd.createRequest(req, event, 1000);
		Assertions.assertTrue(actual);
	}
	@Order(2)
	@Test
	void getReqByUID_value() {
		Request actual = rd.getRequest(1_000, 9_001);
		Assertions.assertNotNull(actual);
	}
	@Order(3)
	@Test
	void updateRequestSuccess() {
		Request req = rd.getRequest(1_000, 9_001);
		req.setUid(1000);
		req.setStatus(9_002);
		req.setFlag(0);
		req.setNotes("Test Notes");
		
		boolean actual = rd.updateRequest(req);
		Assertions.assertTrue(actual);
	}
	@Order(4)
	@Test
	void updateSuperApprove() {
		UserLogin u = ud.getUser(1000);
		Request req = rd.getRequest(1_000, 9_001);
		MyLogger.logger.info(req.toString());
		Assertions.assertTrue(rd.updateSuperApprove(req, u));
		
	}
	@Order(5)
	@Test
	void updateHeadApprove() {
		Request r = rd.getRequest(1_000, 9_001);
		UserLogin u = ud.getUser(1000);
		Assertions.assertTrue(rd.updateHeadApprove(r, u));
	}
	@Order(6)
	@Test
	void updateDenyRequest() {
		Request r = rd.getRequest(1_000, 9_001);
		Assertions.assertTrue(rd.updateDenyRequest(r));
	}

	@Order(7)
	@Test
	void deleteRequestSuccess() {
		Request actual = rd.getRequest(1_000, 9_001);
		Assertions.assertTrue(rd.deleteRequest(actual));
	}
	
	@AfterAll
	void cleanup() {
		Event e = ed.getEvent("Parks for Dums");
		ed.deleteEvent(e);
		// Need an EVENT clean up here.
		
	}
}
