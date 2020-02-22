package edu.umb.cs.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 {
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 account;
	volatile boolean done = true;

	private ReentrantLock doneLock = new ReentrantLock();

	public void setDone() {
		
		doneLock.lock();
		done = false;
		doneLock.unlock();
	}

	public ThreadSafeBankAccount2() {
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account = this;

	}

	public void deposit(double amount) {

		lock.lock();
		System.out.println("deposit Lock obtained");
		System.out.println(Thread.currentThread().getId() + " (d): current balance: " + balance);
		while (balance >= 300) {
			try {
				System.out.println(Thread.currentThread().getId() + " (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
				System.out.println(lock.isLocked());
				if (balance < 300) {
					balance += amount;
					System.out.println(Thread.currentThread().getId() + " (d): new balance: " + balance);
					sufficientFundsCondition.signalAll();
				}
			} catch (InterruptedException e) {System.out.println(" deposit thread interupted");
				break;
			}

			break;
		}
		if (balance < 300) {
			if(!Thread.interrupted()){
			balance += amount;
			
			sufficientFundsCondition.signalAll();}
		}else {
		System.out.println(Thread.currentThread().getId() + " (d): thread terminated " );}
		lock.unlock();
		System.out.println("deposit Lock released");

	}

	public void withdraw(double amount) {
		lock.lock();
		System.out.println(" withdraw Lock obtained");
		System.out.println(Thread.currentThread().getId() + " (w): current balance: " + balance);
		while (balance <= 0) {
			try {
				System.out.println(Thread.currentThread().getId() + " (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
				if (balance > 0) {
					balance -= amount;
					System.out.println(Thread.currentThread().getId() + " (w): new balance: " + balance);
					belowUpperLimitFundsCondition.signalAll();
				}
			} catch (InterruptedException e) {
				System.out.println("Withdraw thread interrupted");
				break;
			} 
		}

		if (balance > 0) {
			if(!Thread.interrupted()){
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + " (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();}
		}

		lock.unlock();
		System.out.println("withdraw Lock released");

	}

	public static void main(String[] args) throws InterruptedException {
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		Thread depositThreads[] = new Thread[5];
		for (int i = 0; i < 5; i++) {
			depositThreads[i] = new Thread(bankAccount.new DepositRunnable());	
			depositThreads[i].start();
		}
		
		Thread withdrawThread = new Thread(bankAccount.new WithdrawRunnable());
		withdrawThread.start();
		Thread.sleep(20);
		bankAccount.setDone();
		
		withdrawThread.interrupt();
		
		for (Thread depositThread : depositThreads) {
			depositThread.interrupt();
			
		}
		
		
		
		
		Thread.sleep(2000);
		
	}

	private class DepositRunnable implements Runnable {
		public void run() {
			while (done) {
				try {

					for (int i = 0; i < 100; i++) {

						account.deposit(100);

						Thread.sleep(2);
					}

				} catch (InterruptedException exception) {
				
				}
			}System.out.println("DepositRunnable is done");
		}
	}

	private class WithdrawRunnable implements Runnable {
		public void run() {

			while (done) {

				try {
					for (int i = 0; i < 100; i++) {
						account.withdraw(100);

						Thread.sleep(2);
					}
				} catch (InterruptedException exception) {
				}
			}System.out.println("WithdrawRunnable is done");

		}
	}
}
