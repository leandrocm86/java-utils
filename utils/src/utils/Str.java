package utils;

public class Str extends Objeto implements Comparable<Str>, CharSequence {
	
	private String val;
	
	public Str() {
		this.val = "";
	}
	
	public Str(String val) {
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
	public int compareTo(String anotherString) {
		return val.compareTo(anotherString);
	}
	public int compareToIgnoreCase(String str) {
		return val.compareToIgnoreCase(str);
	}
	public String concat(String str) {
		return val.concat(str);
	}
	public boolean contains(CharSequence s) {
		return val.contains(s);
	}
	public boolean endsWith(String suffix) {
		return val.endsWith(suffix);
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
	public boolean equalsIgnoreCase(String anotherString) {
		return val.equalsIgnoreCase(anotherString);
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
	public int indexOf(String str) {
		return val.indexOf(str);
	}
	public int indexOf(String str, int fromIndex) {
		return val.indexOf(str, fromIndex);
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
	public int lastIndexOf(String str) {
		return val.lastIndexOf(str);
	}
	public int lastIndexOf(String str, int fromIndex) {
		return val.lastIndexOf(str, fromIndex);
	}
	public int length() {
		return val != null ? val.length() : 0;
	}
	public boolean matches(String regex) {
		return val.matches(regex);
	}
	public String replace(char oldChar, char newChar) {
		return val.replace(oldChar, newChar);
	}
	public String replace(CharSequence target, CharSequence replacement) {
		return val.replace(target, replacement);
	}
	public String replaceAll(String regex, String replacement) {
		return val.replaceAll(regex, replacement);
	}
	public String replaceFirst(String regex, String replacement) {
		return val.replaceFirst(regex, replacement);
	}
	public String[] split(String regex) {
		return val.split(regex);
	}
	public String[] split(String regex, int limit) {
		return val.split(regex, limit);
	}
	public boolean startsWith(String prefix) {
		return val.startsWith(prefix);
	}
	public boolean startsWith(String prefix, int toffset) {
		return val.startsWith(prefix, toffset);
	}
	public boolean startsWith(Str prefix) {
		return val.startsWith(prefix.val);
	}
	public boolean startsWith(Str prefix, int toffset) {
		return val.startsWith(prefix.val, toffset);
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
	public int compareTo(Str str) {
		return val.compareTo(str.val());
	}
	
	public String val() {
		return this.val;
	}
	
	public void val(String novoValor) {
		this.val = novoValor;
	}
	
	public boolean notEmpty() {
		return !this.isEmpty();
	}
	
	public boolean equalsIgnoreCase(Str anotherString) {
		return val.equalsIgnoreCase(anotherString.val());
	}
	
	public boolean startsWithIgnoreCase(String texto) {
		return val.toLowerCase().startsWith(texto.toLowerCase());
	}
	
	public boolean startsWithIgnoreCase(Str texto) {
		return val.toLowerCase().startsWith(texto.toLowerCase());
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
	
	public int compare(Str str) {
		return val.compareTo(str.val());
	}
	
	public int compareIgnoreCase(Str str) {
		return val.compareToIgnoreCase(str.val());
	}
	
	public Str[] corta(String regex) {
		String[] split = val.split(regex);
		Str[] corte = new Str[split.length];
		for (int i = 0; i < split.length; i++)
			corte[i] = new Str(split[i]);
		return corte;
	}
	
	public Str append(Str str) {
		return this.append(str.val);
	}
	
	public Str appendLn(Str str) {
		return this.appendLn(str.val);
	}
	
	public Str append(String string) {
		this.val += string;
		return this;
	}
	
	public Str appendLn(String string) {
		this.val += string + "\n";
		return this;
	}
	
	public void append(char c) {
		this.val += c;
	}
	
	public Str desde(String inicio, boolean inicioIncluso) {
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
	
	public Str ate(String fim, boolean fimIncluso) {
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
	
	public Str desde(boolean inicioIncluso, String... possiveisInicios) {
		int menorIndex = val.length();
		int tamanhoInicio = 0;
		for (String inicio : possiveisInicios) {
			int index = val.indexOf(inicio);
			if (index != -1 && index < menorIndex) {
				menorIndex = index;
				tamanhoInicio = inicio.length();
			}
		}
		
		if (menorIndex == val.length())
			return new Str();
		
		return new Str(val.substring(inicioIncluso ? menorIndex : menorIndex + tamanhoInicio, val.length()));
	}
	
	public Str ate(boolean fimIncluso, String... possiveisFins) {
		int menorIndex = val.length() - 1;
		int tamanhoFim = 0;
		for (String fim : possiveisFins) {
			int index = val.indexOf(fim);
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
	
	public Str substring(String inicio, String fim, boolean inicioIncluso, boolean fimIncluso) {
		int indexInicio = 0;
		if (inicio != null) {
			indexInicio = val.indexOf(inicio);
			if (indexInicio == -1)
				return new Str();
			else if (!inicioIncluso)
				indexInicio += inicio.length();
		}
		int indexFim = val.length();
		if (fim != null) {
			indexFim = val.indexOf(fim, indexInicio);
			if (indexFim == -1)
				indexFim = val.length();
			else if (fimIncluso)
				indexFim += fim.length();
		}
		return new Str(val.substring(indexInicio, indexFim));
	}
	
	public void limpar() {
		val = "";
	}
	
	public void retem(int indexInicio, int indexFim) {
		val = val.substring(indexInicio, indexFim); 
	}
}
