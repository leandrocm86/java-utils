package observer;

public abstract class SimpleObserver implements Observador {

	public SimpleObserver(CharSequence... events) {
		Events.addObserver(this, events);
	}

}
