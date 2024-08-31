package gray;

import java.util.Scanner;

/**
 * Represents a user interface for the chatbot.
 */
public class Ui {

    private final Scanner reader;

    /**
     * Constructs a Ui object for the chatbot to display messages.
     */
    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Prints the given text message with a pre-given style.
     *
     * @param text
     */
    public void say(String text) {
        System.out.println("\t____________________________________________________________");
        System.out.print("\t");
        System.out.println(text.replace("\n", "\n\t"));
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        say("""
                Hello! I'm Gray.
                What can I do for you?""");
    }

    /**
     * Displays an error indicating that the tasks file cannot be loaded.
     */
    public void showLoadingError() {
        say("Error loading save file.");
    }

    /**
     * Displays the error message with a pre-given style.
     *
     * @param message
     */
    public void showError(String message) {
        say(message);
    }

    /**
     * Reads a line from stdin.
     *
     * @return
     */
    public String readCommand() {
        return reader.nextLine();
    }
}
