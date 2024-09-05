package duke;


/**
 * The duke.Ui class handles all interactions with the user.
 * It is responsible for displaying messages and reading input from the user.
 */
public class Ui {
    private static final String SEPARATOR = "------------------------------";
    private static final String GREETING_MESSAGE = "Hello! "
            + "I'm your friendly ChatBot assistant called duke.MentalHealth :)\n"
            + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. "
            + "Hope to see you again soon! "
            + "If you ever need help don't forget to reach out :)";


    /**
     * Constructs a duke.Ui object.
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
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        System.out.println(formatMessage(GREETING_MESSAGE));
    }

    /**
     * Formats a message by surrounding it with separator lines.
     *
     * @param msg The message to be formatted.
     * @return The formatted message with separators.
     */
    public String formatMessage(String msg) {
        return SEPARATOR + "\n" + msg + "\n" + SEPARATOR;
    }

    /**
     * Reads a command from the user.
     *
     * @param msg the message entered by the user.
     * @return The command entered by the user.
     */
    public String readCommand(String msg) {
        return msg;
    }
}
