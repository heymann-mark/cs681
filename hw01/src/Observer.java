
package edu.umb.cs.cs681.hw01;
/*
 * Observer is a functional interface
 * A functional interface has a single 
 * abstract method. The reason it's just a single method
 * is so when 
 */

@FunctionalInterface
public interface Observer {

	public void update( Observer observer);

}
