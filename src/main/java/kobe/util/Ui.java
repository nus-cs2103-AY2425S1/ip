package kobe.util;

import kobe.task.TaskList;

import java.util.Scanner;

/**
 * Handles the user interface for the Kobe chatbot application.
 * The Ui class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    /** Scanner to read user input from the command line. */
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";
        System.out.println("____________________________________________________________");
        System.out.println("Greetings! I am Kobe Bryant. \n" + logo);
        System.out.println("How can I help you, my man?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user's command input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line as a visual separator in the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message indicating that there was an error loading the task list.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! There was an error loading your task list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the goodbye message when the application is exiting.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! My man.");
    }

    /**
     * Displays all tasks in the task list to the user.
     *
     * @param tasks The TaskList object containing all tasks.
     */
    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is currently empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }
}
