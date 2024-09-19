package shenhe;

import java.util.Scanner;

/**
 * Represents the user interface for the application.
 * <p>
 * The {@code Ui} class handles interactions with the user through the command line.
 * It provides methods for displaying messages and reading user input.
 * </p>
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} object.
     * <p>
     * Initializes the {@code Scanner} object for reading user input from the command line.
     * </p>
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     * <p>
     * Prints a greeting and introduction message to the user.
     * </p>
     */
    public String showWelcome() {
        String welcome = """
                Hello, Ying. I'm Shenhe. The assumption that every person has somewhere to call home is
                naive. I got used to living in the mountains alongside the birds and beasts a long time ago.
                You, are not the only traveller, but the most interesting one.
                What do you want today?""";
        return welcome;
    }

    /**
     * Displays a line separator.
     * <p>
     * Prints a line of underscores to separate different sections of the user interface.
     * </p>
     */
    public String showLine() {
        String line = "____________________________________________________________";
        return line;
    }

    /**
     * Displays a goodbye message to the user.
     * <p>
     * Prints a farewell message to the user when they exit the application.
     * </p>
     */
    public String showGoodbye() {
        String goodbye = "Bye traveller. Hope to see you again soon.";
        return goodbye;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * <p>
     * Prints a message to the user confirming that a task has been marked as completed.
     * </p>
     */
    public String showMarkMessage() {
        String markMessage = "Nice! I've marked this task as done:";
        return markMessage;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     * <p>
     * Prints a message to the user confirming that a task has been marked as not completed.
     * </p>
     */
    public String showUnmarkMessage() {
        String unmarkMessage = "OK, I've marked this task as not done yet:";
        return unmarkMessage;
    }

    /**
     * Displays a message indicating that a task has been added.
     * <p>
     * Prints a message to the user confirming that a task has been successfully added to the list.
     * </p>
     */
    public String showAddTaskMessage() {
        String addTaskMessage = "Got it. I've added this task:";
        return addTaskMessage;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     * <p>
     * Prints a message to the user confirming that a task has been removed from the list.
     * </p>
     */
    public String showDeleteMessage() {
        String deleteMessage = "Noted. I've removed this task:";
        return deleteMessage;
    }

    /**
     * Displays a list of tasks.
     * <p>
     * Prints a message to the user showing all the tasks currently in the list.
     * </p>
     */
    public String showTasksMessage() {
        String tasksMessage = "Here are the tasks in your list:";
        return tasksMessage;
    }

    /**
     * Displays a message indicating that the following tasks in the list match the user's search criteria.
     * This method is typically called when a search or filter operation is performed, and matching tasks
     * are about to be listed.
     */
    public String showMatchingMessage() {
        String matchingMessage = "Here are the matching tasks in your list:";
        return matchingMessage;
    }


    /**
     * Reads a command from the user.
     * <p>
     * Reads a line of input from the command line.
     * </p>
     *
     * @return The line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     * <p>
     * Prints a message indicating that an error has occurred.
     * </p>
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a loading error message.
     * <p>
     * Prints a message to the user indicating that there was an error loading tasks from the file.
     * </p>
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
}
