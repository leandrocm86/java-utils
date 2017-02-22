package utils;

import java.util.HashMap;

public class Events {
	
	private static HashMap<String, ArrayList<Observer>> monitoramento = new HashMap<>();
	
	public static void addObserver(Observer observer, String... events) {
		for (String event : events) {
			ArrayList<Observer> list = monitoramento.get(event);
			if (list == null) {
				list = new ArrayList<>();
				monitoramento.put(event, list);
			}
			list.add(observer);
		}
	}
	
	public static void notify(String event) {
		ArrayList<Observer> list = monitoramento.get(event);
		if (list != null)
			for (Observer o : list)
				o.update(event);
	}

}
