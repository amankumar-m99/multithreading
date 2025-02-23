package multithreadingdemo.p09cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DependentCyclicBarrierService implements Runnable {

	private String name;

	private CyclicBarrier barrier;

	public DependentCyclicBarrierService(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		System.out.println("Thread " + name + " started");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " waiting at barrier...");
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " finished");
	}

}