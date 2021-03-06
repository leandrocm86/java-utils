package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import estruturas.Lista;
import utils.Str;

public class Escritor {
	
	public static boolean logar = false;
	public static String URL_TESTES = null;  
	
	private int limiteBuffer = 1000;
	
	private int escritas = 0;
	private BufferedWriter writer;
	private File file;
	private long inicio;
	
	
	public Escritor(File file) {
		this(file, null, false);
	}
	
	public Escritor(File file, String encoding, boolean append) {
		if (logar) {
			inicio = System.currentTimeMillis();
			Log.msgLn("Escrevendo em arquivo de tamanho (bytes): " + file.length());
		}
		this.file = file;
		try {
			FileOutputStream fos = new FileOutputStream(file, append);
			OutputStreamWriter osw = encoding == null ? new OutputStreamWriter(fos) : new OutputStreamWriter(fos, encoding);
			writer = new BufferedWriter(osw);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Escritor(File file, String encoding) {
		this(file, encoding, false);
	}
	
	public Escritor(File file, boolean append) {
		this(file, null, append);
	}
	
	public Escritor(CharSequence fileName) {
		this(fileName, false);
	}
	
	public Escritor(CharSequence fileName, boolean append) {
		this(new File(URL_TESTES == null ? fileName.toString() : URL_TESTES), append);
	}
	
	public Escritor(CharSequence fileName, String encoding) {
		this(fileName, encoding, false);
	}
	
	public Escritor(CharSequence fileName, String encoding, boolean append) {
		this(new File(URL_TESTES == null ? fileName.toString() : URL_TESTES), encoding, append);
	}
	
	public void escreve(CharSequence texto) {
		try {
			writer.append(texto);
			if (++this.escritas == limiteBuffer) {
				writer.flush();
				this.escritas = 0;
			}
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void escreveLn(CharSequence texto) {
		this.escreve(texto);
		this.enter();
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
				writer.append(linha).append(Str.LN);
			}
			if (terminar)
				this.terminar();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void escreveTudo(Collection<? extends CharSequence> texto) {
		this.escreveTudo(texto, true);
	}
	
	public void escreveTudo(Collection<? extends CharSequence> texto, boolean terminar) {
		try {
			for (CharSequence linha : texto) {
				writer.append(linha.toString() + Str.LN);
			}
			if (terminar)
				this.terminar();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void escreveTudo(String fileName, Collection<? extends CharSequence> texto) {
		Escritor escritor = new Escritor(fileName);
		escritor.escreveTudo(texto);
	}
	
	public static void escreveMapa(String fileName, Map<? extends CharSequence, ? extends CharSequence> mapa) {
		Escritor escritor = new Escritor(fileName);
		for (Entry<? extends CharSequence, ? extends CharSequence> entry : mapa.entrySet()) {
			escritor.escreveLn(entry.getKey() + "=" + entry.getValue());
		}
		escritor.terminar();
	}
	
	public static void limpaArquivo(String fileName) {
		Escritor escritor = new Escritor(fileName);
		escritor.terminar();
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
			if (logar) {
				Log.msgLn("Terminada escrita em arquivo de tamanho (bytes): " + file.length() + ". Tempo: "
					+ (System.currentTimeMillis() - inicio) + "ms.");
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public int getTotalLinhasEscritas() {
		return this.escritas;
	}

}
