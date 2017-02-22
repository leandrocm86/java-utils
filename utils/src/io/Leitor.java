package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import utils.ArrayList;

public class Leitor {
	
	private BufferedReader reader;
	private String linhaCorrente;
	
	public Leitor(File file) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(file));
	}
	
	public Leitor(String fileName) throws FileNotFoundException {
		this(new File(fileName));
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
}
