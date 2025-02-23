package multithreadingdemo.p11volatileandatomic;

import java.util.concurrent.atomic.AtomicInteger;

public class P2Atomic {

	public static void main(String[] args) {
		P2SharedResource resource = new P2SharedResource();
		Thread t1 = new Thread(() -> {
			for(int i=0; i <5000; i++) {
				resource.increment();
			}
		});
		Thread t2 = new Thread(() -> {
			for(int i=0; i <5000; i++) {
				resource.increment();
			}
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(resource.getCount());
	}
}

class P2SharedResource {

//	private volatile int count = 0; // volatile won't solve this problem
	private AtomicInteger count = new AtomicInteger(0);

	public void increment() {
//		count++;
		count.incrementAndGet();
	}

	public int getCount() {
//		return count;
		return count.intValue();
	}
}