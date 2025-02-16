package multithreadingdemo.p5deadlock;

public class P1Deadlock {
	
	public static void main(String[] args) {
		Pen pen = new Pen();
		Paper paper = new Paper();
		Thread t1 = new Thread(new Task1(pen, paper), "t1");
		Thread t2 = new Thread(new Task2(pen, paper), "t2");
		t1.start();
		t2.start();
	}
}

class Pen{

	public synchronized void grabPaperAndWrite(Paper paper) {
		System.out.println(Thread.currentThread().getName() + " has pen and needs paper to write.");
		paper.write();
	}
	public synchronized void write() {
		System.out.println(Thread.currentThread().getName() + " finished writing.");
	}
}

class Paper{

	public synchronized void grabPenAndWrite(Pen pen) {
		System.out.println(Thread.currentThread().getName() + " has paper and needs pen to write.");
		pen.write();
	}
	public synchronized void write() {
		System.out.println(Thread.currentThread().getName() + " finished writing.");
	}
}

class Task1 implements Runnable{

	private Pen pen;
	private Paper paper;

	public Task1(Pen pen, Paper paper) {
		this.pen = pen;
		this.paper = paper;
	}
	@Override
	public void run() {
		pen.grabPaperAndWrite(paper);
	}
}

class Task2 implements Runnable{
	
	private Pen pen;
	private Paper paper;
	
	public Task2(Pen pen, Paper paper) {
		this.pen = pen;
		this.paper = paper;
	}
	@Override
	public void run() {
		paper.grabPenAndWrite(pen);
	}
}