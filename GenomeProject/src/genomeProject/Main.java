package genomeProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
        String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
        
        // eSearch builder
        String dbQuery = entrezQuery.main();
        String esearchCommand = "esearch.fcgi?db=nuccore&term=".concat(dbQuery);
        String esearchQuery = header.concat(esearchCommand);
        String esearchResponse = "";
        
        // eSearch send
        try {
            esearchResponse = HTTPHelper.sendGET(esearchQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // eFetch builder
        String uidList = StringFormatter.uidFinder(esearchResponse);
        String efetchCommand = "efetch.fcgi?db=nuccore&id=" + uidList + "&rettype=json";
        String efetchQuery = header.concat(efetchCommand);
        String formatted_query = StringFormatter.removeWhiteSpace(efetchQuery);
        String response = "";
        
        // eFetch send
        try {
            response = HTTPHelper.sendGET(formatted_query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // To string method prints it with specified indentation
        // (not sure what this does but I trust paige <3 -Anna)
        String s = response.replaceAll("(?<=[{:,])|(?=[:,}])", "\"");
        System.out.println(s);

        // After the NCBI fetch, now ask for primers
        PrimerInputHandler primerInputHandler = new PrimerInputHandler();
        primerInputHandler.getPrimersFromUser();
        String forwardPrimer = primerInputHandler.getForwardPrimer();
        String reversePrimer = primerInputHandler.getReversePrimer();
        
        // using f + r primer to make the error go away
        System.out.println(forwardPrimer + reversePrimer);
        
        // testing fasta maker
        String fastatest = StringFormatter.fastaGet(response);
        System.out.println(fastatest);

        // Since we are done with all input operations, close the Scanner
        primerInputHandler.closeScanner();
    }
}
