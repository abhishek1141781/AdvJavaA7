package exec_runnable;

import static java.lang.Thread.currentThread;

public class PrinterTask implements Runnable {
	private int start, end;

	public PrinterTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		System.out.println(currentThread().getName() + " started");
		try {
			for (int i = start; i < end; i++) {

				System.out.println(i + " : " + currentThread().getName());

			//	Thread.sleep(50);
			}
		} catch (Exception e) {
			System.out.println(currentThread().getName() + " got error " + e);
		}
		System.out.println(currentThread().getName() + " over");
	}

}
