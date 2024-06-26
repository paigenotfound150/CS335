package genomeProject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {
	
	// removes any whitespace in a string
	public static String removeWhiteSpace(String original) {
		String str = original.replaceAll("\\s", "");
		return str;
	}
	
	// for HTTP requests
	public static String spaceReplace(String original) {
		String str = original.replaceAll("\\s", "+");
		return(str);
	}
	
	// parse UID list
	public static String uidFinder(String response) {
		int listStart = response.indexOf("<IdList>");
		int listEnd = response.indexOf("</IdList>");
		String uidList = response.substring((listStart + 8), listEnd);
		uidList = uidList.replaceAll("</Id><Id>", ",");
		uidList = uidList.replaceAll("<Id>", "");
		uidList = uidList.replaceAll("</Id>", "");
		return(uidList);
	}
	
	// split into separate records and add to a list
	public static ArrayList<String> splitRecord(String response) {
		ArrayList<String> records = new ArrayList<String>();
		
		Matcher m = Pattern.compile(
		                            Pattern.quote("<GBSeq>")
		                            + "(.*?)"
		                            + Pattern.quote("</GBSeq>")
		                   ).matcher(response);
		while(m.find()){
		    String match = m.group(1);
		    records.add(match);
		}
		return records;
	}
	
	// get definition
	public static String defGet(String response) {
		int defStart = response.indexOf("<GBSeq_definition>");
		int defEnd = response.indexOf("</GBSeq_definition>");
		String definition = response.substring((defStart + 18), defEnd);
		return(definition);
	}
	
	// pull sequence from eFetch response
	public static String seqGet(String response) {
		int seqStart = response.indexOf("<GBSeq_sequence>");
		int seqEnd = response.indexOf("</GBSeq_sequence>");
		String sequence = response.substring((seqStart + 16), seqEnd).toUpperCase();
		return(sequence);
	}

	
	// get characters inbetween two strings
	public static String getBookEndedString(String str, String start, String finish) {
		String s = str.substring(str.indexOf(start) + start.length());
		s = s.substring(0, s.indexOf(finish));
		return s;
	}
	
}
