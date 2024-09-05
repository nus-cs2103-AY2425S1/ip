package barcus.exception;

/**
 * Custom exception class
 */
public class BarcusException extends Exception {
    /**
     * Constructor
     */
    public BarcusException() {}

    /**
     * Returns error message
     * @param message String with error message
     */
    public BarcusException(String message) {
        super(message);
    }
}
