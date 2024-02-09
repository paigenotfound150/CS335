package genomeProject;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String command = "efetch.fcgi?db=nuccore&id=KR132597.1&rettype=fasta&retmode=json";
		String query = header.concat(command);
		
		try {
			HTTPHelper.sendGET(query);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

