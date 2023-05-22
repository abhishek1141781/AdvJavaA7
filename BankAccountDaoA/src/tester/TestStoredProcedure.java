package tester;

import java.sql.SQLException;
import java.util.Scanner;

import dao.BankAccountDaoImplA;

public class TestStoredProcedure {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
//			creating Dao instance
			BankAccountDaoImplA bankDao = new BankAccountDaoImplA();
//			read details to carry out fund transfer method
			System.out.println("Enter the src, dest a/c nos n amount");
			System.out.println(bankDao.transferFunds(sc.nextInt(), sc.nextInt(), sc.nextDouble()));
			bankDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}