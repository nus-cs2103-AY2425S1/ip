package ollie.exception;

/**
 * Represents a checked exception from our chatbot, Ollie.
 */
public class OllieException extends Exception{
    public OllieException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }
}
