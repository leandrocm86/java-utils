import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	public static void main(String[] args) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("05/01/1986");
		String d = "Data " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSS").format(date);
		System.out.println(d);
		
		String t = "abc";
		testar(t);
	}
	
	private static void testar(CharSequence a) {
		System.out.println(a);
	}

}
