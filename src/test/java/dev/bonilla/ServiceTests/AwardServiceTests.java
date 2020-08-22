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
import dev.bonilla.model.Award;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.service.AwardServiceImpl;

@ExtendWith(MockitoExtension.class)
class AwardServiceTests {
	@Mock
	AwardDAOImpl adao;
	@InjectMocks
	AwardServiceImpl as;
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getAward() {
		Award mock = new Award();
		int aid = 1;
		Mockito.when(adao.getAward(aid)).thenReturn(mock);
		Award actual = as.getAward(aid);
		Assertions.assertNotNull(actual);
		
	}

	@Test
	void getAllAwardsByID() {
		List<Award> mocklist = new ArrayList<Award>();
		int uid = 1;
		Mockito.when(adao.getAllAwards(uid)).thenReturn(mocklist);
		List<Award> actual = as.getAllAwards(uid);
		Assertions.assertNotNull(actual);
		
	}
	
	@Test
	void getAllAwards(){
		List<Award> mocklist = new ArrayList<Award>();
		Mockito.when(adao.getAllAwards()).thenReturn(mocklist);
		List<Award> actual = as.getAllAwards();
		Assertions.assertNotNull(actual);
		
	}
	
	@Test
	void createAward() {
		Award mock = new Award();
		Request r = new Request();
		UserLogin u = new UserLogin();
		Mockito.when(adao.createAward(mock, r, u)).thenReturn(true);
		boolean actual = as.createAward(mock, r, u);
		Assertions.assertNotNull(actual);
		
	}
	
	@Test
	void updateAward() {
		Award award = new Award();
		int aid = 1;
		Mockito.when(adao.updateAward(award)).thenReturn(true);
		boolean actual = as.updateAward(award);
		Assertions.assertTrue(actual);
		
		
	}
	
	@Test 
	void deleteAward(){
		Award award = new Award();
		int aid = 1;
		Mockito.when(adao.deleteAward(award)).thenReturn(true);
		boolean actual = as.deleteAward(award);
		Assertions.assertTrue(actual);
		
		
	}
		

}
