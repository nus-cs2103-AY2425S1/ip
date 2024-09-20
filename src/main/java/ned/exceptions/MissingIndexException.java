package ned.exceptions;

/**
 * Exception thrown when a required index is missing.
 *
 * <p>This exception indicates that an operation has failed because an expected
 * index is absent, either due to missing data, improper input, or failure to initialize
 * a required index. It extends {@link NedException} to provide a specific exception type
 * for missing index-related errors within the NED application.
 *
 * @see NedException
 */
public class MissingIndexException extends NedException {

    /**
     * Constructs a new {@code MissingIndexException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public MissingIndexException(String errorMessage) {
        super(errorMessage);
    }
}
