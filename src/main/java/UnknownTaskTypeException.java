/**
 * Exception thrown when an unknown task type is provided.
 */
public class UnknownTaskTypeException extends OllieException {
    /**
     * Constructs an UnknownTaskTypeException with a predefined message.
     */
    public UnknownTaskTypeException() {
        super("Oops! I don't recognize this task type. Please use 'todo', 'deadline', or 'event'. ☺");
   