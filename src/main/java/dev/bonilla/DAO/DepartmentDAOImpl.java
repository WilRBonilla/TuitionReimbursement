package dev.bonilla.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.bonilla.model.Department;
import dev.bonilla.utility.JDBCConnection;
import dev.bonilla.utility.MyLogger;

public class DepartmentDAOImpl implements DepartmentDAO {
	static Connection conn = JDBCConnection.getConnection();
	PreparedStatement ps = null;
	@Override
	public Department getDepartment(int did) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartment(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> getAllDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createDepartment(int did, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDepartment(int did) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDepartment(int did) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDepartmentHead(int uid) {
		MyLogger.logger.info("DAO: Department");
		String sql = "SELECT d.d_head FROM departments d INNER JOIN userlogin u ON d.d_id = u.d_id WHERE u_id = ? ";
		int dhead = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dhead = rs.getInt("D_HEAD");
			}
			
			return dhead;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dhead;
	}

}
