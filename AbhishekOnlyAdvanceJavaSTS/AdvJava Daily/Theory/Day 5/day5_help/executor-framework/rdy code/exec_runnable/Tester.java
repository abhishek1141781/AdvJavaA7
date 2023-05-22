package exec_runnable;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		try  {
			
			// create tasks
			PrinterTask task1 = new PrinterTask( 1,100);
			PrinterTask task2 = new PrinterTask( 100,200);
			PrinterTask task3 = new PrinterTask( 200,300);
			PrinterTask task4 = new PrinterTask( 300,400);
			
			
			//attach tasks to threads : using constructor
			Thread t1=new Thread(task1,"t1");
			t1.start();
			Thread t2=new Thread(task2,"t2");
			t2.start();
			Thread t3=new Thread(task3,"t3");
			t3.start();
			Thread t4=new Thread(task4,"t4");
			t4.start();
			
		
			System.out.println("main waiting for child thrds to finish exec");
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over....");

	}

}
