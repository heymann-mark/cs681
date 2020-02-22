

package edu.umb.cs.threads.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableInterruptiblePrimeGenerator 
   extends InterruptiblePrimeGenerator 
   implements Runnable {
	
	

	public RunnableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	private final ReentrantLock lock = new ReentrantLock();
    
	public ReentrantLock getLock() {return lock;}
	
	public void generatePrimes() {
		for (long n = from; n <= to; n++){ 
			lock.lock();

				if (Thread.interrupted()) {
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
				}
				lock.unlock();
				if (isPrime(n)) {this.primes.add(n);}}} 
				
	
	public void run(){
		generatePrimes();
	}
	
	public static void main(String[] args) {
		RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1,100);
		Thread aThread = new Thread(gen);
		aThread.start();
		gen.getLock().lock();
		aThread.interrupt();
		gen.getLock().unlock();
		try {
			aThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
