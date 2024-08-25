package duck.data.exception;

/**
 * Represents an exception that occurs within the Duck application.
 * This exception is used to handle errors and issues specific to the application's operations.
 */
public class DuckException extends Exception {

    /**
     * Constructs a DuckException with the specified detail message.
     *
     * @param message The detail message associated with the exception.
     */
    public DuckException(String message) {
        super(message);
    }
}
