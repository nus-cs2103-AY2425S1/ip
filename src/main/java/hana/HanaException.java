package hana;

/**
 * Custom exception class for the Hana application.
 */
public class HanaException extends Exception {

    /**
     * Constructs a new HanaException with the specified detail message.
     *
     * @param message The detail message.
     */
    public HanaException(String message) {
        super(message);
    }
}
