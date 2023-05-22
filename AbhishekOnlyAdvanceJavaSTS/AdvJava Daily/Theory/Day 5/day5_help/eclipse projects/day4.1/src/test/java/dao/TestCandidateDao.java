//package dao;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static utils.DBUtils.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import pojos.Candidate;
//
//class TestCandidateDao {
//	private static CandidateDaoImpl dao;
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		openConnection();
//		dao=new CandidateDaoImpl();
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		dao.cleanUp();
//		closeConnection();
//	}
//
//	@Test
//	void testGetAllCandidates()  throws Exception{
//		List<Candidate> list = dao.getAllCandidates();
//		assertEquals(9, list.size());
//	}
//
//}
