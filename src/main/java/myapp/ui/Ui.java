package myapp.ui;
import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user, including reading commands and displaying messages.
 * It provides methods to read user input and show responses, errors, and other messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance and initializes the {@code Scanner} for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user input.
     *
     * @return A {@code String} representing the user's input command, trimmed of leading and trailing whitespace.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a welcome message to the user.
     * This message is shown when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Ruby\nWhat can I do for you?");
    }

    /**
     * Displays a response message to the user.
     *
     * @param response A {@code String} representing the message to be displayed to the user.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error A {@code String} representing the error message to be displayed to the user.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays an error message indicating that there was an issue loading tasks from the file.
     * This method is typically used when the application encounters problems during file operations.
     */
    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks from the file.");
    }
}
