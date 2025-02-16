package multithreadingdemo.p7executorfactorial;

public class P1Factorial {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		long start = System.currentTimeMillis();
		for (int num : nums) {
			factorial(num);
		}
		long end = System.currentTimeMillis();
		long withoutThreads = end - start;
		System.out.println("Program without threads finished in " + withoutThreads + " miliseconds");

		Thread[] threads = new Thread[nums.length];
		start = System.currentTimeMillis();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			threads[i] = new Thread(() -> {
				factorial(num);
			});
			threads[i].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		long withThreads = end - start;
		System.out.println("Program with threads finished in " + withThreads + " miliseconds");
		double difference = (withThreads - withoutThreads)/(double)withoutThreads;
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
			e.printStackTrace();
		}
		return fact;
	}

}
