package genomeProject;

class StringFormatter {
	
	/* removeWhiteSpace removes any whitespace existing in a string. */
	public static String removeWhiteSpace(String original) {
		String str = original.replaceAll("\\s", "");
		return str;
	}
}