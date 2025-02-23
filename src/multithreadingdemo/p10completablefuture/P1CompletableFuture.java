package multithreadingdemo.p10completablefuture;

import java.util.concurrent.CompletableFuture;

public class P1CompletableFuture {

	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
			System.out.println("Async task with supplier");
			return "ok";
		});
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
			System.out.println("Async task with runnable");
		});
		System.out.println("Main");
	}
}
