package tester;

import java.util.Scanner;

import dao.BankAccountDaoImpl;
import pojos.BankAccount;

public class TestStoredProc {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create acct dao instance
			BankAccountDaoImpl acctDao = new BankAccountDaoImpl();
			System.out.println("Enter src , dest a/c nos n amount");
			System.out.println(acctDao.transfterFunds(sc.nextInt(), sc.nextInt(), sc.nextDouble()));
			acctDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
