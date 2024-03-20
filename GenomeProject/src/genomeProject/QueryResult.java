package genomeProject;

public class QueryResult {
    private static String description;
    private static String sequence;

    // Constructor
    public QueryResult(String description, String sequence) {
        this.description = description;
        this.sequence = sequence;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for sequence
    public String getSequence() {
        return sequence;
    }
    
 // put definition and sequence together
 	public static String fastaGet() {
 		String fasta = description + "\n+\n" + sequence;
 		return(fasta);
 	}
}
