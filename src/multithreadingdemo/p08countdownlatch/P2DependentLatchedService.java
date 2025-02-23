package multithreadingdemo.p08countdownlatch;

import java.util.concurrent.CountDownLatch;

public class P2DependentLatchedService {

}

class DependentLatchedService implements Runnable {

	private String name;

	private CountDownLatch latch;

	public DependentLatchedService(String name, CountDownLatch latch) {
		this.name = name;
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Thread " + name + " started");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " finished");
		latch.countDown();
	}

}