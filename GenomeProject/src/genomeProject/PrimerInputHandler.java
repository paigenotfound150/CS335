package genomeProject;

import java.util.Scanner;

public class PrimerInputHandler {
    private String forwardPrimer;
    private String reversePrimer;
    private Scanner sc;

    // Modify constructor to accept Scanner object
    public PrimerInputHandler(Scanner sc) {
        this.sc = sc; // Use the passed Scanner object
    }

    public void getPrimersFromUser() {
        System.out.println("Please enter your forward primer (5' to 3' on + strand)");
        forwardPrimer = sc.nextLine();
        while (!isValidPrimer(forwardPrimer)) {
            System.out.println("Invalid input. Primer must only contain A, T, C, or G. Please try again.");
            forwardPrimer = sc.nextLine();
        }

        System.out.println("Please enter your reverse primer (5' to 3' on - strand):");
        reversePrimer = sc.nextLine();
        while (!isValidPrimer(reversePrimer)) {
            System.out.println("Invalid input. Primer must only contain A, T, C, or G. Please try again.");
            reversePrimer = sc.nextLine();
        }
    }

    private boolean isValidPrimer(String primer) {
        return primer.matches("[ATCG]+");
    }

    public String getForwardPrimer() {
        return forwardPrimer;
    }

    public String getReversePrimer() {
        return reversePrimer;
    }

    // Removed the closeScanner method as it's no longer necessary here
}
