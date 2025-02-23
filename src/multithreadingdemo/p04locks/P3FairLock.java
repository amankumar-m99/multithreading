package multithreadingdemo.p04locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P3FairLock {

	public static void main(String[] args) {
		SharedResource sharedResource = new SharedResource();
		Runnable task = () -> {
			sharedResource.accessResource();
		};
		Thread t1 = new Thread(task, "T1");
		Thread t2 = new Thread(task, "T2");
		Thread t3 = new Thread(task, "T3");
		Thread t4 = new Thread(task, "T4");
		Thread t5 = new Thread(task, "T5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class SharedResource {

	boolean  fair = true;
	private Lock lock = new ReentrantLock(fair);

	public void accessResource() {
		System.out.println(Thread.currentThread().getName() + " requesting lock...");
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " acquired lock.");
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		} finally {
			System.out.println(Thread.currentThread().getName() + " released lock.");
			lock.unlock();
		}
	}
}
