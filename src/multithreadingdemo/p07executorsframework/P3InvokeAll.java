package multithreadingdemo.p07executorsframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class P3InvokeAll {

	public static void main(String[] args) {
		Callable<Integer> callable1 = () -> {
			System.out.println("Task 1");
			Thread.sleep(1000);
			return 1;
		};
		Callable<Integer> callable2 = () -> {
			Thread.sleep(1000);
			System.out.println("Task 2");
			return 2;
		};
		Callable<Integer> callable3 = () -> {
			Thread.sleep(1000);
			System.out.println("Task 3");
			return 3;
		};

		List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			List<Future<Integer>> futures1 = executor.invokeAll(list);
			System.out.println("invoked is blocking all tasks comleted");
			List<Future<Integer>> futures2 = executor.invokeAll(list, 1, TimeUnit.SECONDS);
			System.out.println("Complete tasks as many as possible with in the givrn time limit");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
