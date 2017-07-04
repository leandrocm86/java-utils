package observer;

public class Event {
	
	private CharSequence eventID;
	
	private Object param;
	
	public Event(CharSequence eventID) {
		this(eventID, null);
	}
	
	public Event(CharSequence eventID, Object param) {
		this.eventID = eventID;
		this.param = param;
	}

	public CharSequence getEventID() {
		return eventID;
	}

	public void setEventID(CharSequence eventID) {
		this.eventID = eventID;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
}
