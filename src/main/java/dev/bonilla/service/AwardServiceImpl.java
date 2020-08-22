package dev.bonilla.service;

import java.util.List;

import dev.bonilla.DAO.AwardDAO;
import dev.bonilla.DAO.AwardDAOImpl;
import dev.bonilla.model.Award;
import dev.bonilla.model.Request;
import dev.bonilla.model.UserLogin;

public class AwardServiceImpl implements AwardService {
	AwardDAO ad = new AwardDAOImpl();
	
	@Override
	public Award getAward(int aid) {
		return ad.getAward(aid);
	}

	@Override
	public List<Award> getAllAwards(int uid) {
		return ad.getAllAwards(uid);
	}

	@Override
	public List<Award> getAllAwards() {
		return ad.getAllAwards();
	}

	// TODO : The DAO is currently taking care of business logic
	// That should be handled here.
	@Override
	public boolean createAward(Award award, Request r, UserLogin u) {
		return ad.createAward(award, r, u);
	}

	@Override
	public boolean updateAward(Award award) {
		return ad.updateAward(award);
	}

	@Override
	public boolean deleteAward(Award award) {
		return ad.deleteAward(award);
	}

}
