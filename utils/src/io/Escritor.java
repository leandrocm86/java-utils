package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Escritor {
	
	private final static int LIMITE_BUFFER = 100;
	
	private int escritas = 0;
	
	private BufferedWriter writer;
	
	public Escritor(File file) throws FileNotFoundException {
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	}
	
	public Escritor(File file, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
	}
	
	public Escritor(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public Escritor(String fileName, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
		this(new File(fileName), encoding);
	}
	
	public void escreve(String texto) throws IOException {
		writer.write(texto);
		if (++this.escritas == LIMITE_BUFFER) {
			writer.flush();
			this.escritas = 0;
		}
	}
	
	public void enter() throws IOException {
		this.enter(1);
	}
	
	public void enter(int quantidade) throws IOException {
		for (int i = 0; i < quantidade; i++)
			writer.newLine();
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
