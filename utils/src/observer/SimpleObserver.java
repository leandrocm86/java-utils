package observer;

public abstract class SimpleObserver implements Observer {

	public SimpleObserver(String... events) {
		Events.addObserver(this, events);
	}

}
