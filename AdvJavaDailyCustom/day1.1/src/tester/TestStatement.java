package tester;

import java.sql.*;
import static utils.DBUtils.openConnection;

//Display all user details (Retrieve)
public class TestStatement {

	public static void main(String[] args) {
		try (// get fixed cn from DriverManager
				Connection conn = openConnection();
				// create empty statement
				Statement st = conn.createStatement();
				// execute query : Method of ST : public RST executeQuery(String sql) throws SE
				ResultSet rst = st.executeQuery("select * from users");) {
			/*
			 * id | first_name | last_name | email | password | reg_amt | reg_date | role
			 */
			// RST processing , cursor : before the 1st row
			while (rst.next())
				// read cols data
				System.out.println("ID " + rst.getInt(1) + " Complete Name " + rst.getString(2) + " " + rst.getString(3)
						+ " reged on " + rst.getDate(7) + " using role : " + rst.getString(8));

		} // rst.close , st.close , cn.close()
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
