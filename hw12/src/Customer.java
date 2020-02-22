package edu.umb.cs.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	private ReentrantLock lock = new ReentrantLock();
	private Address address; 
	public Customer(Address addr){
		lock.lock();
		try {
		address = addr;}finally {lock.unlock();} }
	
	public void  setAddress(Address addr){
		lock.lock();
		try {
		address = addr;
		System.out.println("Address has been changed to " +address.toString());
		}finally {lock.unlock();}
		} 
	public Address getAddress(){
		lock.lock();
		try {System.out.println("Current Address: " +address.toString());
		return address;
		}finally {lock.unlock();}
		}  
	
	
	public static void main(String[] args) throws InterruptedException {
		
		 
		ImmutableDemo demo = new ImmutableDemo();
		Thread t1 = new Thread(demo);
		Thread t2 = new Thread(demo);
		Thread t3 = new Thread(demo);
		Thread t4 = new Thread(demo);
		Thread t5 = new Thread(demo);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		demo.setDone();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
	}
	
}
