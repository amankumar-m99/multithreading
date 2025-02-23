package multithreadingdemo.p11volatileandatomic;

public class P1Volatile {

	public static void main(String[] args) {
		P1SharedResource resource = new P1SharedResource();
		Thread writerThread = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resource.setFlagTrue();
		});
		Thread readerThread = new Thread(() -> {
			resource.printIfFlagIsTrue();
		});
		writerThread.start();
		readerThread.start();
	}
}

class P1SharedResource {

//	private boolean flag = false; // this cause problem due to caching. volatile solves this problem
	private volatile boolean flag = false;

	public void setFlagTrue() {
		System.out.println("writer made the flag true");
		flag = true;
	}

	public void printIfFlagIsTrue() {
		while (!flag) {
			// do nothing
		}
		System.out.println("Flag is true");
	}
}