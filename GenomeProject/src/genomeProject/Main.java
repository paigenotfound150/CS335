package genomeProject;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String command = "efetch.fcgi?db=nuccore&id=KR132597.1&rettype=fasta&retmode=json";
		String query = header.concat(command);
		String formatted_query = StringFormatter.removeWhiteSpace(query);
		String response = "";
		try {
			response = HTTPHelper.sendGET(formatted_query);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(response);
	}
	
	
}

