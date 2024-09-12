package tomo;

import java.util.Scanner;

/**
 * Interacts with user through input/output
 */

public class Ui {
    private Scanner scanner;

    /**
     * The constructor of the Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        open();
    }

    /**
     * Reads command from user
     * 
     * @return
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Sends output to user
     * 
     * @param obj
     */
    public void println(Object obj) {
        System.out.println(obj);
    }

    /**
     * Starts the conversation
     */
    private void open() {
        println("What's up, it's ToMo here!");
    }

    /**
     * Asks if the bot can help the user
     */
    public void help() {
        println("How can I help you?");
    }

    /**
     * Finishes the conversation
     */
    public void close() {
        println("Bye, see you later!");
        scanner.close();
    }
}
