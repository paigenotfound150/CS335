package genomeProject;

public class StringFormatter {
	
	/* removeWhiteSpace removes any whitespace in a string. */
	public static String removeWhiteSpace(String original) {
		String str = original.replaceAll("\\s", "");
		return str;
	}
}