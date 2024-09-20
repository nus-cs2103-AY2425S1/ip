package exceptions;

/**
 * The BuddyException class represents a custom exception that is thrown when
 * an application-specific error occurs. It extends the {@code Exception} class
 * and is used to handle errors in the Buddy application.
 */
public class BuddyException extends Exception {

    /**
     * Constructs a new BuddyException with the specified detail message.
     * The message provides information about the error that occurred.
     *
     * @param message the detail message that explains the reason for the exception.
     */
    public BuddyException(String message) {
        super(message);
    }
}