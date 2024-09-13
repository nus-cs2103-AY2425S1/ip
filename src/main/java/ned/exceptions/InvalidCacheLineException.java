package ned.exceptions;

/**
 * Exception thrown when an invalid cache line is encountered.
 *
 * <p>This exception indicates that an operation has failed because it processed
 * a cache line that is malformed, corrupted, or does not conform to the expected
 * format. It extends {@link NedException} to provide a specific exception type
 * for cache line-related errors within the NED application.
 *
 * @see NedException
 */
public class InvalidCacheLineException extends NedException {

    /**
     * Constructs a new {@code InvalidCacheLineException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public InvalidCacheLineException(String errorMessage) {
        super(errorMessage);
    }
}
