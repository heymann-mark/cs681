package edu.umb.cs.cs681.hw01;

public class DJIAQuoteObservable extends Observable {
	private String watchedValue;

	public DJIAQuoteObservable(String value) {
		watchedValue = value;

	}

	public void setValue(String value) {
		if (watchedValue != value) {
			System.out.println("DJIA value has changed to " + value);
			watchedValue = value;
			setChanged();
			notifyObservers(value);

		}
	}
}
