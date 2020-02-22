package edu.umb.cs.cs681.hw01;

public class StockQuoteObservable extends Observable {
	private String watchedValue;

	public StockQuoteObservable(String value) {
		watchedValue = value;

	}

	public void setValue(String value) {
		if (watchedValue != value) {
			System.out.println("Stock value has changed to " + value);
			watchedValue = value;
			setChanged();
			notifyObservers(value);

		}
	}
}
