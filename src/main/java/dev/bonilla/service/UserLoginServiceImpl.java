package dev.bonilla.service;

import java.util.List;

import dev.bonilla.DAO.UserLoginDAO;
import dev.bonilla.DAO.UserLoginDAOImpl;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.MyLogger;

public class UserLoginServiceImpl implements UserLoginService {
	UserLoginDAO ud = new UserLoginDAOImpl();
	@Override
	public UserLogin getUser(String email, String password) {
		MyLogger.logger.info("SERVICE");
		return ud.getUser(email, password);
	}

	@Override
	public List<UserLogin> getAllUserLogins() {
		// Sort alphabetically, maybe.
		MyLogger.logger.info("SERVICE");
		return ud.getAllUserLogins();
	}

	@Override
	public List<UserLogin> getAllUserLogins(int eid) {
		MyLogger.logger.info("SERVICE");
		return null;
	}

	@Override
	public UserLogin createUserLogin(UserLogin ul) {
		MyLogger.logger.info("SERVICE");
		return ud.createUserLogin(ul);
	}

	@Override
	public boolean updateUserLogin(int uid, String email) {
		MyLogger.logger.info("SERVICE");
		return ud.updateUserLogin(uid, email);
	}

	@Override
	public boolean deleteUserLogin(int uid, String email) {
		MyLogger.logger.info("SERVICE");
		return false;
	}

}
