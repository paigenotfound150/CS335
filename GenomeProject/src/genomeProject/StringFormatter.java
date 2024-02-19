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
}