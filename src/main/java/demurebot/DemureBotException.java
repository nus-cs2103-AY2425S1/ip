package demurebot;

/**
 * Custom exception class for DemureBot application.
 * This exception is thrown when a specific error condition occurs within the DemureBot application.
 */
public class DemureBotException extends Exception {

    /**
     * Constructs a new DemureBotException with the specified detail message.
     *
     * @param message The detail message which provides more information about the error.
     */
    public DemureBotException(String message) {
        super(message);
    }
}
