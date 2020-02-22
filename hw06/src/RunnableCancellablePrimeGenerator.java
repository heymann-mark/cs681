/* In its original version, RunnableCancellablePrimeGenerator inherits the 
data field "done" and two methods (setDone() and generatePrimes()) from 
CancellablePrimeGenerator. RunnableCancellablePrimeGenerator does not 
re-define/override those inherited methods but simply use them as they 
are. This is not good in terms of thread safety because those methods 
use the data field "done" and multiple threads access the data field 
through the methods (the main thread calls setDone() and an extra thread 
calls generatePrimes()). Race conditions can occur as a result.
*/

package edu.umb.cs.threads.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends CancellablePrimeGenerator implements Runnable {

	private boolean done = false;
	private ReentrantLock doneLock = new ReentrantLock();

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

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
			try {
				if (done) {
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
				}

				if (isPrime(n)) {
					this.primes.add(n);
				}
				n = n + 1;
			} finally {
				doneLock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1, 10000);
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
