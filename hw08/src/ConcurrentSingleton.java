package edu.umb.cs.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
private ConcurrentSingleton() {};
private static ConcurrentSingleton instance = null;
private static ReentrantLock instanceLock = new ReentrantLock();



 public static ConcurrentSingleton getInstance() {
 
	instanceLock.lock();
	try {
		if (instance == null) {
			instance = new ConcurrentSingleton();
		}
		return instance;
	} finally {
		instanceLock.unlock();
	}
}

 

}
