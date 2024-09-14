package Exception;

/**
 * Exception thrown when a required date is missing in operations where a date is mandatory.
 * This custom exception is designed to handle cases in task management or scheduling functionalities
 * where a date input is critical and cannot be omitted.
 */
public class MissingDateException extends Exception {

    /**
     * Constructs a new MissingDateException with a detailed message.
     * This message helps in identifying the operation that failed due to the absence of a required date,
     * facilitating easier debugging and user feedback.
     *
     * @param message The detailed message explaining the reason for the exception.
     */
    public MissingDateException(String message) {
        super(message);
    }
}
