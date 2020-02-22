/*Define your own Observable(class) and Observer(interface) 
 * – DO NOT reuse java.util.Observableand java.util.Observer 
 * – Define Observableas an abstract class. 
 * – Define Observer as a functional interface. 
 * • update()as the only abstract method in that interface. 
 * – Implement all the methods that are available for java.util.Observable 
 * • c.f. Java API doc for expected behaviors/responsibilities of the methods 
 * – Use LinkedListto hold all registered observers. 
 * • c.f. CS680 slides on “ArrayListv.s. LinkedList”
 * 
 * This is the Observer design pattern. In this pattern there
 * is an observerect called the subject, which maintains a list of dependencies.
 * in this project, there are 3 subjects:
 * BondQuoteObservable, StockQuoteObservable, and DJIAQuoteObservable.
 * Each of them has the same 4 dependencies:
 * PieChartObserver, ThreeDObserver, LinecChartObserver,  
 */


package edu.umb.cs.cs681.hw01;

public class Main {
	public static void main(String[] args) {
		StockQuoteObservable observedStock = new StockQuoteObservable("Original Stock Value");
		BondQuoteObservable observedBond = new BondQuoteObservable("Original Bond Value");
		DJIAQuoteObservable observedDow = new DJIAQuoteObservable("Original DJIA Value");
		/*here we use lambda expressions, the alternative would be to 
		
		StockQuoteObservable stock = new StockQuoteObservable();

		stock.addObserver(pieObserver);
		stock.addObserver(tableObserver);
		stock.addObserver(threeDObserver);
		System.out.println("Stock:");
		
		addObserver is an Observable method, designed to add
		an observer object to the observable list, of that 
		particular observable object. So in this case, the
		observable object is the observedStock. 
		
		
		*/
		observedStock.addObserver((observer) -> {
			System.out.println("Line chart is updated with " + observer);
			System.out.println("Stock event happened, updating Line Chart");

		});
		observedStock.addObserver(( observer) -> {
			System.out.println("Pie chart is updated with " + observer);
			System.out.println("Stock event happened, updating Pie Chart");

		});
		observedStock.addObserver( (observer) -> {
			System.out.println("Table chart is updated with " + observer);
			System.out.println("Stock event happened, updating table");

		});
		observedStock.addObserver((observer) -> {
			System.out.println("Three D chart is updated with " + observer);
			System.out.println("Stock event happened, updating 3DChart");
		});

		observedStock.setValue("New Value");
		System.out.println("");
		System.out.println("Bond:");

		

		observedBond.addObserver((observer) -> {
			System.out.println("Line chart is updated with " + observer);
			System.out.println("Bond event happened, updating Line Chart");

		});
		observedBond.addObserver((observer) -> {
			System.out.println("Pie chart is updated with " + observer);
			System.out.println("Bond event happened, updating Pie Chart");

		});
		observedBond.addObserver((observer) -> {
			System.out.println("Table chart is updated with " + observer);
			System.out.println("Bond event happened, updating table");

		});
		observedBond.addObserver((observer) -> {
			System.out.println("Three D chart is updated with " + observer);
			System.out.println("Bond event happened, updating 3DChart");

		});
		observedBond.setValue("New Value");

		System.out.println("");
		System.out.println("DJIA:");

		

		observedDow.addObserver((observer) -> {
			System.out.println("Line chart is updated with " + observer);
			System.out.println("Dow event happened, updating Line Chart");

		});
		observedDow.addObserver((observer) -> {
			System.out.println("Pie chart is updated with " + observer);
			System.out.println("Dow event happened, updating Pie Chart");

		});
		observedDow.addObserver((observer) -> {
			System.out.println("Table chart is updated with " + observer);
			System.out.println("Dow event happened, updating table");

		});
		observedDow.addObserver((observer) -> {
			System.out.println("Three D chart is updated with " + observer);
			System.out.println("Dow event happened, updating 3DChart");

		});

		observedDow.setValue("New Value");

	}

}