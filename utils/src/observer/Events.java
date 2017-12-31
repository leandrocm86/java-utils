package observer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import estruturas.Lista;

public class Events {
	
	private static HashMap<CharSequence, List<Observador>> monitoramento = new HashMap<>();
	
	public static void addObserver(Observador observer, CharSequence... events) {
		for (CharSequence eventId : events) {
			List<Observador> list = monitoramento.get(eventId);
			if (list == null) {
				list = Collections.synchronizedList(new Lista<>());
				monitoramento.put(eventId, list);
			}
			list.add(observer);
		}
	}
	
	public static void notify(Evento event) {
		List<Observador> list = monitoramento.get(event.getEventID());
		if (list != null) {
			Iterator<Observador> iterator = list.iterator();
			while(iterator.hasNext())
				iterator.next().ouvir(event);
		}
	}
	
	public static void notify(CharSequence eventID) {
		Evento event = new Evento(eventID);
		List<Observador> list = monitoramento.get(eventID);
		if (list != null) {
			Iterator<Observador> iterator = list.iterator();
			while(iterator.hasNext())
				iterator.next().ouvir(event);
		}
	}
}
