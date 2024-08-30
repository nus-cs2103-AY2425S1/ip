package Exceptions;

/**
 * The UnsupportedCommandException is thrown when an unsupported or unrecognized command is encountered.
 * This exception is used to handle invalid commands that the application does not recognize or support.
 */
public class UnsupportedCommandException extends Exception {

    /**
     * Constructs a new UnsupportedCommandException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public UnsupportedCommandException(String message) {
        super(message);
    }
}
