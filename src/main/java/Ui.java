import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui instance with a new Scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greeting() {
        System.out.println("Hello there! ☺ I'm OLLIE ☺");
        System.out.println("What can I do for you today? ☺");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public void exit() {
        System.out.println("Bye. Have a great day. ☺");
        System.out.println("Hope to see you again soon! ☺");
    }

    /**
     * Shows the error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Reads the next command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}