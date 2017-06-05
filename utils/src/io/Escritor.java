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
	
	public Escritor(File file) {
		this(file, false);
	}
	
	public Escritor(File file, boolean append) {
		this.file = file;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Escritor(File file, String encoding) {
		this.file = file;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
		}
		catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Escritor(String fileName) {
		this(new File(fileName));
	}
	
	public Escritor(String fileName, boolean append) {
		this(new File(fileName), append);
	}
	
	public Escritor(String fileName, String encoding) {
		this(new File(fileName), encoding);
	}
	
	public void escreve(String texto) {
		try {
			writer.write(texto);
			if (++this.escritas == LIMITE_BUFFER) {
				writer.flush();
				this.escritas = 0;
			}
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void enter() {
		this.enter(1);
	}
	
	public void enter(int quantidade) {
		try {
			for (int i = 0; i < quantidade; i++)
				writer.newLine();
		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void escreveTudoStringBuffer(Lista<StringBuffer> texto) {
		this.escreveTudoStringBuffer(texto, true);
	}
	
	public void escreveTudoStringBuffer(Lista<StringBuffer> texto, boolean terminar) {
		try {
			for (StringBuffer linha : texto) {
				writer.append(linha).append("\r\n");
			}
			if (terminar)
				this.terminar();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void escreveTudo(Lista<Str> texto) throws IOException {
		this.escreveTudo(texto, true);
	}
	
	public void escreveTudo(Lista<Str> texto, boolean terminar) {
		try {
			for (Str linha : texto) {
				writer.write(linha + "\r\n");
			}
			if (terminar)
				this.terminar();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}
	
	public File getFile() {
		return this.file;
	}
	
	public void terminar() {
		try {
			writer.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
