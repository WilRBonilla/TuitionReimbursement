package dev.bonilla.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.bonilla.model.Award;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;
import dev.bonilla.utility.JDBCConnection;
import dev.bonilla.utility.MyLogger;

public class AwardDAOImpl implements AwardDAO {
	RequestDAO rd = new RequestDAOImpl();
	UserLoginDAO ud = new UserLoginDAOImpl();
	static Connection conn = JDBCConnection.getConnection();
	PreparedStatement ps = null;

	@Override
	public Award getAward(int aid) {
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM award WHERE a_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(aid));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Award(rs.getInt("A_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"),
									rs.getInt("A_VALUE"), rs.getDate("A_DATE"), rs.getString("A_NOTES"));
			}
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO GET AWARD");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Award> getAllAwards(int uid) {
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM award WHERE u_id = ?";
		List<Award> awardList = new ArrayList<Award>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				awardList.add(new Award(rs.getInt("A_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"),
						rs.getInt("A_VALUE"), rs.getDate("A_DATE"), rs.getString("A_NOTES")));
			}
			
			return awardList;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO GET AWARDS FOR USER: " + uid);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Award> getAllAwards() {
		MyLogger.logger.info("DAO");
		String sql = "SELECT * FROM award ";
		List<Award> awardList = new ArrayList<Award>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				awardList.add(new Award(rs.getInt("A_ID"), rs.getInt("U_ID"), rs.getInt("E_ID"),
						rs.getInt("A_VALUE"), rs.getDate("A_DATE"), rs.getString("A_NOTES")));
			}
			
			return awardList;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO GET AWARDS");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean createAward(Award award, Request r, UserLogin u) {
		MyLogger.logger.info("DAO");
		String sql = "CALL new_award (?, ?, ?, ?, ?, ?, ?, ?)";
		CallableStatement cs;
//		Request req = rd.getRequest(award.getAid());
//		UserLogin user = ud.getUser(award.getUid());

		try {
			cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(award.getAid()));
			cs.setString(2, Integer.toString(9)); //hard coding success as 9 for now
			cs.setString(3, r.getNotes());
			cs.setString(4, Integer.toString(award.getValue()));

			cs.setString(5, Integer.toString(award.getEid()));
			cs.setString(6, award.getNotes());
			cs.setString(7, Integer.toString(u.getUid()));
			cs.setString(8, Integer.toString(u.getAvailable()));
			
			cs.execute();
			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("DID NOT CREATE AWARD");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAward(Award award) {
		
		
		MyLogger.logger.info("DAO");
		String sql = "UPDATE award SET a_value = ?, a_notes = ?, a_date = SYSDATE WHERE a_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(award.getValue()));
			ps.setString(2, award.getNotes());
			ps.setString(3, Integer.toString(award.getAid()));
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO UPDATE AWARD");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAward(Award award) {
		MyLogger.logger.info("DAO");
		String sql = "DELETE award WHERE a_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(award.getAid()));
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO DELETE AWARD");
			e.printStackTrace();
		}
		return false;
	
	}

}
