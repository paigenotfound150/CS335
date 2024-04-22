package genomeProject;
import java.util.Scanner;

public class EntrezQuery{
	public static String getUserEntrezQuery(Scanner sc){
		String terms = "";
		System.out.println("Step 1: Reference Database.");
		System.out.println("Please enter your taxon of interest (ex. 'Apis mellifera', 'Bombus')");
		String organismEntry = sc.nextLine();
		System.out.println("Do you have additional search terms to add? (ex. 'NOT partial') Please enter 'y' for yes and 'n' for no.");
		String termOpt = sc.nextLine();
		if(termOpt.equals("y")) {
			System.out.println("Please enter your additional search terms");				
			terms = sc.nextLine();
		}
		String query = (organismEntry.concat(" [Organism]&COI[Gene Name]")).concat(terms);
		String esearchString = StringFormatter.spaceReplace(query);
		return(esearchString);
	}
}