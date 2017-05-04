package utils;

import observer.Events;
import observer.Observer;

public abstract class SimpleObserver implements Observer {

	public SimpleObserver(String... events) {
		Events.addObserver(this, events);
	}

}
