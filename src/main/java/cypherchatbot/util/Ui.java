package cypherchatbot.util;

import java.util.Scanner;

import cypherchatbot.Cypher;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 *  The UI class handles all interactions with the user for the Cypher Chat Bot Application such as
 *  reading user commands from the console, displaying various outputs depending on the command. All user
 *  interaction in the chatbot occurs in this class.
 * */
public class Ui {
    private Scanner scanner;

    private Cypher cypher;
    /**
     * Initializes a new scanner instance for reading user input from the console.
     * */
    public Ui(Cypher cypher) {
        this.scanner = new Scanner(System.in);
        this.cypher = cypher;
    }

    /**
     * Outputs a message to the user with a custom line divider before and after the message.
     *
     * @param s The String message to be displayed to the user.
     */
    public void output(String s) {
        Ui.lineBreak();
        System.out.println(s);
        Ui.lineBreak();
        this.cypher.sendDialog(s);
    }

    /**
     * Prints a custom line divider consisting of dashes to the console that is
     * used to separate different sections of the output for clarity.
     */
    public static void lineBreak() {
        System.out.println("-------------------------------------------------"
                + "-------------------------------------------------------------");
    }

    /**
     * Reads in the command entered by the user.
     *
     * @return The String command that the user gave as input
     */

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        lineBreak();
        System.out.println("Hello! I am Cypher\nWhat can I do for you!");
        lineBreak();
        this.cypher.sendDialog("Hello! I am Cypher\nWhat can I do for you!");

    }

    /**
     * Displays a goodbye message to the user. The method being called indicates
     * that the application has ended and the scanner is closed.
     */
    public void goodBye() {
        this.scanner.close();
        this.cypher.sendDialog("Bye! See you again. This application will close in 3 seconds");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished((e) -> Platform.exit());
        delay.play();
    }

    /**
     * Displays an error message if there was an issue with the specified file path.
     *
     * @param filePath The file path that could not be loaded/found/created.
     */

    public void showLoadingError(String filePath) {
        lineBreak();
        System.out.println(String.format("Given filepath [%s] does not work. Please try again", filePath));
        lineBreak();
        this.cypher.sendDialog(String.format("Given filepath [%s] does not work. Please try again", filePath));
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The String error message to be displayed.
     */

    public void showError(String e) {
        lineBreak();
        System.out.println(e);
        lineBreak();
        this.cypher.sendDialog(e);
    }






}
