package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.Log;

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
	public static final DateTimeFormatter DATA_dd_MM_yyyy_HH_mm_ss_SSS = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss,SSS", Locale.getDefault());
	public static final DateTimeFormatter DATA_dd_MM_yyyy_HH_mm_ss = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
	public static final DateTimeFormatter DATA_dd_MM_yyyy_HH_mm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault());
	public static final DateTimeFormatter DATA_dd_MM_yyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter DATA_dd_MM_HH_mm = DateTimeFormatter.ofPattern("dd/MM HH:mm", Locale.getDefault());
	public static final DateTimeFormatter DATA_dd_MM = DateTimeFormatter.ofPattern("dd/MM");

	public static final DateTimeFormatter HORA_HH_mm_ss_SSS = DateTimeFormatter.ofPattern("HH:mm:ss,SSS", Locale.getDefault());
	public static final DateTimeFormatter HORA_HH_mm_ss = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());
	public static final DateTimeFormatter HORA_HH_mm = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
	public static final DateTimeFormatter HORA_HH = DateTimeFormatter.ofPattern("HH", Locale.getDefault());
	
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
	
	public Data(CharSequence data, DateTimeFormatter format) {
		super(parseDate(data, format).getTime());
		this.valor = data;
		this.formato = format.toString();
	}
	
	public Data(CharSequence data, String format) {
		this(data, getSimpleDateFormat(format));
	}
	
	private static Date parseDate(CharSequence data, DateTimeFormatter format) {
		Benchmark.start("parseDate");
		Date date = parse(data, format);
		Benchmark.stop("parseDate");
		return date;
	}
	
	private static DateTimeFormatter getSimpleDateFormat(String format) {
		Benchmark.start("SimpleDateFormat");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		Benchmark.stop("SimpleDateFormat");
		return formatter;
	}
	
	private static Date parse(CharSequence data, DateTimeFormatter format) {
		try {
			return new Date(Instant.from(ZonedDateTime.parse(data, format)).toEpochMilli());
		} catch (Exception e) {
			Log.msg("Impossivel converter " + data.toString() + " para Data no formato " + format.toString());
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
			return DATA_dd_MM_yyyy_HH_mm_ss.format(this.toZonedInstant());
	}
	
	public Str toStr(DateTimeFormatter formatter) {
		return new Str(formatter.format(this.toZonedInstant()));
	}
	
	public Str toStr(CharSequence format) {
		return new Str(DateTimeFormatter.ofPattern(format.toString()).format(this.toZonedInstant()));
	}
	
	public ZonedDateTime toZonedInstant() {
        return Instant.ofEpochMilli(getTime()).atZone(ZoneId.systemDefault());
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
