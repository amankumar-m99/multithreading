package multithreadingdemo.p02essentialmethods;

public class ThreadDeamon {

	public static void main(String[] args) {
		Thread t1 = new MyThread();
		t1.setName("Non-Daemon");
		Thread t2 = new MyThread();
		t1.setName("Daemon");
		t1.setDaemon(true);
		t1.start();
		t2.start();
		System.out.println("Exiting main thread.");
	}
}

class MyThread extends Thread {

	

	@Override
	public void run() {
		for (;;) {
			System.out.println(Thread.currentThread().getName() + " running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
