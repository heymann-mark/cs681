package edu.umb.cs.cs681.hw11;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	private AccessCounter access;
	private Path path;
	volatile boolean done = false;
	private ReentrantLock doneLock = new ReentrantLock();

	public RequestHandler(AccessCounter a, Path p) {
		this.access = a;
		this.path = p;
	}

	public void setDone() {
		done = true;
	}

	@Override
	public void run() {

		while (true) {
			doneLock.lock();
			if (done) {
				break;
			}
			doneLock.unlock();
			access.increment(path);
			access.getCount(path);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

	}

}
