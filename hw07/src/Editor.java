package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor extends File implements Runnable {
	private ReentrantLock doneLock = new ReentrantLock();
	private boolean done = false;
	File file = new File();

	@Override
	public void run() {
		while (true) {
			doneLock.lock();
			try {
				if (done) {
					System.out.println("...");
					break;
				}
				file.change();
				file.save();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				doneLock.unlock();
			}
		}
	}

	public void setDone() {
		doneLock.lock();
		try {
			done = true;
		} finally {
			doneLock.unlock();
		}
	}

}
