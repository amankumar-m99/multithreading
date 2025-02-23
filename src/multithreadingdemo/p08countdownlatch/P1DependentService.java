package multithreadingdemo.p08countdownlatch;

public class P1DependentService {

}

class DependentService implements Runnable {

	private String name;

	public DependentService(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Thread " + name + " started");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " finished");
	}

}