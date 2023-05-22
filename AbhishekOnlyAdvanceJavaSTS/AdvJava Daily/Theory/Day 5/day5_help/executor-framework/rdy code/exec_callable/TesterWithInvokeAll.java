package exec_callable;

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

public class TesterWithInvokeAll {

	public static void main(String[] args) {
		try {

			// create tasks
			List<PrinterTask> tasks = Arrays.asList(new PrinterTask(1, 10), new PrinterTask(10, 20),
					new PrinterTask(20, 30), new PrinterTask(30, 40));
			System.out.println("creating a thread pool");
			ExecutorService service = Executors.newFixedThreadPool(3);

			System.out.println("invoking all  tasks");
			//Blocking call : Blocks till all tasks are executed
			List<Future<Integer>> invokeAll = service.invokeAll(tasks);
			System.out.println("Getting results");
			invokeAll.forEach(f -> {
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
