package ui;

import java.util.Scanner;

public class CommandLineUI {

    private Scanner sc;

    public CommandLineUI() {
        // Initialize scanner
        sc = new Scanner(System.in); 
    }

    public void showDivider() {
        System.out.println("\t____________________________________________________________");
    }

    public void greet() {
        showDivider();

        speakLine("Bone-jaw! I'm Oui Oui Baguette.");
        speakLine("What can I do for you? Oui Oui");

        showDivider();
    }
    

    public void farewell() {
        showDivider();

        speakLine("Bye. Hope to see you soon! Oui Oui");

        showDivider();
    }

    
    public String readCommand() {
        String input = sc.nextLine();

        return input;
    }


    /**
     * Prints message formatted for a CLI chat response
     *
     * @param s response message
     */
    public void speakLine(String s) {
        speakLines(new String[]{s});
    }


    /**
     * Prints message formatted for a CLI chat response
     *
     * @param strs list of response message
     */
    public void speakLines(String[] strs) {
        for (String s : strs) {
            System.out.println("\t " + s);
        }
    }
}
