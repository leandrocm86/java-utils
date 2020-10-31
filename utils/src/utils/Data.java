package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data extends Date implements Objeto {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 1 Dia em milisegundos.
	 */
	public static final int DIA = 86400000;
	
	/**
	 * 1 Hora em milisegundos.
	 */
	public static final int HORA = 3600000;
	/**
	 * 1 Minuto em milisegundos.
	 */
	public static final int MINUTO = 60000;
	/**
	 * 1 Segundo em milisegundos.
	 */
	public static final int SEGUNDO = 1000;
	
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
	
	private CharSequence valor;
	private CharSequence formato;
	
	public Data() {
		super();
	}
	
	public Data(long milisegundos) {
		super(milisegundos);
	}
	
	public Data(Date date) {
		super(date.getTime());
	}
	
	public Data(CharSequence data, SimpleDateFormat format) {
		super(parse(data, format).getTime());
		this.valor = data;
		this.formato = format.toPattern();
	}
	
	public Data(CharSequence data, String format) {
		this(data, new SimpleDateFormat(format));
	}
	
	private static Date parse(CharSequence data, SimpleDateFormat format) {
		try {
			return format.parse(data.toString());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/** 
	 * Formato padrao dd/MM/yyyy HH:mm:ss, a nao ser que outro tenha sido usado no construtor.
	 * @see java.util.Date#toString()
	 */
	@Override
	public String toString() {
		if (this.formato != null)
			return toStr(this.formato).val();
		else
			return toStr(DATA_dd_MM_yyyy_HH_mm_ss).val();
	}
	
	public Str toStr(SimpleDateFormat format) {
		return new Str(format.format(this));
	}
	
	public Str toStr(CharSequence format) {
		return new Str(new SimpleDateFormat(format.toString()).format(this));
	}
	
	@Override
	public int getHours() {
		if (this.formato != null) {
			int indexHoras = this.formato.toString().indexOf("HH");
			return Integer.parseInt(this.valor.subSequence(indexHoras, indexHoras+2).toString());
		}
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(this);
			return calendar.get(Calendar.HOUR_OF_DAY);
		}
	}
	
	/**
	 * Obtem a diferenca em milesegundos para uma outra determinada data.
	 */
	public long diferenca(Date date) {
		return this.getTime() - date.getTime();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Date)
			return super.getTime() == ((Date)obj).getTime();
		else
			return false;
	}
	
}
