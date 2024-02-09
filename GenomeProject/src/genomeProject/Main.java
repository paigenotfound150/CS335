package genomeProject;

import java.io.IOException;
import java.io.FileWriter;

public class Main {
	
	public static void main(String[] args) {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String command = "efetch.fcgi?db=nuccore&id=KR132597.1&retmode=json";
		String query = header.concat(command);
		String formatted_query = StringFormatter.removeWhiteSpace(query);
		String response = "";
		try {
			response = HTTPHelper.sendGET(formatted_query);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// To string method prints it with specified indentation
		String s = response.replaceAll("(?<=[{:,])|(?=[:,}])", "\"");
		System.out.println(s);
		
	}
	
	
}

