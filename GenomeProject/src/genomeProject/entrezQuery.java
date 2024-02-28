package genomeProject;
import java.util.Scanner;

public class entrezQuery{
    public static String main(Scanner sc){ // Accept Scanner as an argument
        System.out.println("Step 1: Reference Database. Would you like to (a) build an Entrez query or (b) submit your own? Please enter 'a' or 'b'");
        String dbOpt = sc.nextLine();
        if(dbOpt.equals("a")){
            String terms = "";
            System.out.println("Please enter your taxon of interest (ex. 'Apis mellifera', 'Hymenoptera')");
            String organismEntry = sc.nextLine();
            System.out.println("Please enter your gene of interest (ex. 'COI', 'Cox1')");
            String geneEntry = sc.nextLine();
            System.out.println("Do you have additional search terms to add? (ex. 'NOT partial') Please enter 'y' for yes and 'n' for no.");
            String termOpt = sc.nextLine();
            if(termOpt.equals("y")) {
                System.out.println("Please enter your additional search terms");
                terms = sc.nextLine();
                // No scanner closing here
            }
            String organism = organismEntry.concat(" [Organism] ");
            String gene = geneEntry.concat(" [Gene Name] ");
            String esearchString = organism.concat(gene + terms);
            esearchString = StringFormatter.spaceReplace(esearchString);
            return(esearchString);
        } else {
            System.out.println("Please enter your Entrez query");
            String esearchString = sc.nextLine();
            // No scanner closing here
            esearchString = StringFormatter.spaceReplace(esearchString);
            return(esearchString);
        }
    }
}
