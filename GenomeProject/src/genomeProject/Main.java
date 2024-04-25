package genomeProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
        
        String esearchResponse = eSearcher(header, sc);
        
        // catch if there's no results from esearch, give option to restart
        while(esearchResponse.contains("No items found")) {
        	System.out.println("Sorry, but we could not find that search term in our database. Would you like to try entering a new query? Please enter y or n.");
        	String choice = sc.nextLine();
        	
        	if(choice.equals("y")){
        		esearchResponse = eSearcher(header, sc);
        	} else {
        		System.exit(0);
        	}
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
        primerInputHandler.getPrimersFromUser(sc);
        String forwardPrimer = primerInputHandler.getForwardPrimer();
        String reversePrimer = primerInputHandler.getReversePrimer();
        String reverseReversed = reverseReversePrimer(reversePrimer);
        
     // Find matches
        ArrayList<Barcode> barcodes = findBarcodes(queryResults, forwardPrimer, reverseReversed);
        printMatches(barcodes);
        
        sc.close();   
        }
    

    public static ArrayList<Barcode> findBarcodes(ArrayList<QueryResult> queryResults, String forwardPrimer, String reversePrimer) {
    	ArrayList<Barcode> matches = new ArrayList<>();
    	for (QueryResult result: queryResults) {
    		String sequence = result.getSequence();
    		int start = sequence.indexOf(forwardPrimer);
    		int end = sequence.lastIndexOf(reversePrimer);

    		// Check if there's actually a match found
    		if ((start == -1) || (end == -1) || (end <= start)) { continue; }

    		String matched_seq = sequence.substring(start, end);
    		int numberBasePairs = matched_seq.length();	
    		String accession = result.getAccessionID();
    		String desc = result.getDescription();
    		Barcode new_match = new Barcode(accession, desc, matched_seq, numberBasePairs);
    		matches.add(new_match);
    	}
    	return matches;
    }
    
    public static void printMatches(ArrayList<Barcode> matches) {
    	System.out.println("Your matches are located below:");
    	for (Barcode match: matches) {
    		System.out.println(match.getInfo()); 
    		}
    	}

    public static ArrayList<QueryResult> createQueryResultsArray(ArrayList<String> records) {
        ArrayList<QueryResult> queryResults = new ArrayList<>();
        
        for (String record : records) {
        	String description = StringFormatter.defGet(record);
        	String sequence = StringFormatter.seqGet(record);
            String accessionID = StringFormatter.getBookEndedString(record, "<GBSeq_accession-version>", "</GBSeq_accession-version>");
            String speciesName = StringFormatter.getBookEndedString(record, "<GBSeq_organism>", "</GBSeq_organism>");
            QueryResult newQueryResult = new QueryResult(
                description,
                sequence, 
                accessionID, speciesName);
            queryResults.add(newQueryResult);
            System.out.println(newQueryResult.fastaGet());
        }
        return queryResults;
    }

    public static String reverseReversePrimer(String reversePrimer) {
        HashMap<String, ArrayList<String>> reversePrimerMatches = NeucMatch.makeReversePrimerDictionary();
        String reversedPrimer = "";
        for (int i = reversePrimer.length() - 1; i >= 0; i--) {
        	char current_char = reversePrimer.charAt(i);
        	String n = String.valueOf(current_char);
        	String match = NeucMatch.getMatch(reversePrimerMatches, n).replaceAll("[\\[\\]]", "");
            reversedPrimer = match + reversedPrimer; // Fix to correctly reverse and complement
        }
        System.out.println("The reversed primer is: " + reversedPrimer);
        return reversedPrimer;
    }
    
    public static String eSearcher(String header, Scanner sc) {
        // eSearch builder
        String dbQuery = EntrezQuery.getUserEntrezQuery(sc);
        String esearchCommand = "esearch.fcgi?db=nuccore&term=" + dbQuery;
        String esearchQuery = header.concat(esearchCommand);
        String esearchResponse = "";
        
        // eSearch send
        try {
            esearchResponse = HTTPHelper.sendGET(esearchQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return esearchResponse;
    }
}
