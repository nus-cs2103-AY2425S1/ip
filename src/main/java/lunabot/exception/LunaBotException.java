package lunabot.exception;

/**
 * Represents an exception specific to the LunaBot application.
 * This exception is thrown when an error related to task operations or
 * input formatting occurs during the execution of commands.
 */
public class LunaBotException extends Exception {

    /**
     * Constructs a new LunaBotException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public LunaBotException(String message) {
        super(message);
    }
}
