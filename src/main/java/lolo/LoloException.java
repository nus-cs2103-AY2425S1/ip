package lolo;

/**
 * Represents an exception specific to the Lolo application.
 * This exception is thrown when the application encounters an error condition.
 */
public class LoloException extends Exception {

    /**
     * Constructs a new LoloException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public LoloException(String message) {
        super(message);
    }
}
