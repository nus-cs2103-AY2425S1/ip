package exceptions;

/**
 * Thrown when an invalid find string is parsed.
 *
 * @author IsaacPangTH
 */
public class InvalidFindFormatException extends HimException {

    /**
     * Constructor for<code>InvalidFindFormatException</code>.
     */
    public InvalidFindFormatException() {
        super("Find requires a single keyword!");
    }
}
