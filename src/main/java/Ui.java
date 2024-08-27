import java.util.Scanner;

/**
 * Ui handles all user interface interactions for the Mira chatbot,
 * including reading commands, echoing user inputs, and displaying messages.
 */
public class Ui {
    /**
     * Scanner object to read user input from the console.
     */
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user within formatted lines.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints a separator line used for formatting the Ui output.
     */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
