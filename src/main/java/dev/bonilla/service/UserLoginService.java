package dev.bonilla.service;

import java.util.List;

import dev.bonilla.model.UserLogin;

public interface UserLoginService {
	public UserLogin getUser(String email, String password);
	public List<UserLogin> getAllUserLogins();
	public List<UserLogin> getAllUserLogins(int eid); // involve an inner join w/ events and requests.
	public UserLogin createUserLogin(UserLogin ul);
	public boolean updateUserLogin(int uid, String email);
	public boolean deleteUserLogin(int uid, String email);
}
