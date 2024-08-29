package main.java.angel;

/**
 * Represents an exception that is thrown when an invalid command is entered in the Angel application.
 * This exception extends the general {@link AngelException} to provide specific error handling
 * for invalid commands.
 */
public class InvalidCommandException extends AngelException {

    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval
     *                by the {@link #getMessage()} method.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
