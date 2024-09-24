package duke;

/**
 * Exception thrown when an empty command is entered by the user.
 */
public class EmptyTaskException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "The description of the task must contain some substance; it cannot be void.";
    }
}
