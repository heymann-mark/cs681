package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
	private boolean changed = false;
	private List<Observer> observers;

	public Observable() {
		observers = new LinkedList<Observer>();

	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	void deleteObserver(Observer o) {
		observers.remove(o);

	}

	protected void clearChanged() {
		changed = false;
	}

	int countObservers() {
		return observers.size();
	}

	void deleteObservers() {
		observers.clear();

	}

	public boolean hasChanged() {
		return changed;
	}

	void notifyObservers() {
		notifyObservers(null);
	}

	void notifyObservers(Object arg) {
		if (!this.hasChanged())
			return;
		/*
		 * int i = observers.size(); Iterator<Observer> iter = observers.iterator();
		 * while (--i >= 0) ((Observer) iter.next()).update(this, arg);
		 * 
		 * for (Iterator<Observer> iter2 = observers.iterator(); iter2.hasNext();) {
		 * ((Observer) iter2.next()).update(this, arg); }
		 * 
		 * for (ListIterator<Observer> iter3 = observers.listIterator();
		 * iter.hasNext();) { ((Observer) iter3.next()).update(this, arg); }
		 * 
		 * observers.forEach((Observer o) -> o.update(this, arg));
		 * 
		 * for (Observer obs : observers) { obs.update(this, arg); }
		 */
		
		observers.stream().map((observers) -> observers).forEach((Observer o) -> o.update(this));

		clearChanged();

	}

	protected void setChanged() {
		changed = true;
	}

}
