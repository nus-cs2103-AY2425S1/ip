package botmanager;

import java.util.Scanner;

/**
 * Class to handle printing and reading input from user.
 *
 * @author dwsc37
 */
public class Ui {
    private Scanner scanner;

    private void printMessageWithDividers(String message) {
        String line = "______________________________________________________________________________________";
        System.out.println(line);
        System.out.print(message.indent(2));
        System.out.println(line);
    }

    /**
     * Prints a welcome message and initialises a scanner.
     */
    public void start() {
        String greetMessage = "Hello, I'm BotManager, your friendly task assistant!\n" +
                "What can I do for you? (Type 'help' to view all available commands)";
        printMessageWithDividers(greetMessage);
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a goodbye message and closes the scanner.
     */
    public void exit() {
        String goodbyeMessage = "Goodbye! Hope to see you again soon!";
        printMessageWithDividers(goodbyeMessage);
        scanner.close();
    }

    /**
     * Shows a file loading error message.
     */
    public void showLoadError() {
        String loadErrorMessage  = "File data/tasks.txt not found! Initialising empty file...";
        printMessageWithDividers(loadErrorMessage);
    }

    /**
     * Shows a file saving error message.
     */
    public void showSaveError() {
        String saveErrorMessage = "Tasks could not be saved! Please check that the data file has not been removed";
        printMessageWithDividers(saveErrorMessage);
    }

    /**
     * Prints the given message with indentations and dividers.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        printMessageWithDividers(message);
    }

    /**
     * Reads in an input from the user.
     *
     * @return User input, stripped of leading and trailing spaces.
     */
    public String readUserInput() {
        return scanner.nextLine().strip();
    }
}
