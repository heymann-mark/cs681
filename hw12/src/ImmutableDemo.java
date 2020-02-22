package edu.umb.cs.cs681.hw12;

public class ImmutableDemo implements Runnable{
	volatile boolean done = false;
	public void setDone() {done = true;}
	@Override
	public void run() {
		
        Customer customer = new Customer( new Address( "26 Maple Street", "Boston", "MA", "02134") ); 
		customer.getAddress(); 
		customer.setAddress( new Address ("Beacon", "Boston", "MA", "02156") );	
		customer.setAddress( customer.getAddress().change( "Commonwealth", "Newton", "MA", "02122"));
	}

}
