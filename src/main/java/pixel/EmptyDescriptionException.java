package pixel;

/**
 * Exception that is thrown when no description is given
 * when user types todo, deadline, event
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructor method for EmptyDescriptionException
     *
     * @param message message to be displayed when Exception is thrown
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
