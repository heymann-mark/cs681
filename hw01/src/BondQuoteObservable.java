package edu.umb.cs.cs681.hw01;

public class BondQuoteObservable extends Observable {
	private String watchedValue;

	public BondQuoteObservable(String value) {
		watchedValue = value;

	}

	public void setValue(String value) {
		if (watchedValue != value) {
			System.out.println("Bond value has changed to " + value);
			watchedValue = value;
			setChanged();
			notifyObservers(value);

		}
	}

}