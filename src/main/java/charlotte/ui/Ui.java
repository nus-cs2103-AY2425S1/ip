package charlotte.ui;

import java.util.Scanner;

/**
 * Handles the user interface interactions for the Charlotte chatbot.
 * The Ui class is responsible for reading user input and displaying messages and prompts to the user.
 * It provides methods to print messages, handle command input, and display error or other messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui instance with a new Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the console for formatting purposes.
     * This line is used to separate different sections of the UI output.
     */
    public String printLine() {
        return "________________________________________";
    }

    /**
     * Prints a welcome message to the console when the application starts.
     * This method displays a greeting and prompts the user to enter commands.
     */
    public String printWelcome() {
        return printLine() + "\nHello! I'm Charlotte!\nWhat can I do for you?\n" + printLine();
    }

    /**
     * Prints an exit message to the console when the application is about to close.
     * This method displays a goodbye message to the user.
     */
    public String printExit() {
        return printLine() + "\nBye. Hope to see you again soon!\n" + printLine();
    }

    /**
     * Displays an error message indicating that no data file was found.
     * This method is called when the application fails to locate the existing data file.
     */
    public String showLoadingError() {
        return "No existing data file found";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return printLine() + "\n" + message + "\n" + printLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public String showMessage(String message) {
        return printLine() + "\n" + message + "\n" + printLine();
    }
}
