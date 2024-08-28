package barney.data.exception;

/**
 * Represents an exception thrown when a flag is missing from the command.
 */
public class MissingFlagException extends InvalidArgumentException {

    /**
     * Constructs a new MissingFlagException with the specified detail message.
     *
     * @param message The detail message.
     */
    public MissingFlagException(String message) {
        super(message);
    }

}
