package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data extends Date {
	
	private static final long serialVersionUID = 1L;
	
	public static final SimpleDateFormat DATA_dd_MM_yyyy_HH_mm_ss_SSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSS");
	public static final SimpleDateFormat DATA_dd_MM_yyyy_HH_mm_ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat DATA_dd_MM_yyyy_HH_mm = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final SimpleDateFormat DATA_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat DATA_dd_MM_HH_mm = new SimpleDateFormat("dd/MM HH:mm");
	public static final SimpleDateFormat DATA_dd_MM = new SimpleDateFormat("dd/MM");

	public static final SimpleDateFormat HORA_HH_mm_ss_SSS = new SimpleDateFormat("HH:mm:ss,SSS");
	public static final SimpleDateFormat HORA_HH_mm_ss = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat HORA_HH_mm = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat HORA_HH = new SimpleDateFormat("HH");
	
	public Data() {
		super();
	}
	
	public Data(Date date) {
		super(date.getTime());
	}
	
	public Data(CharSequence data, SimpleDateFormat format) {
		super(parse(data, format).getTime());
	}
	
	public Data(CharSequence data) {
		this(data, getFormat(data));
	}
	
	private static Date parse(CharSequence data, SimpleDateFormat format) {
		try {
			return format.parse(data.toString());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	private static SimpleDateFormat getFormat(CharSequence data) {
		switch(data.length()) {
			case  5: return DATA_dd_MM;
			case 10: return DATA_dd_MM_yyyy;
			case 11: return DATA_dd_MM_HH_mm;
			case 16: return DATA_dd_MM_yyyy_HH_mm;
			case 19: return DATA_dd_MM_yyyy_HH_mm_ss;
			case 23: return DATA_dd_MM_yyyy_HH_mm_ss_SSS;
			case  8: return HORA_HH_mm_ss;
			case 12: return HORA_HH_mm_ss_SSS;
			
			default: throw new IllegalArgumentException("Formato de data invalida!"); 
		}
	}
	
	/** 
	 * Formato padrao dd/MM/yyyy HH:mm:ss
	 * @see java.util.Date#toString()
	 */
	@Override
	public String toString() {
		return toString(DATA_dd_MM_yyyy_HH_mm_ss).val();
	}
	
	public Str toString(SimpleDateFormat format) {
		return new Str(format.format(this));
	}
}
