package dude.exception;

/**
 * Represents a custom exception specific to Dude.
 * Abstract class that serves as the base class for all Dude-specific exceptions.
 */
public abstract class DudeException extends Exception {

    /**
     * Constructs a new DudeException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public DudeException(String message) {
        super("Oops!! " + message);
    }
}
