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

import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.UserLogin;
import dev.bonilla.service.UserLoginServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserLoginServiceTests {
	
	@Mock
	UserLoginDAOImpl udao;
	@InjectMocks
	UserLoginServiceImpl us;
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getUser() {
		UserLogin mock = new UserLogin();
		String email = "email@email.com";
		String password = "password";
		
		Mockito.when(udao.getUser(email, password)).thenReturn(mock);
		
		
		UserLogin actual = us.getUser(email, password);
		Assertions.assertNotNull(actual);
	}
	
	@Test
	void getAllUserLogins() {
		List<UserLogin> mocklist = new ArrayList<UserLogin>();
		
		Mockito.when(udao.getAllUserLogins()).thenReturn(mocklist);
		List<UserLogin> actual = us.getAllUserLogins();
		Assertions.assertNotNull(actual);
		
	}
	@Test
	void getAllUserLoginsbyeid() {
		List<UserLogin> mocklist = new ArrayList<UserLogin>();
		int eid = 1;
		Mockito.when(udao.getAllUserLogins(eid)).thenReturn(mocklist);
		List<UserLogin> actual = us.getAllUserLogins(eid);
		Assertions.assertNotNull(actual);
		
	}
	@Test
	void createUser() {
		String email = "";
		int uid = 1;
		UserLogin u = new UserLogin();
		UserLogin mock = new UserLogin();
		Mockito.when(udao.createUserLogin(u)).thenReturn(mock);
		UserLogin actual = us.createUserLogin(u);
		Assertions.assertNotNull(actual);
		
	}
	@Test
	void updateUser() {
		String email = "";
		int uid = 1;
		Mockito.when(udao.updateUserLogin(uid, email)).thenReturn(true);
		boolean actual = us.updateUserLogin(uid, email);
		Assertions.assertTrue(actual);
	}
	@Test
	void deleteUser() {
		String email = "";
		int uid = 1;
		boolean actual = us.deleteUserLogin(uid, email);
		Assertions.assertFalse(actual);
		
	}

}
