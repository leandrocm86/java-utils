package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import estruturas.Lista;
import estruturas.Lista.Tipo;
import utils.Str;

public class Leitor {
	
	public static boolean logar;
	public static String URL_TESTES = null;
	
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
			InputStream in = file.getName().endsWith(".gz") ? new GZIPInputStream(fis) : file.getName().endsWith(".zip") ? new ZipInputStream(fis) : fis;
			if (in instanceof ZipInputStream)
				((ZipInputStream) in).getNextEntry(); // Se o zip tiver mais de um arquivo, soh vamos ler esse primeiro.
			
			if (charset != null) {
				reader = new BufferedReader(new InputStreamReader(in, charset));
			}
			else {
				reader = new BufferedReader(new InputStreamReader(in));
			}
		}
		catch(IOException e) {
			throw new IllegalArgumentException("Excecao lancada ao tentar ler arquivo! " + e.getMessage(), e);
		}
	}
	
	public Leitor(CharSequence fileName) {
		this(new File(URL_TESTES == null ? fileName.toString() : URL_TESTES));
	}
	
	public Leitor(CharSequence fileName, String charset) {
		this(new File(URL_TESTES == null ? fileName.toString() : URL_TESTES), charset);
	}
	
	/**
	 * Retorna a proxima linha do arquivo, ou nulo quando terminar.
	 */
	public Str lerLinha() {
		try {
			this.linhaCorrente = this.reader.readLine();
			if (this.linhaCorrente == null) {
				this.terminar();
				return null;
			}
			return new Str(this.linhaCorrente);
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
	
	public Lista<Str> toList() {
		return this.toList(Lista.Tipo.ARRAY);
	}
	
	public Lista<Str> toList(Lista.Tipo tipo) {
		Lista<Str> lista = new Lista<>(tipo);
		Str proximaLinha;
		while(true) {
			proximaLinha = this.lerLinha();
			if (proximaLinha != null)
				lista.add(proximaLinha);
			else
				break;
		}
		return lista;
	}
	
	public Str toStr() {
		Str retorno = new Str();
		Str proximaLinha;
		while(true) {
			proximaLinha = this.lerLinha();
			if (proximaLinha != null)
				retorno.append(proximaLinha + Str.LN);
			else
				break;
		}
		return retorno;
	}
	
	public static Lista<Str> toList(String nomeArquivo) {
		return toList(nomeArquivo, Tipo.ARRAY);
	}
	
	public static Lista<Str> toList(String nomeArquivo, Lista.Tipo tipo) {
		Leitor leitor = new Leitor(nomeArquivo);
		return leitor.toList(tipo);
	}
	
	public static Str toStr(String nomeArquivo) {
		Leitor leitor = new Leitor(nomeArquivo);
		return leitor.toStr();
	}
	
	public static HashMap<Str, Str> toMap(String nomeArquivo) {
		return toMap(nomeArquivo, false);
	}
	
	/**
	 * Retorna o conteudo do arquivo em um mapa, onde cada chave foi separada do seu valor pelo caractere '='.
	 * O parametro 'properties' indica se o arquivo eh do tipo de configuracao, que trata comentarios e espacos.
	 */
	public static HashMap<Str, Str> toMap(String nomeArquivo, boolean properties) {
		HashMap<Str, Str> mapa = new HashMap<Str, Str>();
		Lista<Str> linhas = toList(nomeArquivo);
		for (Str linha : linhas) {
			if (properties) {
				int indexComentario = linha.indexOf('#');
				if (indexComentario > -1) {
					linha.val(linha.substring(0, indexComentario));
				}
			}
			Str[] corte = linha.corta("=");
			if (corte.length > 1) {
				if (properties) {
					corte[0] = corte[0].trim();
					corte[1] = corte[1].trim();
				}
				mapa.put(corte[0], corte[1]);
			}
		}
		return mapa;
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
