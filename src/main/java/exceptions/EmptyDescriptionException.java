package exceptions;

/**
 * The EmptyDescriptionException is thrown when a required description or input is missing.
 * This exception is typically used in commands that require additional details or parameters.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs a new EmptyDescriptionException with the specified detail message.
     */
    public EmptyDescriptionException() {
        super("Either you've missed some parameters or you typed the command wrongly");
    }
}
