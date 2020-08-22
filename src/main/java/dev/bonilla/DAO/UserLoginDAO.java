package dev.bonilla.DAO;

import java.util.List;

import dev.bonilla.model.UserLogin;

public interface UserLoginDAO {
	public UserLogin getUser(String email, String password);
	public UserLogin getUser(int uid);
	public List<UserLogin> getAllUserLogins();
	public List<UserLogin> getAllUserLogins(int eid); // involve an inner join w/ events and requests.
	public UserLogin createUserLogin(UserLogin ul);
	public boolean updateUserLogin(int id, String email);
	public boolean deleteUserLogin(int id, String email);
}
