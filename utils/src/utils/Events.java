package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Events {
	
	private static HashMap<String, List<Observer>> monitoramento = new HashMap<>();
	
	public static void addObserver(Observer observer, String... events) {
		for (String event : events) {
			String eventId = extractEventId(event);
			List<Observer> list = monitoramento.get(eventId);
			if (list == null) {
				list = Collections.synchronizedList(new Lista<>());
				monitoramento.put(eventId, list);
			}
			list.add(observer);
		}
	}
	
	public static void notify(String event) {
		List<Observer> list = monitoramento.get(extractEventId(event));
		if (list != null) {
			Iterator<Observer> iterator = list.iterator();
			while(iterator.hasNext())
				iterator.next().update(event);
		}
	}
	
	private static String extractEventId(String event) {
		return Strings.substringAte(event, ":", false); // Usando dois pontos como separador.
	}

}
