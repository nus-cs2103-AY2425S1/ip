package oyster.exceptions;

/**
 * Exception when a field for a Task is filled wrong.
 */
public class TaskFieldException extends OysterException {
    public final String field;

    /**
     * Creates a TaskFieldException.
     *
     * @param field The field of the Task in question.
     */
    public TaskFieldException(String field) {
        super(String.format("[%s] %s", "Invalid field", field));
        this.field = field;
    }
}
