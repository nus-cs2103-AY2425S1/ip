import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It is responsible for displaying messages and reading input from the user.
 */
public class Ui {
    private final static String separator = "------------------------------";
    private final static String greetingMessage = "Hello! I'm your friendly ChatBot assistant called MentalHealth :)\n" +
            "What can I do for you?";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon! If you ever need help don't forget to reach out :)";

    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Displays an error message indicating that there is no data in the file.
     */
    public void showLoadingError() {
        System.out.println("No data in file.");
    }

    /**
     * Displays a separator line to visually separate sections of output.
     */
    public void showSeparator() {
        System.out.println(separator);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        System.out.println(formatMessage(greetingMessage));
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void goodbye() {
        System.out.println(formatMessage(goodbyeMessage));
    }

    /**
     * Formats a message by surrounding it with separator lines.
     *
     * @param msg The message to be formatted.
     * @return The formatted message with separators.
     */
    public String formatMessage(String msg) {
        return separator + "\n" + msg + "\n" + separator;
    }

    /**
     * Reads a command from the user.
     *
     * @param scanner A Scanner object used to read input from the user.
     * @return The command entered by the user.
     */
    public String readCommand(Scanner scanner) {
        System.out.println("Enter your message:");
        return scanner.nextLine();
    }
}
