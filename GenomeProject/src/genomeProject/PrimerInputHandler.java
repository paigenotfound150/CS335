package genomeProject;

import java.util.Scanner;

public class PrimerInputHandler {
    private String forwardPrimer;
    private String reversePrimer;

    public void getPrimersFromUser(Scanner sc) {
        // Prompt user for forward primer
        System.out.println("Please enter your forward primer (5' to 3' on + strand)");
        forwardPrimer = sc.nextLine();
        while (!isValidPrimer(forwardPrimer)) {
            System.out.println("Invalid input. Primer must only contain A, T, C, or G. Please try again.");
            forwardPrimer = sc.nextLine();
        }

        // Prompt user for reverse primer
        System.out.println("Please enter your reverse primer (5' to 3' on - strand):");
        reversePrimer = sc.nextLine();
        while (!isValidPrimer(reversePrimer)) {
            System.out.println("Invalid input. Primer must only contain A, T, C, or G. Please try again.");
            reversePrimer = sc.nextLine();
        }
        
        // Do not close the scanner here
    }

    private boolean isValidPrimer(String primer) {
        // Check if the primer sequence contains only A, T, C, or G
        return primer.toUpperCase().matches("[ATCG]+");
    }

    public String getForwardPrimer() {
        return forwardPrimer;
    }

    public String getReversePrimer() {
        return reversePrimer;
    }
}