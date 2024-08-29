package gavinchatbot.util;

/**
 * Represents a custom exception for the GavinChatBot application.
 * This exception is thrown when specific errors occur within the application.
 */
public class GavinException extends Exception {

    /**
     * Constructs a new GavinException with the specified detail message.
     *
     * @param message The detail message associated with the exception.
     */
    public GavinException(String message) {
        super(message);
    }
}
