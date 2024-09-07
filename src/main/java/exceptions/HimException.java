package exceptions;

/**
 * Exception thrown during execution of Him.
 *
 * @author IsaacPangTH
 */
public abstract class HimException extends Exception {

    /**
     * Constructor for<code>HimException</code>.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public HimException(String message) {
        super(message);
    }
}
