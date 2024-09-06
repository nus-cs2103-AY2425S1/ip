package gale.exception;

/**
 * Represents an exception that occurs when running the main application.
 *
 * @author kaikquah
 */
public class GaleException extends Exception {

    /**
     * Constructs a GaleException with the given String.
     * @param errorMsg the error message to be displayed
     */
    public GaleException(String errorMsg) {
        super(errorMsg);
    }
}
