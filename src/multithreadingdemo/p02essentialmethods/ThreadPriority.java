package multithreadingdemo.p02essentialmethods;

public class ThreadPriority {

	public static void main(String[] args) {
		Thread t1 = new PriorityThread("L");
		t1.setPriority(Thread.MIN_PRIORITY);
		Thread t2 = new PriorityThread("M");
		t2.setPriority(Thread.NORM_PRIORITY);
		Thread t3 = new PriorityThread("H");
		t3.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
	}
}

class PriorityThread extends Thread {

	public PriorityThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		int max = 10;
		for (int i = 0; i < max; i++) {
			String format = String.format("Thread:%s, Priority:%s, count: %d", Thread.currentThread().getName(),
					Thread.currentThread().getPriority(), i);
			System.out.println(format);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
