package genomeProject;

public class UID_parser{
	public static String uidFinder(String response) {
		int listStart = response.indexOf("<IdList>");
		int listEnd = response.indexOf("</IdList>");
		String uidList = response.substring((listStart + 8), listEnd);
		uidList = uidList.replaceAll("</Id><Id>", ", ");
		uidList = uidList.replaceAll("<Id>", "");
		uidList = uidList.replaceAll("</Id>", "");
		return(uidList);
	}
}