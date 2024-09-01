package Ui;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "_____________________________________________________________________\n";

    public String readCommand(Scanner s) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return s.nextLine()
                .trim()
                .replaceAll(" +", " ");
    }

    public void displayInitialResponse() {
       System.out.println("Hello! I'm Brock\n"
                + "What can I do for you?");
    }

    // Helper function to display response with lines
    public void displayResponse(String response) {
        System.out.println(HORIZONTAL_LINE
                + response
                + '\n'
                + HORIZONTAL_LINE);
    }
}
