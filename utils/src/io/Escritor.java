package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import estruturas.Lista;
import utils.Str;

public class Escritor {
	
	private int limiteBuffer = 100;
	
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
	
	public Escritor(CharSequence fileName) {
		this(new File(fileName.toString()));
	}
	
	public Escritor(CharSequence fileName, boolean append) {
		this(new File(fileName.toString()), append);
	}
	
	public Escritor(CharSequence fileName, String encoding) {
		this(new File(fileName.toString()), encoding);
	}
	
	public void escreve(CharSequence texto) {
		try {
			writer.write(texto.toString());
			if (++this.escritas == limiteBuffer) {
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
	
	public void escreveTudo(Collection<Str> texto) throws IOException {
		this.escreveTudo(texto, true);
	}
	
	public void escreveTudo(Collection<Str> texto, boolean terminar) {
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
	
	public int getLimiteBuffer() {
		return limiteBuffer;
	}

	public void setLimiteBuffer(int limiteBuffer) {
		this.limiteBuffer = limiteBuffer;
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
