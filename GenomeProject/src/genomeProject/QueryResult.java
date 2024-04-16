package genomeProject;

public class QueryResult {
    private String description;
    private String sequence;
    private String accessionID;
    private String qr_species;

    // Updated constructor to include accessionID and qr_species
    public QueryResult(String description, String sequence, String accessionID, String qr_species) {
        this.description = description;
        this.sequence = sequence;
        this.accessionID = accessionID;
        this.qr_species = qr_species;
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
    
    // Getter for qr_species
    public String getSpeciesName() {
        return qr_species;
    }

    // Updated fastaGet() method to include accessionID, description, sequence, and qr_species
    public String fastaGet() {
        // Formats the output to include accessionID, description, sequence, and qr_species
        return ">" + accessionID + " " + description + "\n" + sequence + "\n" + "Species: " + qr_species;
    }
}
