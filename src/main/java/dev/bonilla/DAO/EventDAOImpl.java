package dev.bonilla.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.bonilla.model.Event;
import dev.bonilla.utility.JDBCConnection;

public class EventDAOImpl implements EventDAO {
	static Connection conn = JDBCConnection.getConnection();
	PreparedStatement ps = null;
	@Override
	public Event getEvent(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(String name) {
		String sql = "SELECT * FROM event WHERE e_name = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Event(rs.getInt("E_ID"), rs.getString("E_NAME"), rs.getString("E_DESC"), rs.getString("E_LOC"),
								rs.getInt("E_CATE"), rs.getInt("E_EMPLOYEETIME"), rs.getString("E_TIME"), rs.getString("E_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Event> getallEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createEvent(Event e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvent(Event e) {
		String sql = "DELETE FROM event WHERE e_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(e.getEid()));
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEvent(Event e) {
		// TODO Auto-generated method stub
		return false;
	}

}
