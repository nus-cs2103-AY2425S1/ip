package barcus.ui;

import java.util.Scanner;

/**
 * Class to handle displaying to the user and accepting user input
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui object, creates a scanner
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints message s with appropriate string in front
     * @param s
     */
    public void talk(String s) {
        System.out.println("Barcus: " + s);
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        talk("Beep bop! Hello I am Barcus, ready to be of assistance!\n" +
                "Write 'bye' to leave!\n");
    }

    /**
     * Prints goodbye message
     */
    public void showGoodbye() {
        talk("Alright, good talk!");
    }

    /**
     * Prints error message
     * @param e
     */
    public void showError(String e) {
        talk("Uh oh, " + e);
    }

    /**
     * Gets user input
     * @return user input
     */
    public String readCommand() {
        System.out.print("You: ");
        return this.scanner.nextLine();
    }

}
