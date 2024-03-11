package genomeProject;

public class QueryResult {
    private String description;
    private String sequence;

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
}
