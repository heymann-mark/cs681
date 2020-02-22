package edu.umb.cs.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton{
	private static Singleton instance = null;
	private static ReentrantLock instanceLock = new ReentrantLock();

	private Singleton() {};

	public static Singleton getInstance() {
			if (instance == null) {
				instance = new Singleton();
			}
			return instance;
	}
	
	public static void main(String[] args) {
//not thread safe anyways because
		
		
	}

}
