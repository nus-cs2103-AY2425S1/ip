package brainrot.exceptions;

/**
 * The UnknownCommandException class represents an Unknown Command
 * It extends the Exception class and includes a constructor to throw the exception message
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructs a new Unknown Command Exception with a specific message.
     *
     * @param message The message of the exception
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
