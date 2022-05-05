package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Str implements Comparable<CharSequence>, CharSequence, Objeto {
	
	public static final String LN = System.getProperty("line.separator");
	
	public static final String SEM_CASE = "sem_case";
	//public static final String SEM_CASE_E_ACENTO = "sem_case_acento";
	
//	private static final Array<Character> conjuntoC = new Array<>('c', 'ç');
//	private static final Array<Character> conjuntoA = new Array<>('a', 'á', 'à', 'ã', 'â');
//	private static final Array<Character> conjuntoE = new Array<>('e', 'é', 'ê');
//	private static final Array<Character> conjuntoI = new Array<>('i', 'í');
//	private static final Array<Character> conjuntoO = new Array<>('o', 'ó', 'õ', 'ô');
//	private static final Array<Character> conjuntoU = new Array<>('u', 'ú');
	
	
	private String val;
	
	public Str() {
		this.val = "";
	}
	
	/**
	 * Cria uma Str formatada, substituindo as ocorrências de '--' pelas demais Strings passadas de parametro. 
	 */
	public Str(CharSequence val, Object... args) {
		this(val);
		int trocas = 0;
		while (trocas < args.length) {
			this.trocaPrimeiro("--", args[trocas].toString());
			trocas++;
		}
	}
	
	public Str(Object object) {
		if (object != null)
			this.val = object.toString();
		else this.val = "";
	}
	
	public Str toUTF8() {
		this.val = new String(this.val.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		return this;
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		return val.subSequence(start, end);
	}
	
	public char	charAt(int index) {
		return val.charAt(index);
	}
	public int compareTo(CharSequence anotherString) {
		return val.compareTo(anotherString.toString());
	}
	public int compareToIgnoreCase(CharSequence str) {
		return val.compareToIgnoreCase(str.toString());
	}
	public boolean contains(CharSequence s) {
		return val.contains(s.toString());
	}
	public boolean endsWith(CharSequence suffix) {
		return val.endsWith(suffix.toString());
	}
	public boolean equals(String s) {
		return val.equals(s);
	}
	public boolean equals(Object anObject) {
		if (anObject == null || val == null)
			return false;
		if (anObject instanceof Str)
			return val.equals(((Str) anObject).val());
		else if (anObject instanceof String)
			return val.equals(anObject);
		else return val.equals(anObject.toString());
	}
	public boolean equalsIgnoreCase(CharSequence anotherString) {
		return val.equalsIgnoreCase(anotherString.toString());
	}
	public int hashCode() {
		return val.hashCode();
	}
	public int indexOf(int ch) {
		return val.indexOf(ch);
	}
	public int indexOf(int ch, int fromIndex) {
		return val.indexOf(ch, fromIndex);
	}
	public int indexOf(CharSequence str) {
		return val.indexOf(str.toString());
	}
	public int indexOf(CharSequence str, int fromIndex) {
		return val.indexOf(str.toString(), fromIndex);
	}
	public boolean vazia() {
		return val == null || val.trim().equals("");
	}
	public static boolean vazia(CharSequence str) {
		return str == null || str.toString().trim().length() == 0;
	}
	public int lastIndexOf(int ch) {
		return val.lastIndexOf(ch);
	}
	public int lastIndexOf(int ch, int fromIndex) {
		return val.lastIndexOf(ch, fromIndex);
	}
	public int lastIndexOf(CharSequence str) {
		return val.lastIndexOf(str.toString());
	}
	public int lastIndexOf(CharSequence str, int fromIndex) {
		return val.lastIndexOf(str.toString(), fromIndex);
	}
	public int length() {
		return val.length();
	}
	public boolean matches(CharSequence regex) {
		return val.matches(regex.toString());
	}
	public String replace(char oldChar, char newChar) {
		return val.replace(oldChar, newChar);
	}
	public String replace(CharSequence target, CharSequence replacement) {
		return val.replace(target, replacement);
	}
	public Str replaceAll(CharSequence regex, CharSequence replacement) {
		return new Str(val.replaceAll(regex.toString(), replacement.toString()));
	}
	public String replaceFirst(CharSequence regex, CharSequence replacement) {
		return val.replaceFirst(regex.toString(), replacement.toString());
	}
	public String[] split(CharSequence regex) {
		return val.split(regex.toString());
	}
	public String[] split(CharSequence regex, int limit) {
		return val.split(regex.toString(), limit);
	}
	public boolean startsWith(CharSequence prefix) {
		return val.startsWith(prefix.toString());
	}
	public boolean startsWith(CharSequence prefix, int toffset) {
		return val.startsWith(prefix.toString(), toffset);
	}
	public String substring(int beginIndex) {
		return val.substring(beginIndex);
	}
	public String substring(int beginIndex, int endIndex) {
		return val.substring(beginIndex, endIndex);
	}
	public String toLowerCase() {
		return val.toLowerCase();
	}
	public String toString() {
		return val.toString();
	}
	public String toUpperCase() {
		return val.toUpperCase();
	}
	public Str reduzEspacos() {
		StringBuilder sb = new StringBuilder();
		boolean espacoFoiUltimoCaracter = false;
		for (char c : this.val.toCharArray()) {
			if (c == ' ') {
				if (espacoFoiUltimoCaracter)
					continue;
				else
					espacoFoiUltimoCaracter = true;
			}
			else {
				espacoFoiUltimoCaracter = false;
			}
			sb.append(c);
		}
		return new Str(sb.toString());
	}
	public Str trim() {
		return new Str(val.trim());
	}
	static String valueOf(boolean b) {
		return String.valueOf(b);
	}
	static String valueOf(char c) {
		return String.valueOf(c);
	}
	static String valueOf(double d) {
		return String.valueOf(d);
	}
	static String valueOf(float f) {
		return String.valueOf(f);
	}
	static String valueOf(int i) {
		return String.valueOf(i);
	}
	static String valueOf(long l) {
		return String.valueOf(l);
	}
	
	// Metodos customizados
	public String val() {
		return this.val;
	}
	
	public void val(CharSequence novoValor) {
		this.val = novoValor.toString();
	}
	
	public boolean naoVazia() {
		return !this.vazia();
	}
	
	/**
	 * Opcoes:
	 * Str.SEM_CASE - Ignora diferencas entre maiusculas/minusculas
	 * Str.SEM_CASE_E_ACENTO - Ignora diferencas entre maisculas e acentos.
	 */
	public boolean comecaCom(CharSequence texto, String opcao) {
		if (texto.length() > this.val.length())
			return false;
		
		switch(opcao) {
			case SEM_CASE:
				return val.toLowerCase().startsWith(texto.toString().toLowerCase());
			/*case SEM_CASE_E_ACENTO: {
				Str str1 = val.length() >= texto.length() ? this.ate(texto.length()) : this;
				Str str2 = val.length() >= texto.length() ? new Str(texto) : new Str(texto).ate(val.length());
				return str1.comparar(str2, SEM_CASE_E_ACENTO) == 0;
			}*/
			default: return this.startsWith(texto);
		}
	}
	
	public int comparar(CharSequence texto, String opcao) {
		switch(opcao) {
			case SEM_CASE: return this.compareToIgnoreCase(texto);
			/*case SEM_CASE_E_ACENTO: { CORRIGIR
				if (texto.length() != val.length())
					return val.length() - texto.length();
				String valMinusculo = val.toLowerCase();
				String textoMinusculo = texto.toString().toLowerCase();
				for (int i = 0; i < val.length(); i++) {
					if (textoMinusculo.charAt(i) == valMinusculo.charAt(i))
						continue;
					else {
						if (this.mesmoConjunto(textoMinusculo.charAt(i), valMinusculo.charAt(i))) {
							continue;
						}
						else
							return valMinusculo.charAt(i) - textoMinusculo.charAt(i);
					}
				}
				return 0;
			}*/
			default: return this.compareTo(texto);
		}
	}
	
//	private boolean mesmoConjunto(char a, char b) {
//		if (conjuntoA.contem(a))
//			return conjuntoA.contem(b);
//		if (conjuntoE.contem(a))
//			return conjuntoE.contem(b);
//		if (conjuntoI.contem(a))
//			return conjuntoI.contem(b);
//		if (conjuntoO.contem(a))
//			return conjuntoO.contem(b);
//		if (conjuntoU.contem(a))
//			return conjuntoU.contem(b);
//		if (conjuntoC.contem(a))
//			return conjuntoC.contem(b);
//		return false;
//	}
	
	public Character characterAt(int index) {
		return Character.valueOf(val.charAt(index));
	}
	
	public Str minusculo() {
		return this.minusculo(false);
	}
	
	public Str minusculo(boolean copia) {
		if (copia)
			return new Str(val.toLowerCase());
		else {
			val = val.toLowerCase();
			return this;
		}
	}
	
	/**
	 * Faz um trim, reduz todos os espaços em branco e devolve o split.
	 */
	public Str[] corta() {
		return this.trim().reduzEspacos().corta(" ");
	}
	
	/**
	 * Faz o split usando regex.
	 */
	public Str[] corta(CharSequence regex) {
		String[] split = val.split(regex.toString());
		Str[] corte = new Str[split.length];
		for (int i = 0; i < split.length; i++)
			corte[i] = new Str(split[i]);
		return corte;
	}
	
	/**
	 * Apenas retorna uma copia concatenada. 
	 */
	public Str concat(Object object) {
		return new Str(this.val + object.toString());
	}
	
	/**
	 * Retorna uma copia deste objeto, apendando um ou mais objetos passados como parametro.
	 * Usa o separador definido no primeiro argumento.
	 */
	public Str addSeparator(CharSequence separator, boolean acceptEmpty, Object... objects) {
		Str s = new Str(this.val);
		for (Object o : objects)
			if (acceptEmpty || ( o != null && !o.toString().isBlank()))
				s.val += separator + o.toString();
		return s;
	}
	
	/**
	 * Retorna uma copia deste objeto, apendando um ou mais objetos passados como parametro.
	 * Usa o separador default " " (espaco em branco) e ignora Strings vazias e objetos nulos.
	 */
	public Str add(Object... objects) {
		return this.addSeparator(" ", false, objects);
	}
	
	/**
	 * Retorna uma copia deste objeto, apendando um ou mais objetos passados como parametro.
	 * Ignora Strings vazias e objetos nulos.
	 */
	public Str add(String separator, Object... objects) {
		return this.addSeparator(separator, false, objects);
	}
	
	/**
	 * Apenda uma String mudando o valor original deste objeto, ou seja, sem criar uma copia.
	 */
	public Str append(Object obj) {
		this.val += obj.toString();
		return this;
	}

	/**
	 * Cria uma Str formatada, substituindo as ocorrências de '--' pelas demais Strings passadas de parametro. 
	 */
	public static Str format(CharSequence s, Object... args) {
		return new Str(s, args);
	}
	
	/**
	 * Apenda uma String mudando o valor original deste objeto, ou seja, sem criar uma copia.
	 * Uma quebra de linha eh inserida no final.
	 */
	public Str appendLn(Object obj) {
		this.val += obj.toString() + LN;
		return this;
	}
	
	public void append(char c) {
		this.val += c;
	}
	
	public Str desde(CharSequence inicio) {
		return this.desde(inicio, true);
	}
	
	public Str desde(CharSequence inicio, boolean inicioIncluso) {
		return sub(inicio, null, inicioIncluso, false);
	}
	
	public Str desde(int inicio) {
		return this.desde(inicio, true);
	}
	
	public Str desde(int inicio, boolean inicioIncluso) {
		if (!inicioIncluso)
			inicio++;
		return new Str(val.substring(inicio));
	}
	
	public Str desdeEnesimo(CharSequence inicio, int n, boolean inicioIncluso) {
		int index = indexOfEnesimo(inicio, n);
		if (!inicioIncluso)
			index += inicio.length();
		return new Str(val.substring(index));
	}
	
	public Str desdeEnesimo(CharSequence inicio, int n) {
		return this.desdeEnesimo(inicio, n, true);
	}
	
	public Str desdeUltimo(CharSequence inicio, boolean inicioIncluso) {
		int ultimoIndex = this.lastIndexOf(inicio);
		return this.desde(ultimoIndex, inicioIncluso);
	}
	
	public Str desdeUltimo(CharSequence inicio) {
		return this.desdeUltimo(inicio, true);
	}
	
	public Str ate(CharSequence fim) {
		return this.ate(fim, false);
	}
	
	public Str ate(CharSequence fim, boolean fimIncluso) {
		return sub(null, fim, true, fimIncluso);
	}
	
	public Str ate(int fim) {
		return this.ate(fim, false);
	}
	
	public Str ate(int fim, boolean fimIncluso) {
		if (fim > val.length())
			fim = val.length();
		if (fimIncluso)
			fim++;
		return new Str(val.substring(0, fim));
	}
	
	public Str ateEnesimo(CharSequence fim, int n, boolean fimIncluso) {
		int index = this.indexOfEnesimo(fim, n);
		if (fimIncluso) {
			index += fim.length();
		}
		return new Str(val.substring(0, index));
	}
	
	public Str ateEnesimo(CharSequence fim, int n) {
		return this.ateEnesimo(fim, n, false);
	}
	
	public Str ateUltimo(CharSequence fim, boolean fimIncluso) {
		int ultimoIndex = this.lastIndexOf(fim);
		return this.ate(ultimoIndex, fimIncluso);
	}
	
	public Str desde(boolean inicioIncluso, CharSequence... possiveisInicios) {
		int menorIndex = val.length();
		int tamanhoInicio = 0;
		for (CharSequence inicio : possiveisInicios) {
			int index = val.indexOf(inicio.toString());
			if (index != -1 && index < menorIndex) {
				menorIndex = index;
				tamanhoInicio = inicio.length();
			}
		}
		
		if (menorIndex == val.length())
			return new Str();
		
		return new Str(val.substring(inicioIncluso ? menorIndex : menorIndex + tamanhoInicio, val.length()));
	}
	
	public Str ate(boolean fimIncluso, CharSequence... possiveisFins) {
		int menorIndex = val.length() - 1;
		int tamanhoFim = 0;
		for (CharSequence fim : possiveisFins) {
			int index = val.indexOf(fim.toString());
			if (index != -1 && index < menorIndex) {
				menorIndex = index;
				tamanhoFim = fim.length();
			}
		}
		
		// Se precisa de um fim e ele nao foi encontrado, retornamos vazio.
		if (fimIncluso && menorIndex + tamanhoFim > val.length())
			return new Str();
		else // Caso contrario, retornamos tudo ou ate o fim que encontramos.
			return new Str(val.substring(0, fimIncluso ? menorIndex + tamanhoFim : menorIndex));
	}
	
	public Str sub(CharSequence inicio, CharSequence fim, boolean inicioIncluso, boolean fimIncluso) {
		int indexInicio = 0;
		if (inicio != null) {
			indexInicio = val.indexOf(inicio.toString());
			if (indexInicio == -1)
				return new Str();
			else if (!inicioIncluso)
				indexInicio += inicio.length();
		}
		int indexFim = val.length();
		if (fim != null) {
			indexFim = val.indexOf(fim.toString(), indexInicio);
			if (indexFim == -1)
				indexFim = val.length();
			else if (fimIncluso)
				indexFim += fim.length();
		}
		return new Str(val.substring(indexInicio, indexFim));
	}
	
	public Str sub(CharSequence inicio, boolean inicioIncluso) {
		int indexInicio = 0;
		if (inicio != null) {
			indexInicio = val.indexOf(inicio.toString());
			if (indexInicio == -1)
				return new Str();
			else if (!inicioIncluso)
				indexInicio += inicio.length();
		}
		return new Str(val.substring(indexInicio));
	}
	
	public Str sub(int indexInicio, int indexFim) {
		return new Str(val.substring(indexInicio, indexFim));
	}
	
	public Str sub(int indexInicio) {
		return new Str(val.substring(indexInicio));
	}
	
	public void limpar() {
		val = "";
	}
	
	public void retem(int indexInicio, int indexFim) {
		val = val.substring(indexInicio, indexFim); 
	}
	
	public Str retem(char... caracteres) {
		Str retorno = new Str();
		for (int i = 0; i < this.length(); i++) {
			char caractere = this.charAt(i);
			for (int j = 0; j < caracteres.length; j++)
				if (caractere == caracteres[j]) {
					retorno.append(this.charAt(i));
					break;
				}
		}
		return retorno;
	}
	
	public Str retemNumeros() {
		return this.retem('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	}
	
	public Str primeiros (int quantidadeCaracteres) {
		return this.sub(0, quantidadeCaracteres);
	}
	
	public Str ultimos(int quantidadeCaracteres) {
		return this.sub(this.length() - quantidadeCaracteres, this.length());
	}
	
	public Integer toInt() {
		return Integer.parseInt(this.val);
	}
	
	public Long toLong() {
		return Long.parseLong(this.val);
	}
	
	public Float toFloat() {
		return Float.parseFloat(this.val);
	}
	
	public Double toDouble() {
		return Double.parseDouble(this.val);
	}
	
	/**
	 * Versão mutável de replace.
	 * Funciona como o replace, mas modificando e retornando esta mesma instância.
	 */
	public Str troca(CharSequence de, CharSequence para) {
		this.val(val.replace(de.toString(), para.toString()));
		return this;
	}
	
	/**
	 * Versão mutável de replaceFirst.
	 * Funciona como o replaceFirst, mas modificando e retornando esta mesma instância.
	 */
	public Str trocaPrimeiro(CharSequence de, CharSequence para) {
		this.val(val.replaceFirst(de.toString(), para.toString()));
		return this;
	}
	
	public Str remover(CharSequence regex) {
		return new Str(val.replaceAll(regex.toString(), ""));
	}
	
	/**
	 * Retorna copia da String, mas sem o caractere indicado.
	 */
	public Str remover(char charParaRemocao) {
		char[] charArray = val.toCharArray();
		StringBuilder sb = new StringBuilder("");
		for (char c : charArray)
			if (c != charParaRemocao)
				sb.append(c);
		return new Str(sb);
	}
	
	public int index(CharSequence s, boolean ignoreEquals) {
		if (!ignoreEquals)
			return val.indexOf(s.toString());
		else
			return val.toLowerCase().indexOf(s.toString().toLowerCase());
	}
	
	/**
	 * Verifica se o primeiro texto eh igual a pelo menos um dos seguintes.
	 */
	public static boolean equals(CharSequence... textos) {
		CharSequence textoAvaliado = textos[0];
		for (int i = 1; i < textos.length; i++)
			if (textoAvaliado != null && textoAvaliado.equals(textos[i]))
				return true;
			else if (textoAvaliado == null && textos[i] == null)
				return true;
		return false;
	}
	
	public static Str valorPercentual (float a, float b) {
		return new Str((int)a + " (" + Math.round((a/b)*100) + "%)");
	}
	
	/**
	 * Retorna o index da n-esima ocorrencia de uma sub-string.
	 * Lança exceção se não encontrar.
	 */
	public int indexOfEnesimo(CharSequence s, int n) {
		assert(n > 0);
		int ultimoIndex = this.indexOf(s, 0);
		if (ultimoIndex == -1)
			throw new IllegalArgumentException("A string '" + s + "' sequer faz parte de '" + this.val + "', quanto mais tem uma n-esima ocorrencia." );
		for (int contador = 1; contador < n; contador++) {
			ultimoIndex = this.indexOf(s, ultimoIndex + s.length());
			if (ultimoIndex == -1) {
				throw new IllegalArgumentException("A string '" + s + "' nao aparece " + n + " vezes em '" + this.val + "'." );
			}
		}
		return ultimoIndex;
	}
	
	/**
	 * Retorna o index da n-esima ocorrencia de uma substring, se ela existir.
	 * Retorna -1 se não encontrar.
	 */
	public int buscaEnesimo(CharSequence s, int n) {
		assert(n > 0);
		int ultimoIndex = this.indexOf(s, 0);
		if (ultimoIndex == -1)
			return -1;
		for (int contador = 1; contador < n; contador++) {
			ultimoIndex = this.indexOf(s, ultimoIndex + s.length());
			if (ultimoIndex == -1) {
				return -1;
			}
		}
		return ultimoIndex;
	}
	
	public boolean contemAlgum(CharSequence... charSequences) {
		for (CharSequence s : charSequences)
			if (this.contains(s))
				return true;
		return false;
	}
	
	public boolean naoContem(CharSequence charSequence) {
		return !this.contains(charSequence);
	}
	
	public int getTamanho(String charset) {
		byte[] utf8Bytes;
		try {
			utf8Bytes = val.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Charset nao suportado foi usado como parametro.");
		}
		return utf8Bytes.length;
	}
	
	/**
	 * Conta quantas linhas tem na String (LN+1).
	 * Se val = null, são zero linhas.
	 * Se não existe separador na String (nenhum LN), é 1 linha.
	 */
	public int totalLinhas() {
		if (val == null)
			return 0;
		if (!val.contains(LN))
			return 1;
		String[] linhas = val.split(LN);
		return linhas.length;
	}
	
	public static Str formataTempo(long milisegundos) {
		if (milisegundos <= 0)
			return new Str();
		if (milisegundos >= Data.SEGUNDO) {
			if (milisegundos >= Data.MINUTO) {
				if (milisegundos >= Data.HORA) {
					if (milisegundos >= Data.DIA)
						return new Str(milisegundos/Data.DIA).append("d").append(formataTempo(milisegundos % Data.DIA));
					else
						return new Str(milisegundos/Data.HORA).append("h").append(formataTempo(milisegundos % Data.HORA));
				}
				else
					return new Str(milisegundos/Data.MINUTO).append("m").append(formataTempo(milisegundos % Data.MINUTO));
			}
			else
				return new Str(milisegundos/Data.SEGUNDO).append("s").append(formataTempo(milisegundos % Data.SEGUNDO));
		}
		else {
			return new Str(milisegundos).append("ms");
		}
	}
	
	public static Str[] vals(Object... objs) {
		Str[] array = new Str[objs.length];
		for (int i = 0; i < objs.length; i++) {
			array[i] = new Str(objs.toString());
		}
		return array;
	}
}
