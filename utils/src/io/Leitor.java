package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import estruturas.Lista;
import utils.Str;

public class Leitor {
	
	public static boolean logar;
	
	private BufferedReader reader;
	private String linhaCorrente;
	private long inicio;
	
	public Leitor(File file) {
		this(file, null);
	}
	
	public Leitor(File file, String charset) {
		try {
			if (logar) {
				inicio = System.currentTimeMillis();
				Log.msgLn("Lendo arquivo de tamanho (bytes): " + file.length());
			}
			
			FileInputStream fis = new FileInputStream(file);
			InputStream in = file.getName().endsWith(".gz") ? new GZIPInputStream(fis) : fis;
			
			if (charset != null) {
				reader = new BufferedReader(new InputStreamReader(in, charset));
			}
			else {
				reader = new BufferedReader(new InputStreamReader(in));
			}
		}
		catch(IOException e) {
			throw new IllegalArgumentException("Excecao lancada ao tentar ler arquivo!", e);
		}
	}
	
	public Leitor(CharSequence fileName) {
		this(new File(fileName.toString()));
	}
	
	public Leitor(CharSequence fileName, String charset) {
		this(new File(fileName.toString()), charset);
	}
	
	/**
	 * Retorna a proxima linha do arquivo, ou nulo quando terminar.
	 */
	public String lerLinha() {
		try {
			this.linhaCorrente = this.reader.readLine();
			if (this.linhaCorrente == null) {
				this.terminar();
			}
			return this.linhaCorrente;
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Retorna a primeira linha no arquivo que contenha uma dada String, ou nulo se nao encontrar.
	 */
	public String procurarLinha(String texto) {
		do {
			this.lerLinha();
			if (this.linhaCorrente.contains(texto)) {
				this.terminar();
				return this.linhaCorrente;
			}
		}
		while(this.linhaCorrente != null);
	
	return null;
	}
	
	public Lista<StringBuffer> toListStringBuffer() {
		return this.toListStringBuffer(false);
	}
	
	public Lista<StringBuffer> toListStringBuffer(boolean linkedList) {
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
	
	public Lista<Str> toList() {
		return this.toList(Lista.Tipo.ARRAY);
	}
	
	public Lista<Str> toList(Lista.Tipo tipo) {
		Lista<Str> lista = new Lista<>(tipo);
		String proximaLinha;
		while(true) {
			proximaLinha = this.lerLinha();
			if (proximaLinha != null)
				lista.add(new Str(proximaLinha));
			else
				break;
		}
		return lista;
	}
	
	public void terminar() {
		try {
			this.reader.close();
			if (logar)
				Log.msgLn("Leitura encerrada em " + (System.currentTimeMillis() - inicio) + "ms.");
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
