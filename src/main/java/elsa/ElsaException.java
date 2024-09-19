package elsa;

/**
 * Represents exceptions specific to elsa.ui.Elsa.
 * This custom exception is thrown when an error related to elsa.ui.Elsa's operations occur.
 *
 * @author Aaron
 */
public class ElsaException extends Exception {
    /**
     * Constructs a new ElsaException with the specified detail message.
     *
     * @param message The detail message of the exception.
     */
    public ElsaException(String message) {
        super(message);
    }
}
