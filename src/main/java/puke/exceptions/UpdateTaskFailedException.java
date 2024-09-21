package puke.exceptions;

/**
 * Exception thrown when an update operation on a task fails.
 */
public class UpdateTaskFailedException extends PukeException {

    private static final String PREFIX = "OOPS!!! Cannot update the task cuz the following reason: ";

    /**
     * Constructs an UpdateTaskFailedException with the specified detail message.
     *
     * @param message the detail message for the exception
     */
    public UpdateTaskFailedException(String message) {
        super(formatMessage(message));
    }

    /**
     * Ensures that the error message starts with the defined PREFIX.
     * This method checks if the prefix is already present to avoid duplication.
     *
     * @param message the original error message
     * @return a formatted message with the PREFIX appended if it was not already present.
     */
    private static String formatMessage(String message) {
        if (!message.startsWith(PREFIX)) {
            return PREFIX + message;
        }
        return message;
    }
}
