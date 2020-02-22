package edu.umb.cs.cs681.hw11;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

	private HashMap<java.nio.file.Path, Integer> map = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock slock = new ReentrantLock();
	private static AccessCounter instance = null;

	String uri = "src/a.html";
	Path path = Paths.get(uri);

	private AccessCounter() {
	};

	public static AccessCounter getInstance() {
		slock.lock();

		if (instance == null)
			instance = new AccessCounter();

		slock.unlock();
		return instance;

	}

	public void increment(Path path) {
		Integer count = 0;
		// regular non static lock
		try {
			lock.lock();
			count = map.get(path);
			if (count == null)
				map.put(path, 1);
			else
				map.put(path, count + 1);
		} finally {
			lock.unlock();
		}
	}

	public int getCount(Path path) {
		// same regular non static lock
		Integer count = 0;
		lock.lock();
		try {
			count = map.get(path);
			if (count == null)
				count = 0;
		} finally {
			System.out.println(path + " accessed " + count + " times");
			lock.unlock();
		}
		return count;
	}

}
