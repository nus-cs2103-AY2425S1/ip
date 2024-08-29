package serenity;

import java.util.Scanner;

/**
 * Represents user interface that handles user interactions.
 */

public class Ui {

    private static final String horizontalLine = "__________________________________________";

    protected Scanner sc;

    /**
     * Constructs Ui object
     */
    public Ui () {
        this.sc = new Scanner(System.in);
    }

    /**
     * Display welcome message.
     */
    public void showWelcome() {
        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    /**
     * Display goodbye message and close scanner.
     */
    public void showGoodbye() {
        this.sc.close();
        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    /**
     * Get command from user input.
     * @return User's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Display message of loading error.
     */
    public void showLoadingError() {
        System.out.println("Error: File cannot be loaded.");
    }

    /**
     * Display message to user.
     *
     * @param message Message to be displayed.
     */

    public void showMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    /**
     * Display TaskList to user.
     *
     * @param tasks TaskList to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println(tasks.toString());
        System.out.println(horizontalLine);
    }
}
