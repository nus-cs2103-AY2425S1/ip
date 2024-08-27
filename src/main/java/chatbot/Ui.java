package chatbot;

import java.util.Scanner;

/**
 * Represents the UI component of the chatbot
 * Contains functionalities that interact with the user
 */
public class Ui {
    private final Scanner input;
    private boolean isRunning;

    /**
     * Constructor for the Ui class
     * Initialises the input and isRunning variables
     */
    public Ui() {
        this.input = new Scanner(System.in);
        this.isRunning = true;
    }

    /**
     * Prints out a welcome message
     * To be used whenever the chatbot is run
     */
    public void sayHi() {
        System.out.println("Hello, I'm Bobby.Bobby");
        System.out.println("What can I do for you?");
    }

    /**
     * Checks if the chatbot is still supposed to be running
     * @return Boolean value representing the run state of the chatbot
     */
    public boolean runStatus() {
        return this.isRunning;
    }

    /**
     * Ends the chatbot run cycle
     */
    public void endRun() {
        this.isRunning = false;
    }

    /**
     * Gets the next line of user input
     * @return The string representation of the user's input
     */
    public String getInput() {
        return this.input.nextLine();
    }

}
