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
	private PreparedStatement pst1;

	public CandidateDaoImpl() throws SQLException {
		// open cn
		cn = openConnection();
		// pst1
		pst1 = cn.prepareStatement("select * from candidates");
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

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("candidate dao cleaned up....");
	}

}
