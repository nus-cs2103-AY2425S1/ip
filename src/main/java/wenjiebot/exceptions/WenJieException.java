package wenjiebot.exceptions;

/**
 * Represents a custom exception specific to the Wen Jie chat bot.
 * This exception is thrown when the bot encounters an error in user input
 * or during command processing.
 */
public class WenJieException extends Exception {

    /**
     * Constructs a WenJieException with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    WenJieException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this WenJieException.
     *
     * @return the detail message string
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
