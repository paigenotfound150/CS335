package genomeProject;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateBarcodeFile {
	
	public static void convertToFile(ArrayList<Barcode> barcodes) {
		try {
		      File myFile = new File("./src/barcodes.txt");
		      myFile.createNewFile(); 
		      writeBarcodesToFile(myFile, barcodes);
		      
		    } catch (IOException e) {
		      System.out.println("An error creating the file barcodes.txt occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void writeBarcodesToFile(File myFile, ArrayList<Barcode> barcodes) {
		try {
		      FileWriter myWriter = new FileWriter("./src/barcodes.txt");
		      PrintWriter printWriter = new PrintWriter(myWriter);
		      
		      for (Barcode barcode : barcodes) {
		    	    printWriter.println("> " + barcode.getDescription());
		    	    printWriter.println("+");
		    	    printWriter.println(barcode.getMatchedSequence());
		    	    printWriter.println(barcode.getTaxonomy());
		    	    printWriter.println("");
		    	    printWriter.println("");
		      }

	    	  printWriter.close();
	    	  myWriter.close();
		      System.out.println("Successfully wrote to file");
		      
		    } catch (IOException e) {
		      System.out.println("An error creating FileWriter occurred.");
		      e.printStackTrace();
		    }
	}
}