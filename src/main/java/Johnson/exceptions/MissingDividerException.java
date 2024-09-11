package Johnson.exceptions;

/**
 * Exception thrown when a divider is missing between the task and date of a dated task.
 */
public class MissingDividerException extends Exception{
    public static final String MISSING_DIVIDER_MESSAGE = "You forget your basic training already, Chief? You need a / to separate the task and date!\n" +
            "Use the format: <task type> <task name> /by <date> <time> or /by <date> to add a date to your task.";

    public MissingDividerException(String message) {
        super(message);
    }
}
