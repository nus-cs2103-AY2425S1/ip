package asura.data.exception;

/**
 * Represents an exception thrown by the Asura program.
 */
public class AsuraException extends Exception{
    /**
     * Creates an AsuraException with the specified message.
     * @param msg The error message that is specified.
     */
    public AsuraException(String msg) {
        super(msg);
    }
}
