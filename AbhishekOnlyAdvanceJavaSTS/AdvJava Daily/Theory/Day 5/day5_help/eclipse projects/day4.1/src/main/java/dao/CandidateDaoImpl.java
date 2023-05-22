package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.getConnection;

import pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	// state
	private Connection cn;
	private PreparedStatement pst1, pst2;

	// def ctor : invoked by Servlet
	public CandidateDaoImpl() throws SQLException {
		// get cn from db utils
		cn = getConnection();
		// pst1 : listing all candidates
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		System.out.println("candidate dao created ...");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		// exec Query --RST
		try (ResultSet rst = pst1.executeQuery()) {
			// int candidateId, String name, String politicalParty, int votes
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		// set IN param
		pst2.setInt(1, candidateId);
		// exec update : DML
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "Votes updated successfully!";
		return "Updating votes  failed!!!!!!!!!!!!!!!!!";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		System.out.println("candidate dao cleaned up !");

	}

}
