package observer;

public abstract class SimpleObserver implements Observer {

	public SimpleObserver(CharSequence... events) {
		Events.addObserver(this, events);
	}

}
