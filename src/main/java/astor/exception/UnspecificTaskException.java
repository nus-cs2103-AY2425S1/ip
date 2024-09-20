package astor.exception;

/**
 * Represents an exception where the user provided invalid inputs.
 */
public class UnspecificTaskException extends AstorException {
    /**
     * Creates an AstorException with the guide to use the application.
     */
    public UnspecificTaskException() {
        super("Please be clear on what to do!\n"
                + "Type \"list\" to see the list of tasks,\n\n"
                + "\"mark\" or \"unmark\" to mark tasks, "
                + "\"todo\" (task info) to create todo task,\n"
                + "\"deadline\" (task info) /by (deadline) to create deadline tasks,\n"
                + "\"event\" (task info) /from (start time) /to (end time) to create event tasks"
                + "\"find\" (keyword) to search for tasks");
    }
}
