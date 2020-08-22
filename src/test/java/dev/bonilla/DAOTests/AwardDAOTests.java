package dev.bonilla.DAOTests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import dev.bonilla.DAO.AwardDAO;
import dev.bonilla.DAO.AwardDAOImpl;
import dev.bonilla.DAO.RequestDAO;
import dev.bonilla.DAO.RequestDAOImpl;
import dev.bonilla.DAO.UserLoginDAO;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.Award;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class AwardDAOTests {
	RequestDAO rd = new RequestDAOImpl();
	AwardDAO ad = new AwardDAOImpl();
	UserLoginDAO ud = new UserLoginDAOImpl();
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createAwardSuccess() {
		int rid = 8;
		Request r = rd.getRequest(rid);
		UserLogin u = ud.getUser(r.getUid());
		Award a = new Award();
		a.setAid(rid);
		a.setNotes(r.getNotes());
		a.setUid(u.getUid());
//		a.setDate(date);
		a.setEid(r.getEid());
		a.setValue(r.getValue());
		
		boolean actual = ad.createAward(a, r, u);
		
		Assertions.assertTrue(actual);
		
	}
	@Order(1)
	@Test
	void createAwardFail() {
		int rid = 8;
		Request r = rd.getRequest(rid);
		UserLogin u = ud.getUser(r.getUid());
		Award a = new Award();
		a.setAid(rid);
		a.setNotes(r.getNotes());
		a.setUid(u.getUid());
//		a.setDate(date);
		a.setEid(r.getEid());
		a.setValue(r.getValue());
		
		boolean actual = ad.createAward(a, r, u);
		
		Assertions.assertTrue(!actual);
		
	}
	
	@Test
	void getAward() {
		Award a = ad.getAward(8);
		int rid = 8;
		Request r = rd.getRequest(rid);
		UserLogin u = ud.getUser(r.getUid());
		a.setAid(rid);
		a.setNotes(r.getNotes());
		a.setUid(u.getUid());
//		a.setDate(date);
		a.setEid(r.getEid());
		a.setValue(r.getValue());
		
		Assertions.assertTrue(a != null);
		
	}
	@Test
	void getNonExistingAward() {
		Award a = ad.getAward(7777);
		
		Assertions.assertNull(a);
	}
	@Test
	void getAllAwardsByUID() {
		List<Award> awardList = ad.getAllAwards(1021);
		
		Assertions.assertNotNull(awardList);
	}
	@Test
	void getAllAwards() {
		List<Award> awardList = ad.getAllAwards();
		
		Assertions.assertNotNull(awardList);
	}
	@Test 
	void updateAward() {
		Award a = new Award();
		a.setAid(8);
		a.setValue(123);
		a.setNotes("Budget changes");
		Assertions.assertTrue(ad.updateAward(a));
	}
	
	@Test 
	void updateAwardF() {
		Award a = new Award();
		a.setAid(99999);
		a.setValue(123);
		a.setNotes("Budget changes");
		Assertions.assertTrue(ad.updateAward(a));
	}
	
	@Test
	void deleteAwardFail() {
		Award a = new Award();
		a.setAid(9999999);
		Assertions.assertTrue(ad.deleteAward(a));
	
	}
	
	
	@AfterAll
	void cleanup() {
		Award a = new Award();
		a.setAid(8);
		ad.deleteAward(a);
		
		Request r = rd.getRequest(8);
		r.setStatus(1001);
		r.setNotes("nothing to see here");
		rd.updateRequest(r);
	}

}
