package multithreadingdemo.p07executorsframework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class P2NewFixedThreadPool {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		long start = System.currentTimeMillis();
		for (int num : nums) {
			factorial(num);
		}
		long end = System.currentTimeMillis();
		long withoutThreads = end - start;
		System.out.println("Program without threads finished in " + withoutThreads + " miliseconds");

		ExecutorService executor = Executors.newFixedThreadPool(3);
		start = System.currentTimeMillis();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			executor.submit(()->{
				factorial(num);
			});
		}

		Future<Long> future = executor.submit(()->{
			return factorial(11);
		});
		try {
			System.out.println("Future returned " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();
//		executor.shutdownNow();
		try {
			while(!executor.awaitTermination(1, TimeUnit.SECONDS)) {}
			System.out.println("Finished all tasks.");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Executor interrupted");
		}
		end = System.currentTimeMillis();
		long withThreads = end - start;
		System.out.println("Program with threads finished in " + withThreads + " miliseconds");
		double difference = (withoutThreads - withThreads)/(double)withoutThreads;
		System.out.println("Difference: " + (difference*100));
	}

	private static long factorial(int i) {
		long fact = 1;
		while (i > 0) {
			fact *= i;
			i--;
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
//			e.printStackTrace();
		}
		return fact;
	}

}
