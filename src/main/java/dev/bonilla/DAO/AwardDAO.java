package dev.bonilla.DAO;

import java.util.List;

import dev.bonilla.model.Award;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;

public interface AwardDAO {
	public Award getAward(int aid);
	public List<Award> getAllAwards(int uid);
	public List<Award> getAllAwards();
	public boolean createAward(Award award, Request r, UserLogin u);
	public boolean updateAward(Award award);
	public boolean deleteAward(Award award);
}
