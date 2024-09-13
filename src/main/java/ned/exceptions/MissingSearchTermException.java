package ned.exceptions;

/**
 * Exception thrown when a required search term is missing.
 *
 * <p>This exception indicates that an operation has failed because a search term
 * was expected but not provided, possibly due to user input error or missing query
 * parameters. It extends {@link NedException} to provide a specific exception type
 * for missing search term-related errors within the NED application.
 *
 * @see NedException
 */
public class MissingSearchTermException extends NedException {

    /**
     * Constructs a new {@code MissingSearchTermException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public MissingSearchTermException(String errorMessage) {
        super(errorMessage);
    }
}

