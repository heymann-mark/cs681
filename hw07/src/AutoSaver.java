package edu.umb.cs.cs681.hw07;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver extends File implements Runnable {
	private ReentrantLock doneLock = new ReentrantLock();
	private boolean done = false;
	File file = new File();

	@Override
	public void run() {

		while (true) {
			if (done) {
				break;
			}
			file.save();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
