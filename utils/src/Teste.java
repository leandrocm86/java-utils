import java.text.ParseException;

import utils.CDI;

public class Teste {

	public static void main(String[] args) throws ParseException {
		String a = "ABC";
		String b = "def";
		Boolean c = Boolean.TRUE;
		Boolean d = Boolean.FALSE;
		CDI.set(a, "a");
		CDI.set(b, "b");
		CDI.set(c, "c");
		CDI.set(d, "d");
		System.out.println(CDI.get(String.class, "a"));
		System.out.println(CDI.get(String.class, "b"));
		System.out.println(CDI.get(Boolean.class, "c"));
		System.out.println(CDI.get(Boolean.class, "d"));
	}
	
//	private static void testar(CharSequence a) {
//		System.out.println(a);
//	}

}
