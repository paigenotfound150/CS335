package genomeProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(response);
        
        // Split the response into a list of QueryResults
        ArrayList<String> records = StringFormatter.splitRecord(response);
        ArrayList<QueryResult> queryResults = createQueryResultsArray(records);

        // After the NCBI fetch, now ask for primers
        PrimerInputHandler primerInputHandler = new PrimerInputHandler();
        primerInputHandler.getPrimersFromUser();
        String forwardPrimer = primerInputHandler.getForwardPrimer();
        String reversePrimer = primerInputHandler.getReversePrimer();

        // Need to reverse the reverse primer
        String reversedReversePrimer = reverseReversePrimer(reversePrimer);    

        // Since we are done with all input operations, close the Scanner
        primerInputHandler.closeScanner();
    }
    
    public static ArrayList<QueryResult> createQueryResultsArray(ArrayList<String> records) {
        ArrayList<QueryResult> queryResults = new ArrayList();
    	
        for (String record : records) {
        	QueryResult newQueryResult = new QueryResult(StringFormatter.defGet(record), StringFormatter.seqGet(record));
            queryResults.add(newQueryResult);
            System.out.println(newQueryResult.fastaGet());
        }
        return queryResults;
    }
    
    public static String reverseReversePrimer(String reversePrimer) {
    	HashMap<String, ArrayList<String>> reversePrimerMatches = NeucMatch.makeReversePrimerDictionary();
        String reversedPrimer = "";
        for (int i = 0; i < reversePrimer.length(); i++) {
        	char current_char = reversePrimer.charAt(i);
        	String n = String.valueOf(current_char);
        	String match = NeucMatch.getMatch(reversePrimerMatches, n).replaceAll("[\\[\\]]", "");
            reversedPrimer = reversedPrimer+match;
        }
        System.out.println("The reversed primer is" + reversedPrimer);
        return reversedPrimer;
    }
}
