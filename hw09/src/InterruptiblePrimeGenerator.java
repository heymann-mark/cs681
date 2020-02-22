package edu.umb.cs.threads.primes;

public class InterruptiblePrimeGenerator extends PrimeGenerator{
	
	public InterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
			
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if Thread.interrupted()==true
			if(Thread.interrupted()){
				System.out.println("Stopped generating prime numbers due to a thread interruption.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}	
	public static void main(String[] args) {
		InterruptiblePrimeGenerator gen = new InterruptiblePrimeGenerator(1,100);
		gen.generatePrimes();
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	
}
}
