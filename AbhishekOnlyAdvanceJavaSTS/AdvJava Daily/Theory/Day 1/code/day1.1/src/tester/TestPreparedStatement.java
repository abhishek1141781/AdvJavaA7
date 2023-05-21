package tester;
/*
 * Display complete name , reg date , reg amount for all 
 * users reged between 2 dates (Retrieve with IN params)
 */
import java.sql.*;
import java.util.Scanner;

//import static utils.DBUtils.openConnection;
import utils.DBUtils;


public class TestPreparedStatement {

	public static void main(String[] args) {
		try (// sc
				Scanner sc = new Scanner(System.in);
				// cn
				Connection cn = DBUtils.openCustomConnection();
				// pst Connection API : public PST prepareStatement(String sql) throws SE
				PreparedStatement pst = cn.prepareStatement(
						"select first_name,last_name,reg_date,reg_amt from users where reg_date between ? and ?");) {
			   System.out.println("Enter begin n end date");
			   //set IN params
			   pst.setDate(1, Date.valueOf(sc.next()));
			   pst.setDate(2, Date.valueOf(sc.next()));
			   //exec query : RST executeQuery() throws SE
			   try(ResultSet rst=pst.executeQuery())
			   {
				   while(rst.next())
					   System.out.println("Name "+rst.getString(1)+" "+rst.getString(2)
					   +" reg date "+rst.getDate(3)+" paid reg amount "+rst.getDouble(4));
			   }//rst.close

		} //pst , cn sc : close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
