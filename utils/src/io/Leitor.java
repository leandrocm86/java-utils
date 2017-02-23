package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import utils.ArrayList;

public class Leitor {
	
	private BufferedReader reader;
	private String linhaCorrente;
	
	public Leitor(File file) throws FileNotFoundException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	}
	
	public Leitor(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
	}
	
	public Leitor(String fileName) throws FileNotFoundException {
		this(new File(fileName));
	}
	
	public Leitor(String fileName, String charset) throws FileNotFoundException, UnsupportedEncodingException {
		this(new File(fileName), charset);
	}
	
	/**
	 * Retorna a proxima linha do arquivo, ou nulo quando terminar.
	 */
	public String lerLinha() throws IOException {
		this.linhaCorrente = this.reader.readLine();
		if (this.linhaCorrente == null) {
			this.reader.close();
		}
		return this.linhaCorrente;
	}
	
	/**
	 * Retorna a primeira linha no arquivo que contenha uma dada String, ou nulo se nao encontrar.
	 */
	public String procurarLinha(String texto) throws IOException {
		do {
			this.lerLinha();
			if (this.linhaCorrente.contains(texto)) {
				this.reader.close();
				return this.linhaCorrente;
			}
		}
		while(this.linhaCorrente != null);
		
		return null;
	}
	
	public ArrayList<StringBuffer> toList() throws IOException {
		ArrayList<StringBuffer> lista = new ArrayList<>();
		String proximaLinha;
		while(true) {
			proximaLinha = this.lerLinha();
			if (proximaLinha != null)
				lista.add(new StringBuffer(proximaLinha));
			else
				break;
		}
		return lista;
	}
	
	public ArrayList<String> toStringList() throws IOException {
		ArrayList<String> lista = new ArrayList<>();
		String proximaLinha;
		while(true) {
			proximaLinha = this.lerLinha();
			if (proximaLinha != null)
				lista.add(proximaLinha);
			else
				break;
		}
		return lista;
	}
}
