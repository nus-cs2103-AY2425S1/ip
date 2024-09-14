package mysutong;

import mysutong.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the user interface for the MySutong application.
 * This class manages all user interactions and display functionalities.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in); // Scanner to read user input.

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void showWelcome() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm mysutong.MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    /**
     * Displays a horizontal line for separating sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is a problem loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Reads a command from the user input.
     *
     * @return the command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a goodbye message to the user when they exit the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks currently in the TaskList.
     *
     * @param tasks the TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
        }
        showLine();
    }

    public void showSearchResults(List<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        showLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
