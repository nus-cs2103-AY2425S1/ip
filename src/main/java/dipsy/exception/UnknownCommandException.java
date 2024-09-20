package dipsy.exception;

/**
 * Represents an exception that is thrown when an unrecognized or unknown command is encountered.
 * This exception provides the user with a helpful usage guide for valid commands.
 */
public class UnknownCommandException extends Exception {

    /**
     * Returns a detailed error message indicating that the command is unrecognized.
     * The message instructs the user to click the Help button for more information.
     *
     * @return The error message with instructions to use the Help button.
     */
    @Override
    public String getMessage() {
        return "Dipsy didn't recognize that command.\n\nPlease click the '?' button in the "
                + "bottom-right corner to see a list of available commands and their usages.";
    }
}
