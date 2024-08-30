package thebotfather.util;

/**
 * TheBotFatherException is a custom exception class for the TheBotFather application.
 * This exception is thrown when the application encounters specific errors
 * related to user commands, file handling, or other custom conditions within the application.
 */
public class TheBotFatherException extends Exception {

    /**
     * Constructs a new TheBotFatherException with the specified detail message.
     *
     * @param message The detail message, which provides more information about the cause of the exception.
     */
    public TheBotFatherException(String message) {
        super(message);
    }
}
