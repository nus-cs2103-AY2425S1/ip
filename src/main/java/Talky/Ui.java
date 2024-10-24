package Talky;

import java.util.Scanner;

/**
 * Class that acts as UI for the chatbot.
 */
public class Ui {
    Scanner sc;

    /**
     * Constructor to initialize the UI class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns string of next command.
     *
     * @return Next command.
     */
    public String getCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints content with seperator around it.
     *
     * @param content Content to wrap seperator around.
     */
    public void printSeperator(String content) {
        final String SEPERATOR = "---------------------------------------";
        System.out.println(SEPERATOR);
        System.out.println(content);
        System.out.println(SEPERATOR);
    }
}
