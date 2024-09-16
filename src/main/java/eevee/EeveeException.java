package eevee;

/**
 * Represents a custom exception used in the Eevee chatbot.
 */
public class EeveeException extends Exception {
    /**
     * Constructs a new EeveeException with the specified detail message.
     *
     * @param message The error detail message.
     */
    public EeveeException(String message) {
        super(message);
    }
}
