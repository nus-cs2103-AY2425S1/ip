package evan.exception;

/**
 * Represents an exception that is thrown when the tasks cannot be saved.
 */
public class SavingException extends Exception {
    /**
     * Instantiates a SavingException object.
     *
     * @param message Message that describes why the tasks couldn't be saved.
     */
    public SavingException(String message) {
        super("Oh no! There was an error when saving the tasks: " + message);
    }
}
