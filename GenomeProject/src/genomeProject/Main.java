package genomeProject;

import java.io.IOException;

import genomeProject.HTTPHelper;

public class Main {
	
	public static void main(String[] args) {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String command = "efetch.fcgi?db=nuccore&id=KR132597.1&rettype=fasta&retmode=json";
		String query = header.concat(command);
		
		HTTPHelper HTTPRequestHandler = new HTTPHelper();
		try {
			HTTPRequestHandler.sendGET(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

