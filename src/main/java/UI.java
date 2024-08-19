import java.util.Scanner;

/**
 * UI handles all user interface interactions for the Mira chatbot,
 * including reading commands, echoing user inputs, and displaying messages.
 */
public class UI {
    /**
     * Scanner object to read user input from the console.
     */
    private final Scanner scanner;

    public UI() {
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
     * Echoes the user's command by printing it within formatted lines.
     *
     * @param command The command to be echoed.
     */
    public void echoCommand(String command) {
        printLine();
        System.out.println(command);
        printLine();
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
     * Prints a separator line used for formatting the UI output.
     */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
