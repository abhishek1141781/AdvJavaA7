package exec_callable;

import static java.lang.Thread.currentThread;

import java.util.concurrent.Callable;

public class PrinterTask implements Callable<Integer> {
	private int start, end;

	public PrinterTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() {
		System.out.println(currentThread().getName() + " started");
		int sum=0;
		try {
			for (int i = start; i < end; i++) {

				System.out.println(i + " : " + currentThread().getName());
				sum += i;

				Thread.sleep(50);
			}
		} catch (Exception e) {
			System.out.println(currentThread().getName() + " got error " + e);
		}
		System.out.println(currentThread().getName() + " over");
		return sum;
	}

}
