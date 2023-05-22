package dao;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	// state
	private Connection cn;
	private PreparedStatement pst1, pst2;

	public CandidateDaoImpl() throws SQLException {
		// open cn
		cn = openConnection();
		// pst1
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		System.out.println("candidate dao created...");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> list = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return list;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		// set IN param
		pst2.setInt(1, candidateId);
		int rowCount = pst2.executeUpdate();
		if (rowCount == 1)
			return "Incremented the votes...";
		return "Votes updation failed!!!!!";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
		System.out.println("candidate dao cleaned up....");
	}

}
