package seanbot;

/**
 * Represents a custom exception for the SeanBot application.
 * This exception is thrown when the application encounters specific errors
 * related to user input or other operations within the SeanBot program.
 */
public class SeanBotException extends Exception {

    /**
     * Constructs a new SeanBotException with the specified detail message.
     */
    public SeanBotException(String message) {
        super(message);
    }
}
