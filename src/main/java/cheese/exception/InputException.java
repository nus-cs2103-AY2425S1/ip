package cheese.exception;

/**
 * Exception for user inputs with incorrect format.
 */
public class InputException extends CheeseException {
    /**
     * Creates new InputException.
     *
     * @param format correct format for input.
     * @param message Additional information.
     */
    public InputException(String format, String message) {
        super("Correct format: " + format + "\n" + message);
    }
}
