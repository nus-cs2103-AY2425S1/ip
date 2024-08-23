/**
 * Represents a custom exception used in the Nayana application.
 */
public class NayanaException extends Exception {
    /**
     * Constructs a new NayanaException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public NayanaException(String message) {
        super(message);
    }
}
