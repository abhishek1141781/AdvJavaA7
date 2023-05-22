package tester;

import java.sql.*;

import utils.DBUtils;
//import utils.DBUtils.*;

//Display all user details (Retrieve)
public class TestStatement {

	public static void main(String[] args) {
		try (// get fixed cn from DriverManager ||| The term "fixed" connection is not a technical term in this context but rather suggests that the method provides a consistent and reliable way to obtain a database connection with specific settings.
				Connection conn = DBUtils.openCustomConnection();
				// create empty statement ||| Creates a Statement object for sending SQL statements to the database. SQL statements without parameters are normally executed using Statement objects
				Statement st = conn.createStatement();
				// execute query : Method of ST : public RST executeQuery(String sql) throws SE
				ResultSet rst = st.executeQuery("select * from users");) {
			/*
			 * id | first_name | last_name | email | password | reg_amt | reg_date | role
			 */
			// RST processing , cursor : before the 1st row
			while (rst.next())
				// read cols data
//				yes this is exactly what i was saying, why not use the result set itself to print the results why first store it in a list and then print that list, instead directly print the result set itself
				System.out.println("ID " + rst.getInt(1) + " Complete Name " + rst.getString(2) + " " + rst.getString(3)
						+ " reged on " + rst.getDate(7) + " using role : " + rst.getString(8));

		} // rst.close , st.close , cn.close()
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
