package cypherchatbot.util;

import java.util.Scanner;
/**
 *  The UI class handles all interactions with the user for the Cypher Chat Bot Application such as
 *  reading user commands from the console, displaying various outputs depending on the command. All user
 *  interaction in the chatbot occurs in this class.
 * */
public class Ui {
    Scanner scanner;

    /**
     * Initializes a new scanner instance for reading user input from the console.
     * */
    public Ui () {
        this.scanner =  new Scanner(System.in);
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
    }

    /**
     * Prints a custom line divider consisting of dashes to the console that is
     * used to separate different sections of the output for clarity.
     */
    public static void lineBreak() {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
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
    }

    /**
     * Displays a goodbye message to the user. The method being called indicates
     * that the application has ended and the scanner is closed.
     */
    public void goodBye() {
        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
        this.scanner.close();
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
    }






}
