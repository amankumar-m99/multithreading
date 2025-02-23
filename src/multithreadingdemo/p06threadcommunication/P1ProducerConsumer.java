package multithreadingdemo.p06threadcommunication;

public class P1ProducerConsumer {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		Runnable producer = () -> {
			for (int i = 0; i < 10; i++) {
				resource.produce(i);
			}
		};

		Runnable consumer = () -> {
			for (int i = 0; i < 10; i++) {
				resource.consume();
			}
		};

		Thread producer1Thread = new Thread(producer, "Producer");
		Thread consumerThread = new Thread(consumer, "Consumer");
		producer1Thread.start();
		consumerThread.start();
	}
}

class SharedResource {

	private int data;
	private boolean hasData;

	public synchronized void produce(int data) {
		System.out.println(Thread.currentThread().getName() + " called with data " + data);
		while (hasData) {
			try {
				System.out.println(Thread.currentThread().getName() + " resource has data. producer waiting.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName() + " resource not has data. Producing data " + data + " and notifying");
		this.data = data;
		hasData = true;
		notifyAll();
	}

	public synchronized void consume() {
		System.out.println(Thread.currentThread().getName() + " called.");
		while (!hasData) {
			try {
				System.out.println(Thread.currentThread().getName() + " resource not has data. consumer waiting.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName() + " resource has data. Consuming data " + data + " and notifying");
		hasData = false;
		notifyAll();
	}
}
