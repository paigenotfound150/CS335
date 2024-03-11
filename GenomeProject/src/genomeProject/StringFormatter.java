package genomeProject;

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
	
	// to parse UID list
	public static String uidFinder(String response) {
		int listStart = response.indexOf("<IdList>");
		int listEnd = response.indexOf("</IdList>");
		String uidList = response.substring((listStart + 8), listEnd);
		uidList = uidList.replaceAll("</Id><Id>", ",");
		uidList = uidList.replaceAll("<Id>", "");
		uidList = uidList.replaceAll("</Id>", "");
		return(uidList);
	}
	
	// pull definition from eFetch response
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
}