package multithreadingdemo.p4locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class P4ReadWriteLock {

	public static void main(String[] args) {
		ReadableWritableResource resource = new ReadableWritableResource();
		Runnable readTask = () -> {
			for(int i=0; i<5; i++) {
				resource.getValue();
			}
		};
		Runnable writeTask = () -> {
			for(int i=0; i<5; i++) {
				resource.incrementValue();
			}
		};
		Thread t1 = new Thread(readTask, "T1");
		Thread t2 = new Thread(readTask, "T2");
		Thread t3 = new Thread(readTask, "T3" );
		Thread t4 = new Thread(writeTask, "T4");
		Thread t5 = new Thread(writeTask, "T5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class ReadableWritableResource {

	private int count = 0;
	private Lock readLock;
	private Lock writeLock;

	public ReadableWritableResource() {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		this.readLock = lock.readLock();
		this.writeLock = lock.writeLock();
	}

	public void incrementValue() {
		System.out.println(Thread.currentThread().getName()+" requesting write lock...");
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+" acquired write lock...");
			Thread.sleep(100);
			count++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName()+" released write lock...");
			writeLock.unlock();
		}
	}

	public void getValue() {
		System.out.println(Thread.currentThread().getName()+" requesting read lock...");
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+" acquired read lock...");
			System.out.println(Thread.currentThread().getName()+" read value " + count);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally { 
			System.out.println(Thread.currentThread().getName()+" released read lock...");
			readLock.unlock();
		}
	}
}
