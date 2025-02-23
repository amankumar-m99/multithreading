package multithreadingdemo.p07executorsframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class P4ScheduledExecutor {

	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(()->{
			long time = System.currentTimeMillis();
			System.out.println("Task executing at fixed rate at time " + time);
			for(int i=0; i<3; i++) {
				try {
					Thread.sleep(700);
					System.out.println("Task executing at fixed rate at time " + time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Task execution at fixed rate at time " + time + " finished now.");
		}, 2, 3, TimeUnit.SECONDS);

		scheduler.scheduleWithFixedDelay(()->{
			long time = System.currentTimeMillis();
			System.out.println("Task executing at fixed delay at time " + time);
			for(int i=0; i<5; i++) {
				try {
					Thread.sleep(700);
					System.out.println("Task executing at fixed delay at time " + time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Task execution at fixed delay at time " + time + " finished now.");
		}, 2, 3, TimeUnit.SECONDS);

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduler.shutdown();
	}
}
