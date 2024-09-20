package health;


/**
 * The duke.Ui class handles all interactions with the user.
 * It is responsible for displaying messages and reading input from the user.
 */
public class Ui {

    private static final String MENTAL_HEALTH = "MentalHealth bot replies: \n\n";


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
     * Formats a message by surrounding it with separator lines.
     *
     * @param msg The message to be formatted.
     * @return The formatted message with separators.
     */
    public String formatMessage(String msg) {
        return MENTAL_HEALTH + msg;
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
