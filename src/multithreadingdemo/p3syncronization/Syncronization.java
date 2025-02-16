package multithreadingdemo.p3syncronization;

public class Syncronization {

	public static void main(String[] args) {
		Counter unSyncCounter = new UnsynchronizedCounter();
		Thread t1 = new Thread(new CounterThread(unSyncCounter));
		Thread t2 = new Thread(new CounterThread(unSyncCounter));
		Counter syncCounter = new SynchronizedCounter();
		Thread t3 = new Thread(new CounterThread(syncCounter));
		Thread t4 = new Thread(new CounterThread(syncCounter));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(unSyncCounter.getValue());
		System.out.println(syncCounter.getValue());
	}
}

class CounterThread implements Runnable {

	private Counter counter;

	public CounterThread(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			this.counter.increment();
		}
	}
}

interface Counter {
	void increment();

	void addOne();

	void reset();

	int getValue();
}

class UnsynchronizedCounter implements Counter {

	private int count = 0;

	@Override
	public void increment() {
		count++;
	}

	@Override
	public void addOne() {
		count++;
	}

	@Override
	public void reset() {
		count = 0;
	}

	@Override
	public int getValue() {
		return count;
	}
}

class SynchronizedCounter implements Counter {

	private int count = 0;

	@Override
	public synchronized void increment() {
		count++;
	}

	@Override
	public void addOne() {
		synchronized (this) {
			count++;
		}
	}

	@Override
	public synchronized void reset() {
		count = 0;
	}

	@Override
	public synchronized int getValue() {
		return count;
	}
}
