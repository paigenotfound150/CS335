package genomeProject;

public class QueryResult {
    private String description;
    private String sequence;
    private String accessionID;

    // Updated constructor to include accessionID
    public QueryResult(String description, String sequence, String accessionID) {
        this.description = description;
        this.sequence = sequence;
        this.accessionID = accessionID;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for sequence
    public String getSequence() {
        return sequence;
    }
    
    // Getter for accessionID
    public String getAccessionID() {
        return accessionID;
    }
    
    // Updated fastaGet() method to include accessionID
    public String fastaGet() {
        // Formats the output to include accessionID, description, and sequence
        return ">" + accessionID + " " + description + "\n+\n" + sequence;
    }
}
