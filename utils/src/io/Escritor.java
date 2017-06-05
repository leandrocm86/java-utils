package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import utils.Lista;
import utils.Str;

public class Escritor {
	
	private final static int LIMITE_BUFFER = 100;
	
	private int escritas = 0;
	
	private BufferedWriter writer;
	
	private File file;
	
	public Escritor(File file) throws FileNotFoundException {
		this.file = file;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	}
	
	public Escritor(File file, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
		this.file = file;
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
	
	public void escreveTudoStringBuffer(Lista<StringBuffer> texto) throws IOException {
		this.escreveTudoStringBuffer(texto, true);
	}
	
	public void escreveTudoStringBuffer(Lista<StringBuffer> texto, boolean terminar) throws IOException {
		for (StringBuffer linha : texto) {
			writer.append(linha).append("\r\n");
		}
		if (terminar)
			this.terminar();
	}
	
	public void escreveTudo(Lista<Str> texto) throws IOException {
		this.escreveTudo(texto, true);
	}
	
	public void escreveTudo(Lista<Str> texto, boolean terminar) throws IOException {
		for (Str linha : texto) {
			writer.write(linha + "\r\n");
		}
		if (terminar)
			this.terminar();
	}
	
	public File getFile() {
		return this.file;
	}
	
	public void terminar() throws IOException {
		writer.close();
	}

}
