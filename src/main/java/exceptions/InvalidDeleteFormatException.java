package exceptions;

/**
 * Thrown when an invalid delete command is parsed.
 *
 * @author IsaacPangTH
 */
public class InvalidDeleteFormatException extends HimException {

    /**
     * Constructor for<code>InvalidDeleteFormatException</code>.
     */
    public InvalidDeleteFormatException() {
        super("Tell me which task you want me to delete!!!!");
    }
}
