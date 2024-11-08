package ahmad.exceptions;

/**
 * Exception class for Ahmad bot.
 */
public class AhmadException extends Exception {
    /**
     * Constructs an AhmadException instance based on the message.
     *
     * @param message Error message.
     */
    public AhmadException(String message) {
        super(message);
    }
}
