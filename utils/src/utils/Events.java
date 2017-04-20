package utils;

import java.util.HashMap;

public class Events {
	
	private static HashMap<String, ArrayList<Observer>> monitoramento = new HashMap<>();
	
	public static void addObserver(Observer observer, String... events) {
		for (String event : events) {
			String eventId = extractEventId(event);
			ArrayList<Observer> list = monitoramento.get(eventId);
			if (list == null) {
				list = new ArrayList<>();
				monitoramento.put(eventId, list);
			}
			list.add(observer);
		}
	}
	
	public static void notify(String event) {
		ArrayList<Observer> list = monitoramento.get(extractEventId(event));
		if (list != null)
			for (Observer o : list)
				o.update(event);
	}
	
	private static String extractEventId(String event) {
		return Strings.substringAte(event, ":", false); // Usando dois pontos como separador.
	}

}
