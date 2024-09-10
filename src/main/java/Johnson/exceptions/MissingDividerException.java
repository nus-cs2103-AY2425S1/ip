package Johnson.exceptions;

/**
 * Exception thrown when a divider is missing between the task and date of a dated task.
 */
public class MissingDividerException extends Exception{
    public MissingDividerException(String message) {
        super(message);
    }
}
