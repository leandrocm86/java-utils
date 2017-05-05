package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import utils.Lista;

public class Leitor {
	
	private BufferedReader reader;
	private String linhaCorrente;
	
	public Leitor(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			if (file.getName().endsWith(".gz")) {
				reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(fis)));
			}
			else {
				reader = new BufferedReader(new InputStreamReader(fis));
			}
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Excecao lancada ao tentar ler arquivo!", e);
		}
	}
	
	public Leitor(File file, String charset) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		if (file.getName().endsWith(".gz")) {
			reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(fis), charset));
		}
		else {
			reader = new BufferedReader(new InputStreamReader(fis, charset));
		}
	}
	
	public Leitor(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public Leitor(String fileName, String charset) throws IOException {
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
	
	public Lista<StringBuffer> toListStringBuffer() throws IOException {
		return this.toListStringBuffer(false);
	}
	
	public Lista<StringBuffer> toListStringBuffer(boolean linkedList) throws IOException {
		Lista<StringBuffer> lista = new Lista<>(Lista.Tipo.LINKED);
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
	
	public Lista<String> toList() throws IOException {
		return this.toList(false);
	}
	
	public Lista<String> toList(boolean linkedList) throws IOException {
		Lista<String> lista = new Lista<>(Lista.Tipo.LINKED);
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
