package multithreadingdemo.p04locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P1LocksDemo {

	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		Runnable task = () -> {
			account.withdrawImmediately(50);
			account.withdrawWithTimeLimit(50, 4000, TimeUnit.MILLISECONDS);
			account.withdrawHoweverLongItMayTake(50);
		};
		Thread task1 = new Thread(task, "Task 1");
		Thread task2 = new Thread(task, "Task 2");
		task1.start();
		task2.start();
	}
}

class BankAccount {

	private double balance = 100;

	private Lock lock = new ReentrantLock();

	private void withdraw(double amount) {
		if (balance >= amount) {
			System.out.println(Thread.currentThread().getName() + " proceeding to withdraw " + amount + "...");
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " withdrawed " + amount + ". Balance: " + balance);
		} else {
			System.out.println(Thread.currentThread().getName() + " insufficient balance.");
		}
	}

	public void withdrawImmediately(double amount) {
		System.out.println(Thread.currentThread().getName() + " attempting to withdraw immediately " + amount);
		if (lock.tryLock()) {
			try {
				withdraw(amount);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println(
					Thread.currentThread().getName() + " could not accquire lock. Aborted process immediately.");
		}
	}

	public void withdrawWithTimeLimit(double amount, long time, TimeUnit unit) {
		System.out.println(Thread.currentThread().getName() + " attempting to withdraw withing time-limit " + amount);
		try {
			if (lock.tryLock(time, unit)) {
				try {
					withdraw(amount);
				} catch (Exception e) {
					Thread.currentThread().interrupt();
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println(Thread.currentThread().getName() + " could not accquire lock withing time limit.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void withdrawHoweverLongItMayTake(double amount) {
		System.out.println(
				Thread.currentThread().getName() + " attempting to withdraw however long it may take " + amount);
		try {
			lock.lock();
			withdraw(amount);
			lock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
