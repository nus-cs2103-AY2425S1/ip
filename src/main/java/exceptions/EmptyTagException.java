package exceptions;

/**
 * Exception that indicates that the tag provided is empty, usually because it was just white space.
 *
 */
public class EmptyTagException extends Exception {

    /**
     * Default error message to show users
     */
    public EmptyTagException() {
        super("Error: The tag is empty. Tags cannot just contain whitespace");
    }
}
