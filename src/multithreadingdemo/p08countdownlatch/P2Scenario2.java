package multithreadingdemo.p08countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P2Scenario2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int numberOfServices = 3;
		CountDownLatch latch = new CountDownLatch(numberOfServices);
		// latches can also be used with threads. Its not limited to executors
		ExecutorService pool = Executors.newFixedThreadPool(numberOfServices);
		pool.submit(new DependentLatchedService("Service 1", latch));
		pool.submit(new DependentLatchedService("Service 2", latch));
		pool.submit(new DependentLatchedService("Service 3", latch));
		/* Finish all dependent services before starting main service */
		latch.await();
//		latch.await(5, TimeUnit.SECONDS); // timeout method
		System.out.println("All dependent services finished. Starting main service");
		pool.shutdown();
	}
}