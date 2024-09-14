package hoodini;

/**
 * Throws exception when
 * there is an error reading from file
 */
public class ReadException extends Exception {
    /**
     * Stores error message
     * @param message Message of error after reading file
     */
    public ReadException(String message) {
        super(message);
    }
}
