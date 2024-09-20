package ned.exceptions;

/**
 * Exception thrown when an unknown or unrecognized command is encountered.
 *
 * <p>This exception indicates that an operation has failed because the system
 * received a command that it does not recognize or support, possibly due to user input error
 * or an invalid command format. It extends {@link NedException} to provide a specific exception type
 * for unknown command-related errors within the NED application.
 *
 * @see NedException
 */
public class UnknownCommandException extends NedException {

    /**
     * Constructs a new {@code UnknownCommandException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
