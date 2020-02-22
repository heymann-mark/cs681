package edu.umb.cs.cs681.hw08;

public class Runner implements Runnable{

	@Override
	public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
		
	}

	public static void main(String[] args) {
		
		Runner singleton1 = new Runner();
		Runner singleton2 = new Runner();
		Runner singleton3 = new Runner();
		Runner singleton4 = new Runner();
		Runner singleton5 = new Runner();
		
		Thread thread1 = new Thread(singleton1);
		Thread thread2 = new Thread(singleton2);
		Thread thread3 = new Thread(singleton3);
		Thread thread4 = new Thread(singleton4);
		Thread thread5 = new Thread(singleton5);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		
	}
}
