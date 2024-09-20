package Johnson.exceptions;

/**
 * Exception thrown when a date is missing for a task that requires one.
 */
public class MissingDateException extends Exception {

    public static final String MISSING_DATE_MESSAGE = "Did they forget to teach you this in basic? You need a date for this task!\n" +
            "Use the format: <task type> <task name> /by <date> <time> or /by <date> to add a date to your task.";
    public MissingDateException(String message) {
        super(message);
    }
}
