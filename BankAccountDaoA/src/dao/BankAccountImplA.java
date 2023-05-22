package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import utils.DButilsA;

public class BankAccountImplA implements BankAccountDaoIFA{

//	state:
	private Connection cn;
	private CallableStatement cst1;
	
	
//	default constructor
	public BankAccountImplA() throws SQLException {
//		open cn
		cn=DButilsA.openCustomConnection();
//		cst1
		cst1=cn.prepareCall("{call transfer_funds_proc(?,?,?,?,?)}");
//		register OUT params
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);
		System.out.println("Bank Account dao created");
	}
	
	@Override
	public String transferFunds(int sAccNo, int dAccNo, double amt) throws SQLException {
//		set IN params
		cst1.setInt(1, sAccNo);
		cst1.setInt(2, dAccNo);
		cst1.setDouble(3, amt);
		
//		execute: stored procedure
		cst1.execute();
		
//		how is cst1 getting value from the table
		return "Updated src balance: "+cst1.getDouble(4) + "dest bal: "+cst1.getDouble(5);
	}

//	cleanup the statemnet and the connecttion
	public void cleanUp() throws SQLException {
		if(cst1!=null)
			cst1.close();
		DButilsA.closeCustomConnection();
		
		System.out.println("Account Dao cleaned up!!!");
	}
}
