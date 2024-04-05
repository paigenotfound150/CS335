package genomeProject;

public class QueryMatch {
    private String accessionID;
    private String description;
    private String matched_sequence;
    private int numberBasePairs; 

    public QueryMatch(String accessionID, String description, String matched_sequence, int numberBasePairs) {
    	this.accessionID = accessionID;
        this.description = description;
        this.matched_sequence = matched_sequence;
        this.numberBasePairs = numberBasePairs;
    }
    
    public String getaccessionID() {
        return accessionID;
    }

    public String getDescription() {
        return description;
    }

    public String getMatchedSequence() {
        return matched_sequence;
    }
    
    public int getNumberBasePairs() {
        return numberBasePairs;
    }
    
    public String getInfo() {
        return accessionID + " " + description + " " + "Base pairs: " + numberBasePairs;
    }
}