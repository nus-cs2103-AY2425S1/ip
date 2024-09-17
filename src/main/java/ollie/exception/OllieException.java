package ollie.exception;

/**
 * Represents a checked exception from our chatbot, Ollie.
 */
public class OllieException extends Exception {
    /**
     * Constructs a general exception for Ollie bot
     */
    public OllieException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }
}
