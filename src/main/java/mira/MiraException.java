package mira;

/**
 * Represents exceptions specific to the Mira chatbot.
 */
public class MiraException extends Exception {
    /**
     * Constructs a new MiraException with the specified detail message.
     *
     * @param message The detail message.
     */
    public MiraException(String message) {
        super(message);
    }
}
