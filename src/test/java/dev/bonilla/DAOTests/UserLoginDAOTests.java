package dev.bonilla.DAOTests;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.bonilla.DAO.UserLoginDAO;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.UserLogin;

class UserLoginDAOTests {

	UserLoginDAO ud = new UserLoginDAOImpl();
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void getUserFail() {
		UserLogin u = new UserLogin();
		u.setEmail("helloworld@email.com");
		u.setPassword("password");
		UserLogin user = ud.getUser(u.getEmail(), u.getPassword());
		Assertions.assertNull(user);

	}
	@Test
	public void getUserSuccess() {
		UserLogin u = new UserLogin();
		u.setEmail("leslieknope@email.com");
		u.setPassword("password");
		UserLogin actual = ud.getUser(u.getEmail(), u.getPassword());
		Assertions.assertNotNull(actual);
	}
	
	@Test
	public void getUserByID() {
		UserLogin u = ud.getUser(1000);
		Assertions.assertNotNull(u);
	}
	
	@Test
	public void getAllUsers() {
		List<UserLogin> ulist = ud.getAllUserLogins();
		
		Assertions.assertNotNull(ulist);
	}
	
	@Test
	public void getAllUsersByEvent(){
		
	}
	@Test
	public void createUserUnavailableLogin() {
		
		UserLogin u = new UserLogin();
		u.setAddress("123 Address");
		u.setDid(102);
		u.setAvailable(1000);
		u.setEmail("leslieknope@email.com");
		u.setFirstname("BOB");
		u.setLastname("LOBLAW");
		u.setPhone(111_111_1111L);
		u.setSid(1002);
		u.setPassword("password");
		UserLogin actual = ud.createUserLogin(u);
		Assertions.assertNull(actual);
		
		
	}
	@Test
	public void createUserSuccess() {
		UserLogin u = new UserLogin();
		u.setAddress("123 Address");
		u.setDid(102);
		u.setAvailable(1000);
		u.setEmail("DUMMY@DUMMY.COM");
		u.setFirstname("BOB");
		u.setLastname("LOBLAW");
		u.setPhone(1111111111L);
		u.setSid(1002);
		u.setPassword("password");
		UserLogin actual = ud.createUserLogin(u);
		Assertions.assertNotNull(actual);
	}
	@Test 
	public void updateUsernameSuccess() {
		UserLogin u = ud.getUser("DUMMY@DUMMY.COM", "password");
		u.setFirstname("ROBERT");

		boolean actual = ud.updateUserLogin(u.getUid(), u.getEmail());
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void deleteUserSuccess() {
		UserLogin u = ud.getUser("DUMMY@DUMMY.COM", "password");
		boolean actual = ud.deleteUserLogin(u.getUid(), u.getEmail());
		Assertions.assertTrue(actual);
	}
}
