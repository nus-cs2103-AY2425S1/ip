package yapyap;

/**
 * Represents a custom exception specific to the YapperBot application.
 * This exception is used to indicate errors that are specific to the application's operations.
 */
public class YapperBotException extends Exception {

    /**
     * Creates a YapperBotException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public YapperBotException(String message) {
        super(message);
    }
}
