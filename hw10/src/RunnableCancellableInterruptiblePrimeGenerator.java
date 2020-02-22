package edu.umb.cs.threads.primes;

import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator{

	public RunnableCancellableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}
	private boolean done = false;
	private ReentrantLock doneLock = new ReentrantLock();

	
	public void run() {
		generatePrimes();
	}

	public void setDone() {
		doneLock.lock();
		try {
			done = true;
		} finally {
			doneLock.unlock();
		}
	}

	public void generatePrimes() {
		long n = from;
		while (true && n < to) { 
			doneLock.lock();
				if (done) {
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
				}
                doneLock.unlock();
				if (isPrime(n)) {this.primes.add(n);}
				n = n + 1;
				try{
					Thread.sleep(3000); }catch(InterruptedException e){ continue; }// or break; 
			
		}
	}

	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeGenerator gen = new RunnableCancellableInterruptiblePrimeGenerator(1, 10000);
		Thread thread = new Thread(gen);
		thread.start();
		gen.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		gen.setDone();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
