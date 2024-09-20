package exceptions;

/**
 * The IndexOutBoundsException is thrown when the Index is out of bounds.
 * This exception is typically used in commands that require Integer input.
 */
public class IndexOutBoundsException extends Exception {

    /**
     * Constructs a new IndexOutBoundsException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public IndexOutBoundsException(String message) {
        super(message);
    }
}
