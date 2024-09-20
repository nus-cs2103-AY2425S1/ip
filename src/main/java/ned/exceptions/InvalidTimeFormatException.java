package ned.exceptions;

/**
 * Exception thrown when an invalid time format is encountered.
 *
 * <p>This exception indicates that an operation has failed because it encountered
 * a time string that does not match the expected format or contains invalid time values.
 * It extends {@link NedException} to provide a specific exception type for time format-related
 * errors within the NED application.
 *
 * @see NedException
 */
public class InvalidTimeFormatException extends NedException {

    /**
     * Constructs a new {@code InvalidTimeFormatException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public InvalidTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
