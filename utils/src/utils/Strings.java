package utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Strings {
	
	private static SimpleDateFormat TIME_FORMAT;
	
	public static boolean empty(String s) {
		return s == null || s.equals("");
	}
	
	public static String stackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}
	
	public static Date parseTime(String time) throws ParseException {
		if (TIME_FORMAT == null)
			TIME_FORMAT = new SimpleDateFormat("hh:mm:ss,SSS");
		if (time.length() == 5)
			time += ":00,000";
		else if (time.length() == 8)
			time += ",000";
		return TIME_FORMAT.parse(time);
	}
}
