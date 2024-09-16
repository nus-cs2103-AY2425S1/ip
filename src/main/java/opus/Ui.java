package opus;

import java.util.Scanner;

/**
 * Handles interactions with the user, including displaying messages
 * and reading input commands.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes a Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Opus");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a list of commands that the user can use.
     */
    public void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("1. list - List all tasks");
        System.out.println("2. mark [task number] - Mark a task as done");
        System.out.println("3. delete [task number] - Delete a task");
        System.out.println("4. deadline [task] /by [end-date] - Add a deadline by the end date");
        System.out.println("5. event [task] /from [start-date] /to [end-date] - Add an event with start and end dates");
        System.out.println("6. todo [task] - Add a Todo");
        System.out.println("7. bye - Exit the application");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
