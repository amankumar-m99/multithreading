package multithreadingdemo.p01threadlifecycle;

public class ThreadLifeCycle {

	public static void main(String[] args) {
		System.out.println("Current Thread:"+ Thread.currentThread().getState());
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=1; i<=5; i++) {
					System.out.println(i + ": Hi Geeks!");
					Thread.sleep(300);
				}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(runnable);
		System.out.println(thread.getState());
		thread.start();
		for(int i=1; i<=5; i++) {
//			System.out.println(i + ":" +thread.getState());
			System.out.println(thread.getState());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread.getState());
		System.out.println("Current Thread:"+ Thread.currentThread().getState());
	}
}
