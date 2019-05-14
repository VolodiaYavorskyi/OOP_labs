package lab7;

import java.util.regex.*;

public class InputChecker {
	
	public static boolean correctStartTime(String s) {
		Pattern p = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
		Matcher m = p.matcher(s);
		
		return m.matches();
	}
	
	public static boolean correctDuration(String s) {
		Pattern p = Pattern.compile("^\\d+ hours [0-5]?[0-9] minutes$");
		Matcher m = p.matcher(s);
		
		return m.matches();
	}
	
	public static boolean correctParticipant(String s) {
		Pattern p = Pattern.compile("^(\\w+)(\\s\\w+)*$");
		Matcher m = p.matcher(s);
		
		return m.matches();
	}
}