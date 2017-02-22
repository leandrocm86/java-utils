package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Escritor {
	
	private BufferedWriter writer;
	
	public Escritor(File file) throws FileNotFoundException {
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	}
	
	public Escritor(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public void escreve(String texto) throws IOException {
		writer.write(texto);
	}
	
	public void escreveTudo(ArrayList<StringBuffer> texto) throws IOException {
		for (StringBuffer linha : texto) {
			writer.write(linha + "\r\n");
		}
		this.terminar();
	}
	
	public void terminar() throws IOException {
		writer.close();
	}

}
