package multithreadingdemo.p4locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P2ReentrantLockDeadLockHandle {

	public static void main(String[] args) {
		ReentrantLockDemo lockingDemo = new ReentrantLockDemo();
		Runnable task = () -> {
			lockingDemo.outerMethod();
		};
		Thread t1 = new Thread(task);
		t1.start();
	}
}

class ReentrantLockDemo {

	private Lock lock = new ReentrantLock();

	public void outerMethod() {
		System.out.println("Outer method called");
		lock.lock();
		System.out.println("Lock acquired by outer method");
		innerMethod();
		System.out.println("Releasing lock by outer method");
		lock.unlock();
	}

	private void innerMethod() {
		System.out.println("Inner method called");
		lock.lock(); // reentrant lock bypasses the deadlock here
		System.out.println("Lock acquired by inner method");
		System.out.println("Releasing lock by inner method");
		lock.unlock();
	}
}