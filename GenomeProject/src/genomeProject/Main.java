package genomeProject;

import java.io.IOException;


public class Main {
	
	public static void main(String[] args) {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String dbQuery = entrezQuery.main();
		String esearchCommand = "esearch.fcgi?db=nuccore&term=".concat(dbQuery);
		String esearchQuery = header.concat(esearchCommand);
		String esearchResponse = "";
		try {
			esearchResponse = HTTPHelper.sendGET(esearchQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String uidList = UID_parser.uidFinder(esearchResponse);
		System.out.println(uidList);
//		String efetchCommand = "efetch.fcgi?db=nuccore&id=KR132597.1&retmode=json";
//		String efetchQuery = header.concat(efetchCommand);
//		String formatted_query = StringFormatter.removeWhiteSpace(efetchQuery);
//		String response = "";
//		try {
//			response = HTTPHelper.sendGET(formatted_query);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// To string method prints it with specified indentation
//		String s = response.replaceAll("(?<=[{:,])|(?=[:,}])", "\"");
//		System.out.println(s);
		
	}
	
	
}

