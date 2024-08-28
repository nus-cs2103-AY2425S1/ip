package bro;

/**
 * Represents a BroException which is an exception thrown by a Bro Instance
 */
public class BroException extends Exception {

    /**
     * Creates a Bro exception
     *
     * @param message The error message of the Bro exception
     */
    public BroException(String message) {
        super(message);
    }
}
