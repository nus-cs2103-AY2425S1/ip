package Johnson.exceptions;

/**
 * Exception thrown when a task is missing for a command that requires one.
 */
public class MissingTaskException extends Exception {

    public static final String MISSING_TASK_MESSAGE = "You forgot to specify the task!";
    public MissingTaskException(String message) {
        super(message);
    }
}