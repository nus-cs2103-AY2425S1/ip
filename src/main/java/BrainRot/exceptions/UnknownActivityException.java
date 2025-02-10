package brainrot.exceptions;

/**
 * The UnknownActivityException class represents an UnknownActivity
 * It extends the Exception class and includes a constructor to throw the exception message
 */
public class UnknownActivityException extends Exception {

    /**
     * Constructs a new Unknown Activity Exception with a specific message.
     *
     * @param message The message of the exception
     */
    public UnknownActivityException(String message) {
        super(message);
    }
}
