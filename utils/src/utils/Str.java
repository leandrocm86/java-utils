package utils;

public class Str extends Objeto implements Comparable<CharSequence>, CharSequence {
	
	private String val;
	
	public Str() {
		this.val = "";
	}
	
	public Str(String val) {
		if (val == null)
			val = "";
		this.val = val;
	}
	
	public Str(Object object) {
		this.val = object.toString();
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
	public boolean isEmpty() {
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
	
	public boolean notEmpty() {
		return !this.isEmpty();
	}
	
	public boolean startsWithIgnoreCase(CharSequence texto) {
		return val.toLowerCase().startsWith(texto.toString().toLowerCase());
	}
	
	public Character characterAt(int index) {
		return new Character(val.charAt(index));
	}
	
	public Str lowerCase() {
		return this.lowerCase(false);
	}
	
	public Str lowerCase(boolean copia) {
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
	
	public Str append(CharSequence string) {
		this.val += string.toString();
		return this;
	}
	
	public Str appendLn(CharSequence string) {
		this.val += string.toString() + "\n";
		return this;
	}
	
	public void append(char c) {
		this.val += c;
	}
	
	public Str desde(CharSequence inicio, boolean inicioIncluso) {
		return substring(inicio, null, inicioIncluso, false);
	}
	
	public Str desde(int inicio) {
		return this.ate(inicio, true);
	}
	
	public Str desde(int inicio, boolean inicioIncluso) {
		if (!inicioIncluso)
			inicio++;
		return new Str(val.substring(inicio));
	}
	
	public Str ate(CharSequence fim, boolean fimIncluso) {
		return substring(null, fim, true, fimIncluso);
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
}
