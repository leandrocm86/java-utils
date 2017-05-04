package observer;

public class Event {
	
	private String eventID;
	
	private Object param;
	
	public Event(String eventID) {
		this(eventID, null);
	}
	
	public Event(String eventID, Object param) {
		this.eventID = eventID;
		this.param = param;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
}
