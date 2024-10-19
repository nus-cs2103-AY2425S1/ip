package exception;

/**
 * Denote exceptions specific to the Hyperion chat-bot
 */
public class HyperionException extends Exception {
    /**
     * Constructs a new {@code HyperionException} instance
     * @param input the error message that is associated with the exception
     */
    public HyperionException(String input) {
        super(input);
    }
}
