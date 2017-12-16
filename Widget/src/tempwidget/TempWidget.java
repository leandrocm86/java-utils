package tempwidget;
import java.awt.Color;

import estruturas.Lista;
import io.Escritor;
import io.Leitor;
import swing.Widget;
import system.Agenda;
import utils.Str;

public class TempWidget {
	
	private static final String URL = "W:\\Temps\\TEMPer1F_H1\\TEMPer1F_H1\\1.txt";
	
	private static Widget tempWidget;
	private static Widget umidWidget;
	private static int temp;
	private static int umid;

	public static void main(String[] args) {
		umidWidget = new Widget("Umidade");
		tempWidget = new Widget("Temperatura");
		Agenda.agendar(()->fazerLeitura(), 10000); // Primeira leitura espera 10 segundos
		Agenda.agendarCiclo(()->fazerLeitura(), 300000); // Releitura a cada 5 minutos
	}
	
	private static void fazerLeitura() {
		Leitor leitor = new Leitor(URL);
		Lista<Str> registros = leitor.toList();
		Str ultimaLinha = registros.ultimo(); // formato: 2017-12-08 00:12:38,30,31,59,79
		umid = Integer.parseInt(ultimaLinha.substring(26, 28));
		temp = Integer.parseInt(ultimaLinha.substring(20, 22)) - 2;
		
		atualizarTexto();
		limparRegistrosAntigos(registros);
	}
	
	private static void atualizarTexto() {
		umidWidget.setText(umid + "");
		tempWidget.setText(temp + "");
		if (temp >= 30)
			tempWidget.setTextColor(Color.RED);
		else
			tempWidget.setTextColor(Color.WHITE);
		umidWidget.updateIcon();
		tempWidget.updateIcon();
	}
	
	private static void limparRegistrosAntigos(Lista<Str> registros) {
		if (registros.size() > 50) {
			Escritor escritor = new Escritor(URL);
			escritor.escreveLn(registros.ultimo());
			escritor.terminar();
		}
	}

}
