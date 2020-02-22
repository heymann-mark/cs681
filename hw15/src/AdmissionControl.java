package edu.umb.cs.cs681.hw15;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {//
	private AtomicInteger currentVisitors;// his says =0
	private volatile boolean done = false;
	private ReentrantLock lock;// his
	private Condition sufficientVisitorsCondition;
	private AdmissionControl control;

	public AdmissionControl() {
		lock = new ReentrantLock();
		sufficientVisitorsCondition = lock.newCondition();
		currentVisitors = new AtomicInteger();
		control = this;
	}

	public void setDone() {
		done = !done;
	}

	public void enter() {
		lock.lock();System.out.println("Lock obtained");
		try {
			while (currentVisitors.intValue() >= 5) {
				System.out.println("Too many visitors. Please wait for a while!");
				sufficientVisitorsCondition.await();
			}
			currentVisitors.getAndIncrement();
		} catch (InterruptedException exception) {
			
		} finally {
			lock.unlock();System.out.println("Lock released");
		}

	}

	public void exit() {
		lock.lock();
		System.out.println("Lock obtained");
		try {

			currentVisitors.getAndDecrement();
			sufficientVisitorsCondition.signalAll();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public int countCurrentVisitors() {
		lock.lock();
		try {
			return currentVisitors.intValue();
		} finally {
			lock.unlock();
		}

	}

	public class EntranceHandler implements Runnable {

		public void run() {
			while (!done) {
				control.enter();
			}
		}
	}

	public class ExitHandler implements Runnable {

		public void run() {
			while (!done) {
				control.exit();
			}
		}
	}

	public class MonitorHandler implements Runnable {

		public void run() {
			control.countCurrentVisitors();
		}
	}
}
