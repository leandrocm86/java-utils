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
