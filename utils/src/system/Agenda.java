package system;

public class Agenda {
	
	public static void agendar(Tarefa tarefa, long tempo) {
		new java.util.Timer().schedule(
	        new java.util.TimerTask() {
	            public void run() {
	                tarefa.executar();
	            }
	        }, 
	        tempo
		);
	}
	
	public static void agendarCiclo(Tarefa tarefa, long periodo) {
		new java.util.Timer().schedule(
	        new java.util.TimerTask() {
	            public void run() {
	                tarefa.executar();
	            }
	        },
	        periodo,
	        periodo
		);
	}

}
