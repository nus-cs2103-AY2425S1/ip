package ned.exceptions;

/**
 * Exception thrown when an invalid index is encountered.
 *
 * <p>This exception indicates that an operation has failed because it received
 * an index that is out of bounds, negative when a positive index was expected,
 * or otherwise invalid in the given context. It extends {@link NedException} to
 * provide a specific exception type for index-related errors within the NED application.
 *
 * @see NedException
 */
public class InvalidIndexException extends NedException {

    /**
     * Constructs a new {@code InvalidIndexException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}
