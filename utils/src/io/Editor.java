package io;

import estruturas.Lista;
import utils.Str;

public class Editor {
	
	private CharSequence fileName;
	private Lista<Str> linhas;
	
	public Editor(CharSequence fileName) {
		this.fileName = fileName;
		this.linhas = new Leitor(fileName).toList();
	}
	
	public void trocar(CharSequence de, CharSequence para) {
		for (Str linha : linhas) {
			linha.troca(de, para);
		}
	}
	
	public void terminar() {
		new Escritor(this.fileName).escreveTudo(linhas);
	}

}
