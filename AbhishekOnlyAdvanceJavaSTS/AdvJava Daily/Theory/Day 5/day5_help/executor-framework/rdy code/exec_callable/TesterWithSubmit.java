package exec_callable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TesterWithSubmit {

	public static void main(String[] args) throws Exception{
		try {

			// create tasks
			List<PrinterTask> tasks = Arrays.asList(new PrinterTask(1, 10), new PrinterTask(10, 20),
					new PrinterTask(20, 30), new PrinterTask(30, 40));
			System.out.println("creating a thread pool");
			ExecutorService service = Executors.newFixedThreadPool(2);
			List<Future<Integer>> futureList = new ArrayList<>();
			tasks.forEach(task -> futureList.add(service.submit(task)));
			System.out.println("Submitted tasks");
			System.out.println("main thrd doing something else.....");
			Thread.sleep(1000);
			System.out.println("Now getting results");
			futureList.forEach(f -> {
				try {
					System.out.println("Result " + f.get());
				} catch (Exception e) {
					System.out.println("err in thread " + Thread.currentThread().getName() + " " + e);

				}
			});
			System.out.println("requesting service shutdown: after tasks are completed");
			service.shutdown();
			System.out.println("Service terminated " + service.isTerminated());

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over....");

	}

}
