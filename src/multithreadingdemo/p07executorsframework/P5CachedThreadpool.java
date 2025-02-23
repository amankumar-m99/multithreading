package multithreadingdemo.p07executorsframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P5CachedThreadpool {

	public static void main(String[] args) {
		ExecutorService scheduler = Executors.newCachedThreadPool();
	}
}
