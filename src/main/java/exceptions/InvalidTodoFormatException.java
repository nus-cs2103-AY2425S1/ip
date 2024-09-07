package exceptions;

/**
 * Thrown when an invalid todo string is parsed by Him.
 *
 * @author IsaacPangTH
 */
public class InvalidTodoFormatException extends HimException {

    /**
     * Constructor for <code>InvalidTodoFormatException</code>.
     */
    public InvalidTodoFormatException() {
        super("Invalid Todo format");
    }
}