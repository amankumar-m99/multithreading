package multithreadingdemo.p2essentialmethods;

public class ThreadYield {

	public static void main(String[] args) {
		Thread t1 = new YeildThread("T1");
		Thread t2 = new YeildThread("T2");
		Thread t3 = new YeildThread("T3");
		t1.start();
		t2.start();
		t3.start();
	}
}

class YeildThread extends Thread {

	public YeildThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		int max = 10;
		for (int i = 0; i < max; i++) {
			System.out.println(Thread.currentThread().getName() + " running");
			Thread.yield();
		}
	}
}
