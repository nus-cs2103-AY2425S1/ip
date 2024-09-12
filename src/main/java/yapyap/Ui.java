package yapyap;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles the user interface interactions, including printing messages and reading user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the start message when the application is launched.
     */
    public void printStartMessage() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    /**
     * Prints the end message when the application is closing.
     */
    public void printEndMessage() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

    /**
     * Displays the list of tasks in the provided TaskList.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("________________________________");
    }

    /**
     * Displays the list of matching tasks based on the search keyword.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println("There are no matching tasks!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
            }
        }
        System.out.println("________________________________");
    }

    /**
     * Prints an error message indicating that there was an issue loading tasks.
     */
    public void printLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Prints a message indicating that an invalid command was entered by the user.
     */
    public void printInvalidCommandMessage() {
        System.out.println("________________________________");
        System.out.println("IDK what you are yapping about!!");
        System.out.println("________________________________");
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}

