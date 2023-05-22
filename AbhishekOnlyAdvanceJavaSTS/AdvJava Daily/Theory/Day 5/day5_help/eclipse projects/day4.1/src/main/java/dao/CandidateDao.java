package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface CandidateDao {
//add a method to fetch all the candidates
	List<Candidate> getAllCandidates() throws SQLException;
	//add a method to increment the votes
	String incrementVotes(int candidateId) throws SQLException;
}
