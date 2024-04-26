package genomeProject;

public class QueryResult {
    private String qrDesc;
    private String qrSeq;
    private String qrAccID;
    private String qrTax;

    // Updated constructor to include accessionID and qr_species
    public QueryResult(String description, String sequence, String accessionID, String tax) {
        this.qrDesc = description;
        this.qrSeq = sequence;
        this.qrAccID = accessionID;
        this.qrTax = tax;
    }

    // Getter for description
    public String getDescription() {
        return qrDesc;
    }

    // Getter for sequence
    public String getSequence() {
        return qrSeq;
    }
    
    // Getter for accessionID
    public String getAccessionID() {
        return qrAccID;
    }
    
    // Getter for qrTax
    public String getTaxonomy() {
        return qrTax;
    }

    // Updated fastaGet() method to include accessionID, description, sequence, and qr_species
    public String fastaGet() {
        // Formats the output to include accessionID, description, sequence, and qr_species
        return ">" + qrAccID + " " + qrDesc + "\n" + ""+ qrSeq + "\n" + "Taxonomy: " + qrTax;
    }
}
