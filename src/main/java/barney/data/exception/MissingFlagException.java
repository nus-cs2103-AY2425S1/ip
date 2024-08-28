package barney.data.exception;

/**
 * Exception thrown when a required flag is missing.
 *
 * @param message The detail message explaining the exception.
 */
public class MissingFlagException extends InvalidArgumentException {
    public MissingFlagException(String message) {
        super(message);
    }

}
