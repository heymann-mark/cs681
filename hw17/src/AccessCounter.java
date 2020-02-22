package edu.umb.cs.cs681.hw17;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.umb.cs.cs681.hw17.AccessCounter;
import edu.umb.cs.cs681.hw17.RequestHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessCounter {

	ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap<Path, AtomicInteger>();
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
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
		AtomicInteger count = new AtomicInteger();
		map.putIfAbsent(path, count);
		//map.get(path).incrementAndGet();
		System.out.println(path + "accessed " + map.get(path).incrementAndGet()  + " times");
	}

	public AtomicInteger getCount(Path path) {
		AtomicInteger count = new AtomicInteger();
		 count = map.get(path);
		return count;
	}

	public static void main(String[] args) throws InterruptedException {
		Path path1 = Paths.get("src/a.html");
		AccessCounter access = AccessCounter.getInstance();
		RequestHandler request = new RequestHandler(access, path1);

		Thread t1 = new Thread(request);
		Thread t2 = new Thread(request);
		Thread t3 = new Thread(request);
		Thread t4 = new Thread(request);
		Thread t5 = new Thread(request);
		Thread t6 = new Thread(request);
		Thread t7 = new Thread(request);
		Thread t8 = new Thread(request);
		Thread t9 = new Thread(request);
		Thread t10 = new Thread(request);
		Thread t11 = new Thread(request);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e) {}
		request.setDone();

		t1.interrupt();// step 2
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		t7.interrupt();
		t8.interrupt();
		t9.interrupt();
		t10.interrupt();
		t11.interrupt();

		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		t8.join();
		t9.join();
		t10.join();
		t11.join();

	}

}
