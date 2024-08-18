/**
 * Represents a general exception in the Ollie chatbot.
 */
public class OllieException extends Exception {
    /**
     * Constructs an OllieException with the specified detail message.
     *
     * @param message the detail message
     */
    public OllieException(String message) {
        super(message);
    }
}