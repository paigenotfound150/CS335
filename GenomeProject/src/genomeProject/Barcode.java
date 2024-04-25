package genomeProject;

public class Barcode {
    private String barcAccID;
    private String barcDesc;
    private String barcSeq;
    private String barcTax;
    private int numberBasePairs; 

    public Barcode(String accessionID, String description, String matched_sequence, int numberBasePairs, String taxonomy) {
    	this.barcAccID = accessionID;
        this.barcDesc = description;
        this.barcSeq = matched_sequence;
        this.numberBasePairs = numberBasePairs;
        this.barcTax = taxonomy;
    }
    
    public String getaccessionID() {
        return barcAccID;
    }

    public String getDescription() {
        return barcDesc;
    }
    
    public String getTaxonomy() {
    	return barcTax;
    }

    public String getMatchedSequence() {
        return barcSeq;
    }
    
    public int getNumberBasePairs() {
        return numberBasePairs;
    }
    
    public String getInfo() {
        return barcAccID + " " + barcTax + " " + barcDesc + " " + "Base pairs: " + numberBasePairs + "Barcode" + barcSeq;
    }
}