import java.awt.Color;

import estruturas.Lista;
import io.Escritor;
import io.Leitor;
import swing.Fonte;
import swing.Widget;
import utils.Str;

public class Main {
	
	private static final String URL = "W:\\Temps\\TEMPer1F_H1\\TEMPer1F_H1\\1.txt";
	
	private static Widget widget;
	private static int temp;
	private static int umid;

	public static void main(String[] args) {
		widget = new Widget("widget");
		widget.setSize(50, 42);
		widget.setFont(new Fonte("Arial", 1, 17));
		widget.setPosition(1540, 1035);
		
		fazerLeitura();
	}
	
	private static void fazerLeitura() {
		Leitor leitor = new Leitor(URL);
		Lista<Str> registros = leitor.toList();
		Str ultimaLinha = registros.ultimo(); // formato: 2017-12-08 00:12:38,30,31,59,79
		temp = Integer.parseInt(ultimaLinha.substring(20, 22)) - 2;
		umid = Integer.parseInt(ultimaLinha.substring(26, 28));
		
		atualizarTexto();
		limparRegistrosAntigos(registros);
		
		new java.util.Timer().schedule(
	        new java.util.TimerTask() {
	            public void run() {
	                fazerLeitura();
	            }
	        }, 
	        300000 // Releitura a cada 5 minutos
		);
	}
	
	private static void atualizarTexto() {
		Str texto = new Str("T: ").appendLn(temp).append("U: ").append(umid);
		widget.setText(texto);
		if (temp >= 30)
			widget.setTextColor(Color.RED);
	}
	
	private static void limparRegistrosAntigos(Lista<Str> registros) {
		if (registros.size() > 50) {
			Escritor escritor = new Escritor(URL);
			escritor.escreveLn(registros.ultimo());
			escritor.terminar();
		}
	}

}
