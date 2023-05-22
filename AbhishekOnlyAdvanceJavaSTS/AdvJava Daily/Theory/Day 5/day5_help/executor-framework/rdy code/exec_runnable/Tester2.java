package exec_runnable;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester2 {

	public static void main(String[] args) {
		try {

			// create tasks
			List<PrinterTask> tasks = Arrays.asList(new PrinterTask(1, 10), new PrinterTask(10, 20),
					new PrinterTask(20, 30), new PrinterTask(30, 400));
			System.out.println("creating a thread pool");
			ExecutorService service = Executors.newFixedThreadPool(3);
			System.out.println("executing tasks");
			tasks.forEach(service::execute);
			System.out.println("doing something else in the meantime ....."+Thread.currentThread().getName());
			System.out.println("requesting service shutdown: after tasks are completed");
			service.shutdown();
			//waits till all tasks are over or timeout (whatever happens first!)
			service.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("Service terminated "+service.isTerminated());

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over....");

	}

}
