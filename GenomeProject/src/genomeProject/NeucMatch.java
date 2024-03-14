package genomeProject;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class NeucMatch{
	public static HashMap<String, ArrayList<String>> makeDictionary(){
		HashMap<String, ArrayList<String>> neucDictionary = new HashMap<>();
		ArrayList<String> aMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N"));
		neucDictionary.put("A", aMatch);
		ArrayList<String> tMatch = new ArrayList<String>(List.of("A", "R", "M", "W", "D", "H", "V", "N"));
		neucDictionary.put("T", tMatch);
		ArrayList<String> cMatch = new ArrayList<String>(List.of("G", "R", "K", "S", "B", "D", "V", "N"));
		neucDictionary.put("C", cMatch);
		ArrayList<String> gMatch = new ArrayList<String>(List.of("C", "Y", "M", "S", "B", "H", "V", "N"));
		neucDictionary.put("G", gMatch);
		ArrayList<String> uMatch = new ArrayList<String>(List.of("A", "R", "M", "W", "D", "H", "V", "N"));
		neucDictionary.put("U", uMatch);
		ArrayList<String> rMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "C", "M", "S", "V"));
		neucDictionary.put("R", rMatch);
		ArrayList<String> yMatch = new ArrayList<String>(List.of("G", "R", "K", "S", "B", "D", "V", "N", "A", "R", "M", "W", "H"));
		neucDictionary.put("Y", yMatch);
		ArrayList<String> kMatch = new ArrayList<String>(List.of("C", "Y", "M", "S", "B", "H", "V", "N", "A", "R", "M", "W", "D"));
		neucDictionary.put("K", kMatch);
		ArrayList<String> mMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "G", "R", "K", "S", "V"));
		neucDictionary.put("M", mMatch);
		ArrayList<String> sMatch = new ArrayList<String>(List.of("G", "R", "K", "S", "B", "D", "V", "N", "C", "Y", "M", "H"));
		neucDictionary.put("S", sMatch);
		ArrayList<String> wMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "A", "R", "M", "V"));
		neucDictionary.put("W", wMatch);
		ArrayList<String> bMatch = new ArrayList<String>(List.of("G", "R", "K", "S", "B", "D", "V", "N", "Y", "M", "H", "A", "R", "W"));
		neucDictionary.put("B", bMatch);
		ArrayList<String> dMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "C", "M", "S", "V", "A", "R", "M"));
		neucDictionary.put("D", dMatch);
		ArrayList<String> hMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "G", "R", "S", "V", "A", "R", "M"));
		neucDictionary.put("H", hMatch);
		ArrayList<String> vMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "G", "R", "S", "V", "C", "M"));
		neucDictionary.put("V", vMatch);
		ArrayList<String> nMatch = new ArrayList<String>(List.of("T", "U", "Y", "K", "W", "B", "D", "H", "N", "C", "M", "S", "V", "A", "R", "M", "G"));
		neucDictionary.put("N", nMatch);
		return(neucDictionary);
	}
	
	public static HashMap<String, ArrayList<String>> makeReversePrimerDictionary() {
		HashMap<String, ArrayList<String>> neucDictionary = new HashMap<>();
		ArrayList<String> aMatch = new ArrayList<String>(List.of("T"));
		neucDictionary.put("A", aMatch);
		ArrayList<String> tMatch = new ArrayList<String>(List.of("A"));
		neucDictionary.put("T", tMatch);
		ArrayList<String> cMatch = new ArrayList<String>(List.of("G"));
		neucDictionary.put("C", cMatch);
		ArrayList<String> gMatch = new ArrayList<String>(List.of("C"));
		neucDictionary.put("G", gMatch);
		return neucDictionary;
	}
		
	public static String getMatch(HashMap<String, ArrayList<String>> dictionary, String letterToMatch){
		if(dictionary.containsKey(letterToMatch)){
			String match = dictionary.get(letterToMatch).toString();
			return(match);
		} else {
		throw new Error("Neucleotide to be matched not found.");
		}
	}
}


