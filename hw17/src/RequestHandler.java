package edu.umb.cs.cs681.hw17;

import java.nio.file.Path;
import java.nio.file.Paths;

import edu.umb.cs.cs681.hw17.AccessCounter;

public class RequestHandler implements Runnable{
	private AccessCounter access;
	private Path path;
	volatile boolean done = false;
	
	public RequestHandler(AccessCounter a, Path p) {
		this.access = a;
		this.path =p;
	}
public void setDone() {done = true;}

	@Override
	public void run() {		
		
		while(!done) {
		access.increment(path);
		access.getCount(path);
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e) {}}
	}
	
}
