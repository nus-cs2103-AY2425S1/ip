package ui;

import parser.Parser;
/**
 * Represents the user interface of the BeeBot application.
 * <p>
 * Provides methods to engage the user when the application is first opened and closed.
 * </p>
 */
public class Ui {

    /**
     * Constructs a Ui instance and displays a welcome message to the user.
     */
    public Ui() {
        Parser.speak("Hello! I'm BeeBot\n" + "What can I do for you?\n");
    }

    /**
     * Prints a farewell message to the console and ends the interaction with the user.
     */
    public static void exit() {
        Parser.speak("Bye. Hope to see you again!\n");
    }
}