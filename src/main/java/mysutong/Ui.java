package mysutong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the user interface for the MySutong application.
 * This class manages all user interactions and display functionalities.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in); // Scanner to read user input.
    private List<String> responses;  // List to store responses for later retrieval.

    /**
     * Constructs a new Ui instance and initializes the response list.
     */
    public Ui() {
        responses = new ArrayList<>();
    }

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void showWelcome() {
        String horizontalLine = "____________________________________________________________";
        responses.add(horizontalLine);
        responses.add("Hello! I'm mysutong.MySutong");
        responses.add("What can I do for you?");
        responses.add(horizontalLine);
    }

    /**
     * Adds a horizontal line for separating sections of output.
     */
    public void showLine() {
        responses.add("____________________________________________________________");
    }

    /**
     * Adds an error message to the responses when there is a problem loading tasks from the file.
     */
    public void showLoadingError() {
        responses.add("Error loading tasks from file.");
    }

    /**
     * Reads a command from the user input (still needed for CLI functionality).
     *
     * @return the command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Adds a goodbye message to the responses when the user exits the application.
     */
    public void showGoodbye() {
        responses.add("Bye. Hope to see you again soon!");
    }

    /**
     * Adds the list of tasks currently in the TaskList to the responses.
     *
     * @param tasks the TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        responses.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            responses.add((i + 1) + ". " + tasks.getTasks().get(i));
        }
        showLine();
    }

    /**
     * Adds the search results to the responses.
     *
     * @param tasks the list of tasks that match the search criteria.
     */
    public void showSearchResults(List<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            responses.add("No matching tasks found.");
        } else {
            responses.add("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                responses.add((i + 1) + ". " + tasks.get(i));
            }
        }
        showLine();
    }

    /**
     * Adds a message to the responses.
     *
     * @param message the message to be added.
     */
    public void showMessage(String message) {
        responses.add(message);
    }

    /**
     * Adds an error message to the responses.
     *
     * @param message the error message to be added.
     */
    public void showError(String message) {
        responses.add("Error: " + message);
    }

    /**
     * Returns all the responses as a single concatenated string.
     *
     * @return the concatenated response string.
     */
    public String getResponse() {
        return String.join("\n", responses);
    }

    /**
     * Clears the current responses list.
     * This is useful if you want to start fresh for a new response session.
     */
    public void clearResponses() {
        responses.clear();
    }
}
