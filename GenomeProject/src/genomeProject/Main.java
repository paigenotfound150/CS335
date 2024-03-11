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
        
        // To string method prints it with specified indentation
        // (not sure what this does but I trust paige <3 -Anna)
        String s = response.replaceAll("(?<=[{:,])|(?=[:,}])", "\"");
        System.out.println(s);
        List<QueryResult> queryResults = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // Use a single Scanner instance

        // Adjusted entrezQuery to use the existing scanner instance
        String dbQuery = entrezQuery.main(scanner);
        String esearchCommand = "esearch.fcgi?db=nuccore&term=" + dbQuery + "&retmode=json&rettype=uilist";
        String esearchQuery = header + esearchCommand;

        try {
            String esearchResponse = HTTPHelper.sendGET(esearchQuery);
            String uidList = StringFormatter.uidFinder(esearchResponse);

            String efetchCommand = "efetch.fcgi?db=nuccore&id=" + uidList + "&rettype=fasta&retmode=text";
            String efetchQuery = header + efetchCommand;
            String fastaResponse = HTTPHelper.sendGET(efetchQuery);

            queryResults = parseFASTAResponse(fastaResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Instantiate PrimerInputHandler using the existing scanner instance
        PrimerInputHandler primerInputHandler = new PrimerInputHandler(scanner);
        primerInputHandler.getPrimersFromUser();

        // Print descriptions and sequences with labels after receiving primer inputs
        for (QueryResult result : queryResults) {
            System.out.println("Description: " + result.getDescription());
            System.out.println("Sequence: " + result.getSequence() + "\n");
        }

        scanner.close(); // Close the scanner here, at the end of all input operations
    }

    private static List<QueryResult> parseFASTAResponse(String fastaResponse) {
        List<QueryResult> results = new ArrayList<>();
        String[] records = fastaResponse.trim().split(">");
        
        // using f + r primer to make the error go away
        System.out.println(forwardPrimer + reversePrimer);
        
        // testing fasta maker
        String fastatest = StringFormatter.fastaGet(response);
        System.out.println(fastatest);

        // Since we are done with all input operations, close the Scanner
        primerInputHandler.closeScanner();
        for (String record : records) {
            if (!record.isEmpty()) {
                int firstLineEnd = record.indexOf("\n");
                String description = record.substring(0, firstLineEnd).trim();
                String sequence = record.substring(firstLineEnd).replaceAll("\\s+", "").trim();
                results.add(new QueryResult(description, sequence));
            }
        }
        return results;
    }
}
