package multithreadingdemo.p08countdownlatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class P1Scenario1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Future<?> future1 = pool.submit(new DependentService("Service 1"));
		Future<?> future2 = pool.submit(new DependentService("Service 2"));
		Future<?> future3 = pool.submit(new DependentService("Service 3"));
		future1.get();
		future2.get();
		future3.get();
		/* Finish all dependent services before starting main service */
		/* if number of services is more then this way is not scalable.*/
		/* invokeAll is also not scalable.*/
		System.out.println("All dependent services finished. Starting main service");
		pool.shutdown();
	}
}