package utils;

import estruturas.Array;

public class Str implements Comparable<CharSequence>, CharSequence, Objeto {
	
	public static final String LN = System.getProperty("line.separator");
	
	public static final String SEM_CASE = "sem_case";
	public static final String SEM_CASE_E_ACENTO = "sem_case_acento";
	
	private static final Array conjuntoC = new Array('c', 'ç');
	private static final Array conjuntoA = new Array('a', 'á', 'à', 'ã', 'â');
	private static final Array conjuntoE = new Array('e', 'é', 'ê');
	private static final Array conjuntoI = new Array('i', 'í');
	private static final Array conjuntoO = new Array('o', 'ó', 'õ', 'ô');
	private static final Array conjuntoU = new Array('u', 'ú');
	
	
	private String val;
	
	public Str() {
		this.val = "";
	}
	
	public Str(String val) {
		if (val != null)
			this.val = val;
		else
			this.val = "";
	}
	
	public Str(Object object) {
		if (object != null)
			this.val = object.toString();
		else this.val = "";
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
	public String concat(CharSequence str) {
		return val.concat(str.toString());
	}
	public boolean contains(CharSequence s) {
		return val.contains(s.toString());
	}
	public boolean endsWith(CharSequence suffix) {
		return val.endsWith(suffix.toString());
	}
	public boolean equals(Object anObject) {
		if (anObject == null || val == null)
			return false;
		if (anObject instanceof Str)
			return val.equals(((Str) anObject).val());
		else if (anObject instanceof String)
			return val.equals(anObject);
		else return false;
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
	public boolean vazio() {
		return val == null || val.isEmpty();
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
	public String replaceAll(CharSequence regex, CharSequence replacement) {
		return val.replaceAll(regex.toString(), replacement.toString());
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
	public String trim() {
		return val.trim();
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
	
	public boolean naoVazio() {
		return !this.vazio();
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
			case SEM_CASE_E_ACENTO: {
				Str str1 = val.length() >= texto.length() ? this.ate(texto.length()) : this;
				Str str2 = val.length() >= texto.length() ? new Str(texto) : new Str(texto).ate(val.length());
				return str1.comparar(str2, SEM_CASE_E_ACENTO) == 0;
			}
			default: return this.startsWith(texto);
		}
	}
	
	public int comparar(CharSequence texto, String opcao) {
		switch(opcao) {
			case SEM_CASE: return this.compareToIgnoreCase(texto);
			case SEM_CASE_E_ACENTO: {
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
			}
			default: return this.compareTo(texto);
		}
	}
	
	private boolean mesmoConjunto(char a, char b) {
		if (conjuntoA.contem(a))
			return conjuntoA.contem(b);
		if (conjuntoE.contem(a))
			return conjuntoE.contem(b);
		if (conjuntoI.contem(a))
			return conjuntoI.contem(b);
		if (conjuntoO.contem(a))
			return conjuntoO.contem(b);
		if (conjuntoU.contem(a))
			return conjuntoU.contem(b);
		if (conjuntoC.contem(a))
			return conjuntoC.contem(b);
		return false;
	}
	
	public Character characterAt(int index) {
		return new Character(val.charAt(index));
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
	
	public Str[] corta(CharSequence regex) {
		String[] split = val.split(regex.toString());
		Str[] corte = new Str[split.length];
		for (int i = 0; i < split.length; i++)
			corte[i] = new Str(split[i]);
		return corte;
	}
	
	public Str append(Object obj) {
		this.val += obj.toString();
		return this;
	}
	
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
		return substring(inicio, null, inicioIncluso, false);
	}
	
	public Str desde(int inicio) {
		return this.desde(inicio, true);
	}
	
	public Str desde(int inicio, boolean inicioIncluso) {
		if (!inicioIncluso)
			inicio++;
		return new Str(val.substring(inicio));
	}
	
	public Str desdeUltimo(CharSequence inicio, boolean inicioIncluso) {
		int ultimoIndex = this.lastIndexOf(inicio);
		return this.desde(ultimoIndex, inicioIncluso);
	}
	
	public Str ate(CharSequence fim) {
		return this.ate(fim, false);
	}
	
	public Str ate(CharSequence fim, boolean fimIncluso) {
		return substring(null, fim, true, fimIncluso);
	}
	
	public Str ateUltimo(CharSequence fim, boolean fimIncluso) {
		int ultimoIndex = this.lastIndexOf(fim);
		return this.ate(ultimoIndex, fimIncluso);
	}
	
	public Str ate(int fim) {
		return this.ate(fim, false);
	}
	
	public Str ate(int fim, boolean fimIncluso) {
		if (fimIncluso)
			fim++;
		return new Str(val.substring(0, fim));
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
	
	public Str substring(CharSequence inicio, CharSequence fim, boolean inicioIncluso, boolean fimIncluso) {
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
	
	public Str sub(int indexInicio, int indexFim) {
		return new Str(val.substring(indexInicio, indexFim));
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
	
	public Str ultimos(int quantidade) {
		return this.sub(this.length() - quantidade, this.length());
	}
	
	public Integer toInt() {
		return Integer.parseInt(this.val);
	}
	
	public Float toFloat() {
		return Float.parseFloat(this.val);
	}
	
	public Str troca(CharSequence de, CharSequence para) {
		this.val(this.val.replaceAll(de.toString(), para.toString()));
		return this;
	}
}
