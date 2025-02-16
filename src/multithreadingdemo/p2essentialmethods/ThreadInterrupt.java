package multithreadingdemo.p2essentialmethods;

public class ThreadInterrupt {

	public static void main(String[] args) {
		Thread t1 = new InterruptThread();
		t1.start();
		t1.interrupt();
	}
}

class InterruptThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("Run called. Going to sleep");
			Thread.sleep(3000);
			System.out.println("Run finishing. Sleep over");
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted! Run not finished!");
//			e.printStackTrace();
		}
	}
}
