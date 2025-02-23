package multithreadingdemo.p09cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P2CyclicBarrier {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int parties = 3;
//		CyclicBarrier barrier = new CyclicBarrier(parties);
		CyclicBarrier barrier = new CyclicBarrier(parties, ()->{
			System.out.println("All services at barrier. Barrier tripped.");
			System.out.println("This thread is executed by last thread reaching at the barrier.");
		});
		ExecutorService pool = Executors.newFixedThreadPool(parties);
		pool.submit(new DependentCyclicBarrierService("Service 1", barrier));
		pool.submit(new DependentCyclicBarrierService("Service 2", barrier));
		pool.submit(new DependentCyclicBarrierService("Service 3", barrier));
		System.out.println(
				"Barrier doesn't blocks the main thread. So this line gets printed without waiting fo dependent services to get finished.");
		pool.shutdown();
	}
}
