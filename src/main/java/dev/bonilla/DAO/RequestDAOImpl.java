package dev.bonilla.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.bonilla.model.Award;
import dev.bonilla.model.EmployeeRequest;
import dev.bonilla.model.Event;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.JDBCConnection;
import dev.bonilla.utility.MyLogger;

public class RequestDAOImpl implements RequestDAO {
	static Connection conn = JDBCConnection.getConnection();
	PreparedStatement ps = null;

	@Override
	public Request getRequest(int rid) {
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM request WHERE req_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(rid));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Request(rs.getInt("REQ_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"), rs.getInt("REQ_VALUE"),
						rs.getTimestamp("REQ_TIME"), rs.getInt("REQ_STATUS"), rs.getInt("REQ_FLAG"),
						rs.getString("REQ_NOTES"));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO GET REQUEST.");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Request getRequest(int uid, int value){
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM request WHERE u_id = ? AND req_value = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(value));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Request(rs.getInt("REQ_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"), rs.getInt("REQ_VALUE"),
						rs.getTimestamp("REQ_TIME"), rs.getInt("REQ_STATUS"), rs.getInt("REQ_FLAG"),
						rs.getString("REQ_NOTES"));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO GET REQUEST.");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Request> getAllRequests(int uid) {
		MyLogger.logger.info("RETRIEVING REQUESTS FOR USER ID: " + uid);
		List<Request> reqList = new ArrayList<Request>();
		String sql = "SELECT * FROM request WHERE u_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MyLogger.logger.info("SUCCESS");
				// CHECK OSQL for columns
				Request r = new Request(rs.getInt("REQ_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"),
						rs.getInt("REQ_VALUE"), rs.getTimestamp("REQ_TIME"), rs.getInt("REQ_STATUS"),
						rs.getInt("REQ_FLAG"), rs.getString("REQ_NOTES"));
				reqList.add(r);
			}
			return reqList;

		} catch (Exception e) {
			MyLogger.logger.error("FAILED TO GET ALL REQUESTS FOR USER ID: " + uid);
			e.printStackTrace();
			return null;
		}

	}

	public List<EmployeeRequest> getAllMyEmpRequests(int sid) {
		MyLogger.logger.info("RETRIEVING REQUESTS FOR USER ID: " + sid);
		List<EmployeeRequest> reqList = new ArrayList<EmployeeRequest>();

		String sql = "SELECT userlogin.u_id, userlogin.u_firstname, userlogin.u_lastname, userlogin.available, "
				+ "request.req_id, request.req_value, request.req_time, request.req_status, request.req_flag, request.req_notes, "
				+ "userlogin.d_id FROM userlogin INNER JOIN request ON userlogin.u_id = request.u_id WHERE req_status = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(sid));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MyLogger.logger.info("SUCCESS");
				// CHECK OSQL for columns
				EmployeeRequest r = new EmployeeRequest();
				r.setUid(rs.getInt("U_ID"));
				r.setFirstname(rs.getString("U_FIRSTNAME"));
				r.setLastname(rs.getString("U_LASTNAME"));
				r.setAvail(rs.getInt("AVAILABLE"));
				r.setRid(rs.getInt("REQ_ID"));
				r.setValue(rs.getInt("REQ_VALUE"));
				r.setTime(rs.getTimestamp("REQ_TIME"));
				r.setStatus(rs.getInt("REQ_STATUS"));
				r.setFlag(rs.getInt("REQ_FLAG"));
				r.setDepartment(rs.getInt("D_ID"));
				r.setNotes(rs.getString("REQ_NOTES"));
				reqList.add(r);
			}
			return reqList;

		} catch (Exception e) {
			MyLogger.logger.error("FAILED TO GET ALL REQUESTS FOR USER ID: " + sid);
			e.printStackTrace();
		}

		return null;
	}

	// Creates a user request
	@Override
	public boolean createRequest(Request req, Event event, int sid) {
		CallableStatement cs;
		MyLogger.logger.info("DAO");
		String sql = "CALL new_req (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			cs = conn.prepareCall(sql);
			// Request table
			cs.setString(1, Integer.toString(req.getUid()));
			cs.setString(2, Integer.toString(req.getValue()));
			cs.setString(3, Integer.toString(sid)); // status
			cs.setString(4, Integer.toString(req.getFlag()));
			cs.setString(5, req.getNotes());
			// Event table for callable statement
			cs.setString(6, event.getName());
			cs.setString(7, event.getDescription());
			cs.setString(8, event.getLocation());
			cs.setString(9, Integer.toString(event.getCategory()));
			cs.setString(10, Integer.toString(event.getEmployeetime()));
			cs.setString(11, event.getEventtime().toString());
			cs.setDate(12, Date.valueOf(event.getEventDate()));
			cs.execute();
			MyLogger.logger.info("SUCCESSFUL CREATION OF REQUEST");
			return true;

		} catch (SQLException e) {
			MyLogger.logger.info("CREATION FAIL");
			e.printStackTrace();
		} catch (Exception e) {
			MyLogger.logger.info("CREATION FAIL");
			e.printStackTrace();
		}
		return false;
	}

	// User
	public boolean updateSuperApprove(Request r, UserLogin employee) {
		MyLogger.logger.info("DAO: SUPER APPROVE");

		String sql = "UPDATE request SET req_status = ? WHERE req_id = ?";
		try {
			MyLogger.logger.info("S:APPROVE for R_ID:" + r.getRid() + " NEXT TO APPROVE: SUPERVISOR");
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(r.getStatus()));
			ps.setString(2, Integer.toString(r.getRid()));
			ps.execute();

			return true;
		} catch (SQLException e) {
			MyLogger.logger.info("FAILED TO APPROVE");
			e.printStackTrace();
		}

		return false;

	}

	public boolean updateHeadApprove(Request r, UserLogin employee) {
		MyLogger.logger.info("DAO: HEAD APROVE");
		// Check if the approver is the department head, launch it up to the benco.

		// For now, hard-coding the benco to 1040
		String sql = "UPDATE request SET req_status = 1040 WHERE req_id = ?";
		MyLogger.logger.info("S:APPROVE for R_ID:" + r.getRid() + " NEXT TO APPROVE: BENCO");
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(r.getRid()));
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return false;
	}

	public boolean updateDenyRequest(Request r) {
		MyLogger.logger.info("DAO");
		String sql = "UPDATE request SET req_status = ? WHERE req_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(r.getStatus()));
			ps.setString(2, Integer.toString(r.getRid()));

			ps.execute();

			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("COULD NOT DENY REQUEST");
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateRequest(Request req) {
		String sql = "UPDATE request SET req_value = ?, req_status = ?, req_flag = ?, req_notes = ? WHERE req_id = ?";
		MyLogger.logger.info("DAO");
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(req.getValue()));
			ps.setString(2, Integer.toString(req.getStatus()));
			ps.setString(3, Integer.toString(req.getFlag()));
			ps.setString(4, req.getNotes());
			ps.setString(5, Integer.toString(req.getRid()));

			ps.execute();
			return true;

		} catch (SQLException e) {
			MyLogger.logger.info("UPDATE FAIL");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRequest(Request req) {
		String sql = "DELETE request WHERE req_id = ?";
		MyLogger.logger.info("DAO");
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(req.getRid()));
			ps.execute();
			return true;

		} catch (SQLException e) {
			MyLogger.logger.info("DELETE FAIL");
			e.printStackTrace();
		}
		return false;
	}

}
