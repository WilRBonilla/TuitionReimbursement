package dev.bonilla.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.JDBCConnection;
import dev.bonilla.utility.MyLogger;

public class UserLoginDAOImpl implements UserLoginDAO {
	public static Connection conn = JDBCConnection.getConnection();
	PreparedStatement ps = null;

	@Override
	public UserLogin getUser(String email, String password) {

		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM userlogin WHERE u_email = ? AND u_password = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MyLogger.logger.info("LOGIN SUCCESS");
				return new UserLogin(rs.getInt("U_ID"), rs.getString("U_EMAIL"), rs.getString("U_PASSWORD"),
						rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME"), rs.getString("U_ADDRESS"),
						rs.getLong("U_PHONE"), rs.getInt("D_ID"), rs.getInt("AVAILABLE"), rs.getInt("s_id"));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("LOGIN FAILED");
			e.printStackTrace();
		}

		return null;
	}

	public UserLogin getUser(int uid) {

		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM userlogin WHERE u_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MyLogger.logger.info("LOGIN SUCCESS");
				return new UserLogin(rs.getInt("U_ID"), rs.getString("U_EMAIL"), rs.getString("U_PASSWORD"),
						rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME"), rs.getString("U_ADDRESS"),
						rs.getLong("U_PHONE"), rs.getInt("D_ID"), rs.getInt("AVAILABLE"), rs.getInt("s_id"));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("LOGIN FAILED");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<UserLogin> getAllUserLogins() {
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM userlogin";
		List<UserLogin> usersList = new ArrayList<UserLogin>();

		try {
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MyLogger.logger.info("GETTING ALL USERS");
				usersList.add(new UserLogin(rs.getInt("U_ID"), rs.getString("U_EMAIL"), rs.getString("U_PASSWORD"),
						rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME"), rs.getString("U_ADDRESS"),
						rs.getLong("U_PHONE"), rs.getInt("D_ID"), rs.getInt("AVAILABLE"), rs.getInt("s_id")));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("COULD NOT GET ALL USERS");
			e.printStackTrace();
		}

		return usersList;
	}

	@Override
	public List<UserLogin> getAllUserLogins(int eid) {

		return null;
	}

	@Override
	public UserLogin createUserLogin(UserLogin ul) {
		MyLogger.logger.info("DAO");
		String sql = "INSERT INTO userlogin VALUES (u_idmaker.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			// Need to double check that this is order in OSQL.
			ps = conn.prepareStatement(sql);
			ps.setString(1, ul.getEmail());
			ps.setString(2, ul.getPassword());
			ps.setString(3, ul.getFirstname());
			ps.setString(4, ul.getLastname());
			ps.setString(5, ul.getAddress());
			ps.setLong(6, ul.getPhone());
			ps.setInt(7, ul.getDid());
			ps.setInt(8, ul.getAvailable());
			ps.setString(9, Integer.toString(ul.getSid()));

			ps.execute();
			MyLogger.logger.info("USER WITH ID: " + ul.getUid() + "HAS BEEN CREATED");
			return ul;
			
		} catch (SQLException e) {
			MyLogger.logger.error("CREATION FAILED");
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public boolean updateUserLogin(int id, String email) {
		MyLogger.logger.info("DAO");
		String sql = "UPDATE userlogin SET u_email = ? WHERE u_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, id);

			ps.execute();
			MyLogger.logger.info("USER WITH ID: " + id + "DELETED");
			
			return true;
			
		} catch (SQLException e) {
			MyLogger.logger.error("UPDATE FAILED");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUserLogin(int id, String email) {
		MyLogger.logger.info("DAO");
		String sql = "DELETE FROM userlogin WHERE u_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();
				MyLogger.logger.info("USER WITH ID: " + id + "DELETED");
				return true;
			
		} catch (SQLException e) {
			MyLogger.logger.error("DELETION FAILED");
			e.printStackTrace();
		}

		return false;
	}

}
