

package edu.umb.cs.cs681.hw21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.concurrent.TimeUnit;
public class RunnableInterruptiblePrimeGenerator 
   extends InterruptiblePrimeGenerator 
   implements Runnable, Callable<List<Long>> {
	
	

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
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		Image img;
		/*
		for each download{
			img = new ImageProxy(…);
			img.draw(); 
			CompletableFuture<ImageImpl> downloadedImage = CompletableFuture<ImageImpl>
			.supplyAsync( ()->{ return img.fetchImage(); }, executor ) .thenAccept( (ImageImpl image)-> image.draw() );
		}
		*/
		
		
		
		for(int completedTaskNum=0;completedTaskNum < taskNum; completedTaskNum++){ 
			 Future<List<Long>> future = null;
				try {
					future = completionService.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 try {
					List<Long> primes1 = future.get();
					for(Long L : primes1) {
						 System.out.print("MMMMMMMMMMMMMMM"+ L.toString());
					 }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //... // do something with pr
			
				
		 }
		
		
		/*
		//RunnableInterruptiblePrimeGenerator r1, r2;
		//r1 = new RunnableInterruptiblePrimeGenerator(1L, 500L);
		//r2 = new RunnableInterruptiblePrimeGenerator(501L, 1000L); 
		 ExecutorService executor = Executors.newFixedThreadPool(2);
		//ExecutorCompletionService<List<Long>> completionService =
			//	new ExecutorCompletionService<>(Executors.newFixedThreadPool(2)); 
		//ArrayList<Future<List<Long>>> futures = new ArrayList<>();
		
		
		//for(int i=0; i<futures.size(); i++){ 
		//	futures.add( completionService.submit( new RunnableInterruptiblePrimeGenerator(1L + (500*i), (i + 1)*500L)));
		//}
		//Future<List<Long>> future = (Future<List<Long>>) executor.submit(r1);
		//future.cancel(true);
		//if(future.isCancelled()){ ... }
		 int taskNum=futures.size();
		 for(int completedTaskNum=0;completedTaskNum < taskNum; completedTaskNum++){ 
			 Future<List<Long>> future = null;
				try {
					future = completionService.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 try {
					List<Long> primes1 = future.get();
					for(Long L : primes1) {
						 System.out.print("MMMMMMMMMMMMMMM"+ L.toString());
					 }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //... // do something with pr
			
				
		 }
				 		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		executor.shutdownNow();
		 Calls interrupt() on each prime gen thread. An 
		 interruption is caught by Thread.interrupted() in 
		 RunnableInterruptiblePrimeGenerator’s run(). 
		try {
			executor.awaitTermination(60L , TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			 TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<Long> list = r1.getPrimes();
		 
		Long r1count = 0L;
		for(Long l : list ){
			++r1count;
		}
		System.out.println("r1 generated " + r1count + "prime numbers");
		List<Long> list2 = r1.getPrimes();
		 
		Long r2count = 0L;
		for(Long l : list ){
			++r2count;
		}
	     System.out.println("r1 generated " + r2count + "prime numbers");
		r1.getPrimes().forEach((Long prime)-> ++r1count)                  ;
		r1.getPrimes().forEach((Long prime)-> System.out.print(prime + ",, ")); 
		System.out.println();
		r2.getPrimes().forEach((Long prime)-> System.out.print(prime + ", ")); 
		 }
		 
		 

		//RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1,100);
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
	*/
	
		 }
	@Override
	public List<Long> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
}
	

