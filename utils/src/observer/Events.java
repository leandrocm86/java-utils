package observer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import estruturas.Lista;

public class Events {
	
	private static HashMap<String, List<Observer>> monitoramento = new HashMap<>();
	
	public static void addObserver(Observer observer, String... events) {
		for (String eventId : events) {
			List<Observer> list = monitoramento.get(eventId);
			if (list == null) {
				list = Collections.synchronizedList(new Lista<>());
				monitoramento.put(eventId, list);
			}
			list.add(observer);
		}
	}
	
	public static void notify(Event event) {
		List<Observer> list = monitoramento.get(event.getEventID());
		if (list != null) {
			Iterator<Observer> iterator = list.iterator();
			while(iterator.hasNext())
				iterator.next().update(event);
		}
	}
	
	public static void notify(String eventID) {
		Event event = new Event(eventID);
		List<Observer> list = monitoramento.get(eventID);
		if (list != null) {
			Iterator<Observer> iterator = list.iterator();
			while(iterator.hasNext())
				iterator.next().update(event);
		}
	}
}
